package cn.njust.label.main.controller;

import cn.njust.label.main.dto.Flight;
import cn.njust.label.main.dto.TrackPointItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;



@CrossOrigin
@RestController
@RequestMapping("/mongorestore")
@EnableAutoConfiguration(exclude= MongoAutoConfiguration.class)

public class mongodbTest  {

    @Autowired
    public MongoTemplate mongoTemplate;
    public ArrayList<TrackPointItems> pGPSArrayInit= new ArrayList<>();//原纪录经纬度坐标数组
    public ArrayList<TrackPointItems> pGPSArrayFilter = new ArrayList<>();//过滤后的经纬度坐标数组
    public ArrayList<TrackPointItems> pGPSArrayFilterLevel = new ArrayList<>();//过滤后平飞段的经纬度坐标数组
    public ArrayList<TrackPointItems> pGPSArrayFilterTurn = new ArrayList<>();//过滤后平飞段的经纬度坐标数组

    @RequestMapping("/test")
    @ResponseBody
    public void test(){
        String planeId = "7866659";
        pGPSArrayInit = getENPointFromMongDB(planeId);
        System.out.println("原始经纬度点坐标的个数:"+pGPSArrayInit.size());//输出原始经纬度点坐标的个数

        //-------------------------3、进行轨迹压缩-----------------------------------------------------------------------//
        double DMax = 30.0;//设定最大距离误差阈值
        pGPSArrayFilter.add(pGPSArrayInit.get(0));//获取第一个原始经纬度点坐标并添加到过滤后的数组中
        pGPSArrayFilter.add(pGPSArrayInit.get(pGPSArrayInit.size()-1));//获取最后一个原始经纬度点坐标并添加到过滤后的数组中
        TrackPointItems[] enpInit = new TrackPointItems[pGPSArrayInit.size()];//使用一个点数组接收所有的点坐标，用于后面的压缩
        Iterator<TrackPointItems> iInit = pGPSArrayInit.iterator();
        int jj=0;
        while(iInit.hasNext()){
            enpInit[jj] = iInit.next();
            jj++;
        }//将ArrayList中的点坐标拷贝到点数组中
        int start = 0;//起始下标
        int end = pGPSArrayInit.size()-1;//结束下标
        TrajCompressC(enpInit,pGPSArrayFilter,start,end,DMax);//DP压缩算法
        System.out.println("压缩后的点数:"+pGPSArrayFilter.size());//输出压缩后的点数
        //-------------------------4、对压缩后的经纬度点坐标数据按照ID从小到大排序---------------------------------------------//
        TrackPointItems[] enpFilter = new TrackPointItems[pGPSArrayFilter.size()];//使用一个点数组接收过滤后的点坐标，用于后面的排序
        Iterator<TrackPointItems> iF = pGPSArrayFilter.iterator();
        int i = 0;
        while(iF.hasNext()){
            enpFilter[i] = iF.next();
            i++;
        }//将ArrayList中的点坐标拷贝到点数组中
        Arrays.sort(enpFilter);//进行排序
        //将排序后的点坐标写到一个新的ArrayList数组中
        //过滤并排序后的经纬度坐标数组
        ArrayList<TrackPointItems> pGPSArrayFilterSort = new ArrayList<>(Arrays.asList(enpFilter));
        //-------------------------5、生成仿真测试文件--------------------------------------------------------------------//
//        writeTestPointToFile(fTestInitPoint,pGPSArrayInit);//将原始经纬度数据点写入仿真文件中，格式为“经度，维度”
//        writeTestPointToFile(fTestFilterPoint, pGPSArrayFilterSort);//将过滤后的经纬度数据点写入仿真文件中，格式为“经度，维度”
        //-------------------------6、求平均误差-------------------------------------------------------------------------//
        double mDError = getMeanDistError(pGPSArrayInit,pGPSArrayFilterSort);//求平均误差
        System.out.println("平均误差:"+mDError);
        //-------------------------7、求压缩率--------------------------------------------------------------------------//
        double cRate = (double)pGPSArrayFilter.size()/pGPSArrayInit.size()*100;//求压缩率
        System.out.println("压缩率:"+cRate);

        //-------------------------8、求平飞段、转向段--------------------------------------------------------------------------//
        //过滤并排序后的经纬度坐标数组

        ArrayList<TrackPointItems> pGPSArrayFilterLT = new ArrayList<>(getIfturn(pGPSArrayFilter, 3, 2, 2));
        for (TrackPointItems enPoint : pGPSArrayFilterLT) {
            if (enPoint.ifturn == 0) {
                pGPSArrayFilterLevel.add(enPoint);//平飞段
            } else {
                pGPSArrayFilterTurn.add(enPoint);//转向段
            }
        }
        System.out.println(pGPSArrayFilterLevel);
        //return 平飞段
//        return pGPSArrayFilterLevel;
    }


    /**
     * 函数功能：使用三角形面积（使用海伦公式求得）相等方法计算点pX到点pA和pB所确定的直线的距离
     * @param pA：起始点
     * @param pB：结束点
     * @param pX：第三个点
     * @return distance：点pX到pA和pB所在直线的距离
     */
    public static double distToSegment(TrackPointItems pA,TrackPointItems pB,TrackPointItems pX){
        double a = Math.abs(geoDist(pA, pB));
        double b = Math.abs(geoDist(pA, pX));
        double c = Math.abs(geoDist(pB, pX));
        double p = (a+b+c)/2.0;
        double s = Math.sqrt(Math.abs(p*(p-a)*(p-b)*(p-c)));
        return s*2.0/a;
    }

    /**
     * 函数功能：求两个经纬度点之间的距离
     * @param pA：起始点
     * @param pB：结束点
     * @return distance：距离
     */
    public static double geoDist(TrackPointItems pA,TrackPointItems pB)
    {
        double radLat1 = Rad(pA.getLatitudeMultiple());
        double radLat2 = Rad(pB.getLatitudeMultiple());
        double delta_lon = Rad(pB.getLongitudeMultiple() - pA.getLongitudeMultiple());
        double top_1 = Math.cos(radLat2) * Math.sin(delta_lon);
        double top_2 = Math.cos(radLat1) * Math.sin(radLat2) - Math.sin(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
        double top = Math.sqrt(top_1 * top_1 + top_2 * top_2);
        double bottom = Math.sin(radLat1) * Math.sin(radLat2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(delta_lon);
        double delta_sigma = Math.atan2(top, bottom);
        return delta_sigma * 6378137.0;
    }
    /**
     * 函数功能：角度转弧度
     * @param d：角度
     * @return 返回的是弧度
     */
    public static double Rad(double d)
    {
        return d * Math.PI / 180.0;
    }

    /**
     * 函数功能：根据最大距离限制，采用DP方法递归的对原始轨迹进行采样，得到压缩后的轨迹
     * @param enpInit：原始经纬度坐标点数组
     * @param enpArrayFilter：保持过滤后的点坐标数组
     * @param start：起始下标
     * @param end：终点下标
     * @param DMax：预先指定好的最大距离误差
     */
    public static void TrajCompressC(TrackPointItems[] enpInit,ArrayList<TrackPointItems> enpArrayFilter,
                                     int start,int end,double DMax){
        if(start < end){//递归进行的条件
            double maxDist = 0;//最大距离
            int cur_pt = 0;//当前下标
            for(int i=start+1;i<end;i++){
                double curDist = distToSegment(enpInit[start],enpInit[end],enpInit[i]);//当前点到对应线段的距离
                if(curDist > maxDist){
                    maxDist = curDist;
                    cur_pt = i;
                }//求出最大距离及最大距离对应点的下标
            }
            //若当前最大距离大于最大距离误差
            if(maxDist >= DMax){
                enpArrayFilter.add(enpInit[cur_pt]);//将当前点加入到过滤数组中
                //将原来的线段以当前点为中心拆成两段，分别进行递归处理
                TrajCompressC(enpInit,enpArrayFilter,start,cur_pt,DMax);
                TrajCompressC(enpInit,enpArrayFilter,cur_pt,end,DMax);
            }
        }
    }
    /**
     * 函数功能：求平均距离误差
     * @param pGPSArrayInit：原始数据点坐标
     * @param pGPSArrayFilterSort：过滤后的数据点坐标
     * @return ：返回平均距离
     */
    public static double getMeanDistError(
            ArrayList<TrackPointItems> pGPSArrayInit,ArrayList<TrackPointItems> pGPSArrayFilterSort){
        double sumDist = 0.0;
        for(int i=1;i<pGPSArrayFilterSort.size();i++){
            int start = pGPSArrayFilterSort.get(i - 1).getItemid()-1; //itemID是从1开始的
            int end = pGPSArrayFilterSort.get(i).getItemid()-1; //itemID是从1开始的
            for(int j=start+1;j<end;j++){
                sumDist += distToSegment(
                        pGPSArrayInit.get(start),pGPSArrayInit.get(end),pGPSArrayInit.get(j));
            }
        }
        return sumDist/(pGPSArrayInit.size());
    }

    /**
     * 函数功能：求航向角（将经线至北向南方向作为基准方向。区间起点到终点方向作为该段区间航行方向，基准方向与航行方向的夹角定义区间夹角，）
     * @param lat1：起始点纬度
     * @param lon1：起始点经度
     * @param lat2：终点纬度
     * @param lon2：终点经度
     * @return ：返回航向角
     */
    public static double bearing(double lat1, double lon1, double lat2, double lon2){
        double latitude1 = Math.toRadians(lat1);
        double latitude2 = Math.toRadians(lat2);
        double longDiff = Math.toRadians(lon2 - lon1);
        double y = Math.sin(longDiff)*Math.cos(latitude2);
        double x = Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);
        double Nbearing = (Math.toDegrees(Math.atan2(y, x))+360)%360;
        return -Nbearing+180;
    }
    /**
     * 函数功能：为对象补充航向角数据
     * @param pGPSArrayFilter：原始数据点坐标
     */
    //方位角 数组
    public static ArrayList<TrackPointItems> getDirectList(ArrayList<TrackPointItems> pGPSArrayFilter){
        for(int i=0;i<pGPSArrayFilter.size() - 1;i++){
            pGPSArrayFilter.get(i).angle = bearing(pGPSArrayFilter.get(i).getLongitudeMultiple(), pGPSArrayFilter.get(i).getLatitudeMultiple(), pGPSArrayFilter.get(i + 1).getLongitudeMultiple(), pGPSArrayFilter.get(i + 1).getLatitudeMultiple());
        }
        return pGPSArrayFilter;
    }
    /**
     * 函数功能：标记平飞段、转向段
     * @param window:窗口大小
     * @param a:多区间转向阈值
     * @param b:单区间转向阈值
     * @param pGPSArrayFilter:原始数据
     */
    public  static  ArrayList<TrackPointItems> getIfturn(ArrayList<TrackPointItems> pGPSArrayFilter,int window,double a,double b) {
        LinkedList<Object> l = new LinkedList<>();
        ArrayList<TrackPointItems> p = new ArrayList<>(getDirectList(pGPSArrayFilter));
        for (int i = 0; i < p.size(); i++) {
            l.add(p.get(i).getItemid());
            if (l.size() == window) {
                if (Math.abs(p.get(i-2).angle-p.get(i-1).angle)>=a && Math.abs(p.get(i-1).angle-p.get(i).angle)>=a){
                    p.get(i-2).ifturn = 1;
                    p.get(i-1).ifturn = 1;
                    p.get(i).ifturn = 1;
                }else if (Math.abs(p.get(i-1).angle-p.get(i).angle)>=b){
                    p.get(i).ifturn = 1;
                }else {
                    p.get(i-2).ifturn = 0;
                }
                l.removeFirst();
            }
//            System.out.println(p.get(i));
        }
        return p;
    }

    public ArrayList<TrackPointItems> getENPointFromMongDB(String planeId){
        ArrayList<Flight> aa;//原纪录经纬度坐标数组
        Query query1 = new Query(Criteria.where("planeId").is(planeId));

        aa = (ArrayList<Flight>) mongoTemplate.find(query1, Flight.class);//从原始数据文件中获取转换后的经纬度坐标点数据，存放到ArrayList数组中

        ArrayList<TrackPointItems> pGPSArrayInit = aa.get(0).getTrackPointItems();

        return  pGPSArrayInit; //我真正要用的，没错啦！！！

    }

}

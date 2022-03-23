package cn.njust.label.main.controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.List;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

import cn.njust.label.main.dto.*;
import cn.nocml.FileTool;
import cn.nocml.MathTool;
import cn.nocml.Pair;

import cn.njust.label.main.dto.Flight;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import static cn.njust.label.main.controller.mongodbTest.getIfturn;
import static java.lang.Integer.parseInt;

@ComponentScan
@CrossOrigin
@RestController
@RequestMapping("/xx")
@EnableAutoConfiguration(exclude= MongoAutoConfiguration.class)
@Data
@Document("TraCluster")
public class TraCluster {

    @Autowired
    MongoTemplate mongoTemplate;
    public HashMap<Integer , Flight> trajectorys = new HashMap<Integer, Flight>();

    private int MDL_COST_ADVANTAGE = 0;
    /*
     * partition 之后的线段
     */
    private ArrayList<Line> lines = new ArrayList<Line>();
    /*
     * 初始轨迹
     */

    public HashMap<Integer , ArrayList<Line>> cluster = new HashMap<Integer, ArrayList<Line>>();
    CentreTra rtra = new CentreTra(); //rtra是代表轨迹,即中心轨迹
    int minLines = 8;
    double eps = 29;
    double r = 10;  //相似性度量半径
    double ep = 30; //相似性度量角度阈值
    public ArrayList<Line> getLines() {
        return lines;
    }

    public void setMDL_COST_ADVANTAGE(int mDL_COST_ADVANTAGE) {
        MDL_COST_ADVANTAGE = mDL_COST_ADVANTAGE;
    }

    public HashMap<Integer, Flight> getTrajectorys() {
        return trajectorys;
    }

    public void setParameter(int minLines , double eps){
        this.minLines = minLines;
        this.eps = eps;
    }


    //分割后线段较多  分段
    public void partition(){
        long startTime = System.currentTimeMillis();
        System.out.println("partition...");
        System.out.println("航班数量size "+trajectorys.size());
        int c = 0; //用来在console看进度
        int prels = 0; //分割前总共有多少线段
        for(Map.Entry<Integer, Flight> en : trajectorys.entrySet()){
            prels = prels+en.getValue().getPoints().size();
            List<Line> ls = partition(en.getValue().getPoints());
            lines.addAll(ls);
            if(c %500 == 0){
                long endTime = System.currentTimeMillis();
                long usedTime = (endTime-startTime)/1000;
                System.out.println("processed(partition) " + c + "..."+usedTime+"秒");
            }
            c++;
        }
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime-startTime)/1000;
        System.out.println("partition分割时间"+usedTime+"秒");
        System.out.println("分割前lines " + prels); //分割后线段大小，lines（分割后的线段）
        System.out.println("分割后lines " + lines.size()); //分割后线段大小，lines（分割后的线段）
    }
    //分段--提取特征点
    private List<Line> partition(List<TrackPointItems> trackPointItems){
        try{
            int start = 0;
            int end = 1;
            List<Line> list = new ArrayList<Line>();
            double cost_par , cost_nopar;
            cost_par = cost_nopar = 0.0;
            while(end < trackPointItems.size()){
                cost_par = Distance.distance_mdl_par(trackPointItems, start, end);
                cost_nopar = Distance.distance_mdl_nopar(trackPointItems, start, end);
                if(cost_par > cost_nopar + MDL_COST_ADVANTAGE){
                    Line line = new Line(trackPointItems.get(start), trackPointItems.get(end - 1));
                    line.setNum(trackPointItems.get(0).getNum());
                    list.add(line);
                    start = end - 1 ;
                }else{
                    end++;
                }
            }
            //没有特征点
            if(list.size()==0){
                for(int i = 0; i < trackPointItems.size() -1; i++){
                    Line line = new Line(trackPointItems.get(i), trackPointItems.get(i+1));
                    line.setNum(trackPointItems.get(0).getNum());
                    list.add(line);
                }
            }
            else{
                //如果只剩一个点，则加入到之前的的线段中
                if ((trackPointItems.size() - 1) - start == 1) {
                    list.get(list.size() - 1).setE(trackPointItems.get(end - 1));
                }
                //剩多个点，则加入到最后
                else{
                    Line temp = new Line(trackPointItems.get(start), trackPointItems.get(end - 1));
                    temp.setNum(trackPointItems.get(0).getNum());
                    list.add(temp);
                }
            }



            return list;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //修改后
//	private List<Line> partition(List<TrackPointItems> points){
//		try{
//			int start = 0;
//			int end = 1;
//			int current;
//			List<Line> list = new ArrayList<Line>();
//			double cost_par , cost_nopar;
//			cost_par = cost_nopar = 0.0;
//			while(end+start <= points.size()){
//				current = end + start;
//				cost_par = Distance.distance_mdl_par(points, start, current+1);
//				cost_nopar = Distance.distance_mdl_nopar(points, start, current+1);
//				if(cost_par > cost_nopar + MDL_COST_ADVANTAGE){
//					Line line = new Line(points.get(start), points.get(current));
//					line.setNum(points.get(0).getNum());
//					list.add(line);
//					start = current;
//					end = 1;
//				}else{
//					end++;
//				}
//			}
////			//如果只剩一个点，则加入到之前的的线段中
////			if ((points.size() - 1) - start == 1) {
////				list.get(list.size() - 1).setE(points.get(end - 1));
////			}
////			//剩多个点，则加入到最后
////			else{
////				Line temp = new Line(points.get(start), points.get(end - 1));
////				temp.setNum(points.get(0).getNum());
////				list.add(temp);
////			}
//
//			return list;
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return null;
//	}


//    lines直接用覆盖的
    public void getNoTurnLines(){
        ArrayList<TrackPointItems> postPatitPoints =new ArrayList<>();

        postPatitPoints.add(lines.get(0).getS());
        for(int i = 0 ; i < lines.size(); i++) {
            Line l = lines.get(i);
            if (postPatitPoints.get(postPatitPoints.size()-1).equals(l.getS())) {
                postPatitPoints.add(l.getE());
            }
            else {
                postPatitPoints.add(l.getS());
                postPatitPoints.add(l.getE());
            }
        }

        //利用删去重复元素得到postPatitPoints
//        for(int i = 0 ; i < lines.size(); i++){
//            Line l = lines.get(i);
//            postPatitPoints.add(l.getS());
//            postPatitPoints.add(l.getE());
////            System.out.println(l.getS().getLatitude()+" "+postPatitPoints.get(i).getLatitude()+"是否相等"+(l.getS().getLatitude()-postPatitPoints.get(i).getLatitude()));
//        }

//        for (int i = 0; i < postPatitPoints.size(); i++) {
//            for (int j = 1 + i; j < postPatitPoints.size(); j++) {
//                if (postPatitPoints.get(i).equals(postPatitPoints.get(j))) {
//                    postPatitPoints.remove(j);
//                }
//            }
//        }


        ArrayList<TrackPointItems> pGPSArrayLT = new ArrayList<>(getIfturn(postPatitPoints, 3, 3, 2));
        ArrayList<TrackPointItems> pGPSArrayLevel = new ArrayList<>();//过滤后平飞段的经纬度坐标数组
        ArrayList<TrackPointItems> pGPSArrayTurn = new ArrayList<>();//过滤后转向段的经纬度坐标数组

        for (TrackPointItems enPoint : pGPSArrayLT) {
            if (enPoint.ifturn == 0) {
                pGPSArrayLevel.add(enPoint);//平飞段
            } else {
                pGPSArrayTurn.add(enPoint);//转向段
            }
        }
        lines.clear();
        //平飞段数据加载入lines
        for(int i = 0; i < pGPSArrayLevel.size()-1; i++){
            Line linetemp = new Line(pGPSArrayLevel.get(i), pGPSArrayLevel.get(i+1));
            linetemp.setNum(pGPSArrayLevel.get(i).getNum());
            linetemp.setOrder(pGPSArrayLevel.get(i).getItemid());
            lines.add(linetemp);
        }
        System.out.println("平飞段的段数"+lines.size());

    }


    //DBScan聚类
    public void cluster(){
        System.out.println("cluster...");
        System.out.println("line size : " + lines.size());
        int end = lines.size();
        int clusterId = 1;
        for(int i = 0 ; i < end ; i++){
            if(i % 500 == 0)
                System.out.println("processed(cluster) " + i + "..."); //用来查看进度
            Line l = lines.get(i);
            if(l.getClassifiy() == 0){
                ArrayList<Integer> neighbor = getNeighbor(i); //返回l邻域内的线段
                if(neighbor.size() + 1 >= minLines){
                    lines.get(i).setClassifiy(2);
                    lines.get(i).addCluster(neighbor); //cluster加入邻域线段
                    lines.get(i).addCluster(i); //cluster加入本身线段
                    lines.get(i).setClusterId(clusterId);
                    for(int ndx : neighbor){
                        lines.get(ndx).setClassifiy(2);
                    }
                    ExpandCluster(i , neighbor);
                    clusterId++;
                }else{
                    l.setClassifiy(1);
                }
            }
        }


        for(int i = 0 ; i < lines.size() ; i++){
            if(lines.get(i).getClusterId() > 0){
                ArrayList<Line> ls = new ArrayList<Line>();
                clusterId = lines.get(i).getClusterId();
                for(int j : lines.get(i).getCluster()){
                    ls.add(lines.get(j));
                }
                cluster.put(clusterId, ls);
            }
        }
        System.out.println("cluster end...");
    }
    //升序排序
    public void sortLine(){
        try{
            Collections.sort(lines, new Comparator<Line>(){
                @Override
                public int compare(Line l1, Line l2) {
                    return l1.getNum() - l2.getNum();
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    //文档中相似度判断聚类
//	public void similarCluster(){
//		int end = lines.size();
//		int clusterId = 1;
//		for(int i = 0 ; i < end ; i++){
//			Line l = lines.get(i);
//			if(l.getClassifiy() == 0){
//				ArrayList<Integer> neighbor = getNeighbor(i);
//				if(neighbor.size() + 1 >= minLines){
//					lines.get(i).setClassifiy(2);
//					lines.get(i).addCluster(neighbor);
//					lines.get(i).addCluster(i);
//					lines.get(i).setClusterId(clusterId);
//					for(int ndx : neighbor){
//						lines.get(ndx).setClassifiy(2);
//					}
//					ExpandCluster(i , neighbor);
//					clusterId++;
//				}else{
//					l.setClassifiy(1);
//				}
//			}
//		}
//
//	}



    public HashMap<Integer , ArrayList<Line>> getCluster(){
        return cluster;
    }
    public void outputCluster(String ofile){
        ArrayList<String> save = new ArrayList<String>();
        for(Map.Entry<Integer , ArrayList<Line>> en : this.cluster.entrySet()){
            for(Line l : en.getValue()){
                save.add(en.getKey() + "\t" + l.toString());
            }
        }
        try {
            FileTool.SaveListToFile(save, ofile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //	Step2 计算核心线段的密度相连集合并把其加入该核心线段组成的簇中。
//		如果新加入的线段未被分类，则把其加入队列Q中以做进一步扩展，因为该线段可能是核心线段；
//		若新加入的线段不是核心线段，则不加入队列Q中。
    private void ExpandCluster(int center , ArrayList<Integer> neighbor){
//        System.out.println("Expanding...");
        while(neighbor.size() > 0){
            int index = neighbor.get(0);
            ArrayList<Integer> sub_neighbor = getNeighbor(index);
            if (sub_neighbor.size() + 1>= minLines) {
                for (int ndx : sub_neighbor) {
                    if (lines.get(ndx).getClassifiy() == 0 || lines.get(ndx).getClassifiy() == 1) {
                        lines.get(center).addCluster(ndx);
                        lines.get(ndx).setClassifiy(2);
                    }
                    if(lines.get(ndx).getClassifiy() == 0){
                        neighbor.add(ndx);
                    }
                }
            }
            neighbor.remove(0);
        }
    }

    //similarCluster
    //文档中相似性判断
    //返回l邻域内的线段（相似）
    private ArrayList<Integer> getNeighbor(int index){
        ArrayList<Integer> ndxs = new ArrayList<Integer>();
        Line l = lines.get(index);
        Line llong = new Line();
        Line lshort = new Line();
        for(int i = 0 ; i < lines.size() ;i++){
            if(i == index)
                continue;
            Line ltemp = lines.get(i);
            //比较自身线段长度和其他点线段长度
            if (Distance.distance(l) >= Distance.distance(ltemp)) {
                llong = l ; lshort = ltemp;
            }else{
                llong = ltemp ; lshort = l;
            }
            double dis = Distance.dist(llong,lshort); //轨迹距离函数，垂直距离+平行距离+角度
            if (Distance.distance(llong.getS(),lshort.getS())<r&&Distance.distance_angle(llong,lshort)<ep) {
                ndxs.add(i);
            }
        }
        return ndxs;
    }

    //原算法
    //返回l邻域内的线段
    //线段的ε 邻域Nε(Li)
    private ArrayList<Integer> getNeighbor2(int index){
        ArrayList<Integer> ndxs = new ArrayList<Integer>();
        Line l = lines.get(index);
        Line llong = new Line();
        Line lshort = new Line();
        for(int i = 0 ; i < lines.size() ;i++){
            if(i == index)
                continue;
            Line ltemp = lines.get(i);
            //比较自身线段长度和其他点线段长度
            if (Distance.distance(l) >= Distance.distance(ltemp)) {
                llong = l ; lshort = ltemp;
            }else{
                llong = ltemp ; lshort = l;
            }
            double dis = Distance.dist(llong,lshort); //轨迹距离函数，垂直距离+平行距离+角度
            if (dis <= eps) {
                ndxs.add(i);
            }
        }
        return ndxs;
    }
    public void ouputLines(String ofile){
        List<String> ls = new ArrayList<String>();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int order = 0;
        for(Line l : lines){
            l.setOrder(order++);
            ls.add(l.toString());
            if(map.containsKey(l.getNum())){
                map.put(l.getNum(), map.get(l.getNum()) + 1);
            }else{
                map.put(l.getNum() , 1);
            }
        }
        try {
            FileTool.SaveListToFile(ls, ofile, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Map.Entry<Integer , Integer> en : map.entrySet()){
//            System.out.println(en.getKey() + "\t" + en.getValue()); //输出 航班号+该航班被分割成了多少区间
        }
    }

    public ArrayList<Rtra> getRTrajectory( int min  , int radius){
        rtra.setParameter(min, radius);
        for(Map.Entry<Integer, ArrayList<Line>> en : cluster.entrySet()){
            ArrayList<Line> ctra = new ArrayList<Line>();
            ctra.addAll(en.getValue());
            rtra.setCluster(ctra);
            rtra.getRTra();
            rtra.clearData();
        }
        return rtra.getRTrajectory();
    }
    public boolean isBZEOR(ArrayList<Line> list){
        try{
            for(Line l : list){
                TrackPointItems p1 = l.getS();
                TrackPointItems p2 = l.getE();
                if(p1.getLongitudeMultiple() < 0 || p1.getLatitudeMultiple() < 0){
                    return false;
                }
                if(p2.getLongitudeMultiple() < 0 || p2.getLatitudeMultiple() < 0){
                    return false;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
    public void saveRtrajectory(String filepath){
        ArrayList<Rtra> rTrajectory  = rtra.getRTrajectory();
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0 ; i < rTrajectory.size() ; i++){
            Rtra rt = rTrajectory.get(i);
            for(TrackPointItems p : rt.getPoints()){
                list.add(i + "\t" + p.toString());
            }
        }
        try {
            FileTool.SaveListToFile(list, filepath, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void overWriteLines(String ifile , int p1x , int p1y , int p2x , int p2y){
        try{
            lines.clear();
            ArrayList<String> list = FileTool.LoadListFromFile(ifile, 0, Charset.forName("utf-8"));
            for(String line : list) {
                String [] array = line.split("\t");
                TrackPointItems s = new TrackPointItems(Double.parseDouble(array[p1x]) , Double.parseDouble(array[p1y]));
                TrackPointItems e = new TrackPointItems(Double.parseDouble(array[p2x]) , Double.parseDouble(array[p2y]));
                Line l = new Line(s, e);
                l.setNum(parseInt(array[1]));
                l.setOrder(parseInt(array[0]));
                lines.add(l);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //计算每个簇的基数，若其值小于阈值，则算法将该簇淘汰，因为其不够密集。
    public void check_tra_num(int n){
        HashMap<Integer , ArrayList<Line>> tempCluster = new HashMap<Integer, ArrayList<Line>>(cluster);
        for(Map.Entry<Integer , ArrayList<Line>> en : tempCluster.entrySet()){
            HashSet<Integer> set = new HashSet<Integer>();
            for(Line l : en.getValue()){
                set.add(l.getNum());
            }
            if(set.size() < n){
                cluster.remove(en.getKey());
            }
        }
    }
    private cn.nocml.Pair<Double , Integer> calclateParameter(){
        int n = lines.size();
        int sigma = 0;
        double prob = 0.0;
        for(int i = 0 ; i < n ; i++){
//			if( lines.get(i).getCluster().size() == 0)
//				System.out.println("pause");
            sigma += lines.get(i).getCluster().size() + 1;
        }
        for(int i = 0 ; i < n ;i++){
            double nx = lines.get(i).getCluster().size() + 1;
            double px = nx / sigma;
            prob +=(px * MathTool.log(px, 2));
        }
        prob = -1 * prob;
        int avg = sigma / n;
        return new Pair<Double , Integer>(prob , avg);
    }


    public  HashMap<Integer, Flight> getMapFromMongDB(){
        ArrayList<Flight> aa;//原纪录经纬度坐标数组
        Query query = new Query(Criteria.where("planeId").exists(true));
        System.out.println("开始加载数据");
        long startTime = System.currentTimeMillis();
        aa = (ArrayList<Flight>) mongoTemplate.find(query, Flight.class);//从原始数据文件中获取转换后的经纬度坐标点数据，存放到ArrayList数组中
        long endTime = System.currentTimeMillis();
        long usedTime = (endTime-startTime)/1000;
        System.out.println("成功加载数据"+usedTime+"秒");
        for (int i = 0; i <aa.size(); i++){
            for (int j =0; j<aa.get(i).getPoints().size(); j++){
                aa.get(i).getPoints().get(j).setNum(parseInt(aa.get(i).getPlaneId()));
                aa.get(i).getPoints().get(j).setLatitudeMultiple(aa.get(i).getPoints().get(j).getLatit()*600);
                aa.get(i).getPoints().get(j).setLongitudeMultiple(aa.get(i).getPoints().get(j).getLongit()*600);
            }
        }
        for (int i = 0; i <aa.size(); i++) {
            trajectorys.put(Integer.valueOf(aa.get(i).getPlaneId()),aa.get(i));
        }

//        trajectorys.put(1,mongoTemplate.find(query, Flight.class));
        return  trajectorys;
    }





    @RequestMapping(value = "/tt", method = RequestMethod.GET)
    @ResponseBody
    public void main(String[] args) {
        try{


            System.out.println("程序开始,开始计时");
            long startTime = System.currentTimeMillis();

            TraCluster traClus = new TraCluster();
            String root = System.getProperty("user.dir") + "/data/";
//			String filename = "data.motion.txt";
//			traClus.loadTrajectory(root + filename);
//			String filename = "Copydeer95.txt";
//            String filename = "test.txt";
//            traClus.loadPoints(root + filename);
            Draw draw = new Draw();


            HashMap<Integer , Flight> trajectorys = new HashMap<Integer, Flight>();
            System.out.println("连接数据库");
            traClus.trajectorys = getMapFromMongDB();
//            System.out.println(trajectorys);
//            aa = (ArrayList<Flight>) mongoTemplate.find(query,Flight.class);//从原始数据文件中获取转换后的经纬度坐标点数据，存放到ArrayList数组中
//            System.out.println("数据获取成功");
            traClus.setParameter(20, 30);
            traClus.setMDL_COST_ADVANTAGE(0);
            traClus.sortLine();
//			测试读取速度
//            int tmp = 0;
//            for (Map.Entry<Integer, Flight> en : traClus.trajectorys.entrySet()){
//                tmp = tmp + en.getValue().getPoints().size();
//            }
//            long endTime = System.currentTimeMillis();
//            long usedTime = (endTime-startTime)/1000;
//            System.out.println("读取速度测试时运行时间"+usedTime+"秒");

            //功能
            //分段
            long endTime11 = System.currentTimeMillis();
            long usedTime11 = (endTime11-startTime)/1000;
            System.out.println("开始分段"+usedTime11+"秒");
            long startTimepar = System.currentTimeMillis();
            traClus.partition(); //分段，MDL算法
            long endTimepar = System.currentTimeMillis();
            long usedTimepar = (endTimepar-startTimepar)/1000;
            System.out.println("partition阶段运行时间"+usedTimepar+"秒");
            traClus.ouputLines(root + "ls_my.txt"); //输出分割后的线段

            //平飞段
            long startTimeNT = System.currentTimeMillis();
            traClus.getNoTurnLines(); //转向判断，保留平飞段
            long endTimeNT = System.currentTimeMillis();
            long usedTimeNT = (endTimeNT-startTimeNT)/1000;
            System.out.println("平飞段计算 运行时间"+usedTimeNT+"秒");

            System.out.println("录入PartitionLevelLines");
            mongoTemplate.insert(traClus.lines,"PartitionLevelLines");
            System.out.println("录入PartitionLevelLines结束");
            long endTimeNT0 = System.currentTimeMillis();
            long usedTimeNT0 = (endTimeNT0-startTimeNT)/1000;
            System.out.println("平飞段计算+平飞段录入 运行时间"+usedTimeNT0+"秒");


            //聚类
            long startTimeclus = System.currentTimeMillis();
            traClus.cluster(); //聚类，traclus算法，获得中心轨迹
//            traClus.outputCluster(root + "cluster_my.txt"); //输出聚类后的线段
            traClus.check_tra_num(5); //计算每个簇的基数，若其值小于阈值，则算法将该簇淘汰，因为其不够密集。
//            traClus.outputCluster(root +"cluster_my_check.txt"); //输出最终聚类结果
            long endTimeclus = System.currentTimeMillis();
            long usedTimeclus = (endTimeclus-startTimeclus)/1000;
            System.out.println("聚类计算 运行时间"+usedTimeclus+"秒");

            System.out.println("录入Cluster");
//            mongoTemplate.insert(traClus.cluster,"Cluster");
            mongoTemplate.insert(traClus.cluster.entrySet(),"Cluster");
            System.out.println("录入Cluster结束");
            long endTimeclus0 = System.currentTimeMillis();
            long usedTimeclus0 = (endTimeclus0-startTimeclus)/1000;
            System.out.println("聚类计算+聚类录入时间 运行时间"+usedTimeclus0+"秒");



            long startTimeCentra = System.currentTimeMillis();
            ArrayList<Rtra> rTrajectory = traClus.getRTrajectory(1,25);
            long endTimeCentra = System.currentTimeMillis();
            long usedTimeCentra = (endTimeCentra-startTimeCentra)/1000;
            System.out.println("中心段计算 运行时间"+usedTimeCentra+"秒");
//            traClus.saveRtrajectory(root + "RTra.txt");


            System.out.println("录入CentreTra");
            mongoTemplate.insert(rTrajectory,CentreTra.class);
            System.out.println("录入CentreTra结束");
            long endTimeCentra0 = System.currentTimeMillis();
            long usedTimeCentra0 = (endTimeCentra0-startTimeCentra)/1000;
            System.out.println("中心段计算+录入 运行时间"+usedTimeCentra0+"秒");

            long endTime2 = System.currentTimeMillis();
            long usedTime2 = (endTime2-startTime)/1000;
            System.out.println("总程序运行时间"+usedTime2+"秒");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

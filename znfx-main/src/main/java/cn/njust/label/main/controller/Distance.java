package cn.njust.label.main.controller;


import java.util.List;

import cn.njust.label.main.dto.TrackPointItems;
import cn.njust.label.main.dto.Line;

public class Distance {

    /**
     * @description 两个点之间的欧式距离
     * @param p1 第一个点
     * @param p2 第二个点
     * @return 欧式距离
     */
    public static double distance(TrackPointItems p1 , TrackPointItems p2){
        double x = Math.abs(p1.getLongitudeMultiple() - p2.getLongitudeMultiple());;
        double y = Math.abs(p1.getLatitudeMultiple() - p2.getLatitudeMultiple());
        return Math.sqrt(x * x  + y * y);
    }
    /**
     * @description 计算线段的长度
     * @param l 线段
     * @return 线段的长度
     */
    public static double distance(Line l){
        double x = l.getE().getLongitudeMultiple() - l.getS().getLongitudeMultiple();
        double y = l.getE().getLatitudeMultiple() - l.getS().getLatitudeMultiple();
        return Math.sqrt(x * x  + y * y);
    }
    /**
     * @description 计算两条线段之间的垂直距离
     * @param l1 第一条线段
     * @param l2 第二条线段
     * @return 线段之间的垂直距离
     */
    public static double distance_perpen(Line l1 , Line l2){
        double dxy1 = distance(l1.getS(), l2.getS());
        double cos1 = cos_angle(l2.getS(), l1.getS() , l2.getS() , l2.getE());
        double dy1 = dxy1 * Math.sqrt(1 - cos1 * cos1);

        double dxy2 = distance(l1.getE(), l2.getE());
        double cos2 = cos_angle(l2.getE(), l1.getE() , l2.getE() , l2.getS());
        double dy2 = dxy2 * Math.sqrt(1 -cos2 * cos2);
        System.out.printf("cos1%f , cos2%f \n",cos1 , cos2);
        if(dy1 == 0 && dy2 == 0)
            return 0;
        else
            return Math.sqrt(dy1 * dy1 + dy2 * dy2) / (dy1 + dy2);
    }

    /**
     * @description 轨迹划分时的 最小描述长度
     * @param trackPointItems 轨迹集合
     * @param start 开始点下标（包含）
     * @param end 结束点下标（包含）
     * @return
     */
    public static double distance_mdl_par(List<TrackPointItems> trackPointItems, int start , int end){
        double LH = Math.log10(distance(trackPointItems.get(start) , trackPointItems.get(end))) / Math.log10(2);
        double LDH = 0;
        Line l = new Line(trackPointItems.get(start), trackPointItems.get(end));
        double angleTemp = 0;
        double perpenTemp = 0;
        for(int i = start ; i < end ; i++){
            int next = i + 1;
            Line lTemp = new Line(trackPointItems.get(i), trackPointItems.get(next));
            if (distance(l) >= distance(lTemp)) {
                angleTemp += distance_angle(l, lTemp);
                perpenTemp += distance_perpen2(l, lTemp);
            }else{
                angleTemp += distance_angle(lTemp, l);
                perpenTemp += distance_perpen2(lTemp, l);
            }
        }
        if(angleTemp == 0 || perpenTemp == 0){
            LDH = 0;
        }else{
            LDH=(Math.log10(perpenTemp)+Math.log10(angleTemp))/Math.log10(2);
        }

        return LH + LDH;
    }
    //计算轨迹总长度
    public static double distance_mdl_nopar(List<TrackPointItems> trackPointItems, int start , int end){

        double LHTemp = 0.0;
        for(int i = start ; i < end ; i++){
            LHTemp += distance(trackPointItems.get(i) , trackPointItems.get(i+1));
        }
        return Math.log10(LHTemp)/Math.log10(2);
    }

    /**
     * @description 求两条线段间的夹角余弦
     * @param l2 较长的线段
     * @param l1 较短的线段
     * @return 夹角余弦
     */
    public static  double distance_angle(Line l1 , Line l2){

        double len = distance(l2);

        double cos = cos_angle(l1, l2);

        double sin = Math.sqrt(1 - cos * cos);

        double angle = len * sin;

        return angle;
    }
    /**
     * @description 计算两条线段之间的夹角余弦
     * @param si 线段i的起点
     * @param ei 线段i的终点
     * @param sj 线段j的起点
     * @param ej 线段j的终点
     * @return
     */
    static double cos_angle(TrackPointItems si, TrackPointItems ei, TrackPointItems sj, TrackPointItems ej){
        //把两条线段平移到原点（线段开始、结束均减去开始点的坐标）
        double x1=ej.getLongitudeMultiple()-sj.getLongitudeMultiple();
        double y1=ej.getLatitudeMultiple()-sj.getLatitudeMultiple();

        double x2=ei.getLongitudeMultiple()-si.getLongitudeMultiple();
        double y2=ei.getLatitudeMultiple()-si.getLatitudeMultiple();

        double xi=Math.sqrt(Math.pow(x1,2)+Math.pow(y1,2));
        double yi=Math.sqrt(Math.pow(x2,2)+Math.pow(y2,2));
        if(xi == 0 || yi ==0){
            return 0;
        }
        double cos=(x1*x2+y1*y2)/(xi*yi);
        return cos;
    }

    /**
     * @description 计算两条线段之间的夹角余弦
     * @param l1 线段1
     * @param l2 线段2
     * @return 夹角余弦
     */
    static double  cos_angle(Line l1 , Line l2){
        return cos_angle(l1.getS() , l1.getE(), l2.getS() , l2.getE());
    }
    public static double caculate_angle(TrackPointItems si, TrackPointItems ei, TrackPointItems sj, TrackPointItems ej)
    {
        double x1=ej.getLongitudeMultiple()-sj.getLongitudeMultiple();
        double y1=ej.getLatitudeMultiple()-sj.getLatitudeMultiple();
        double cos = cos_angle(si, ei, sj, ej);
        float sin=(float) Math.sqrt(1-cos * cos);
        double angle=Math.sqrt(Math.pow(x1,2)+Math.pow(y1,2))*sin;
        return angle;

    }

    /**
     * @description 计算两条线段之间的垂直距离
     * @param l1 较长的线段
     * @param l2 较短的线段
     * @return 线段之间的垂直距离
     */
    public static double distance_perpen2(Line l1 , Line l2){
        return caculate_perpen(l1.getS(), l1.getE(), l2.getS(), l2.getE());
    }

    public static double caculate_perpen(TrackPointItems si, TrackPointItems ei, TrackPointItems sj, TrackPointItems ej)
    {

        double x1=sj.getLongitudeMultiple()-si.getLongitudeMultiple();
        double y1=sj.getLatitudeMultiple()-si.getLatitudeMultiple();

        double x2=ei.getLongitudeMultiple()-si.getLongitudeMultiple();
        double y2=ei.getLatitudeMultiple()-si.getLatitudeMultiple();

        double x3=ej.getLongitudeMultiple()-si.getLongitudeMultiple();
        double y3=ej.getLatitudeMultiple()-si.getLatitudeMultiple();

        double x=Math.pow(x2,2)+Math.pow(y2,2);

        double u1=(x1*x2+y1*y2)/x;
        double u2=(x3*x2+y3*y2)/x;

        double psx=si.getLongitudeMultiple()+u1*x2;
        double psy=si.getLatitudeMultiple()+u1*y2;

        double pex=si.getLongitudeMultiple()+u2*x2;
        double pey=si.getLatitudeMultiple()+u2*y2;

        double Lper1=Math.sqrt(Math.pow(psx-sj.getLongitudeMultiple(),2)+Math.pow(psy-sj.getLatitudeMultiple(),2));
        double Lper2=Math.sqrt(Math.pow(pex-ej.getLongitudeMultiple(),2)+Math.pow(pey-ej.getLatitudeMultiple(),2));
        double d_perpen;

        if(Lper1==0&&Lper2==0)
        {
            d_perpen=0;
        }
        else
        {
            d_perpen=(Math.pow(Lper1,2)+Math.pow(Lper2,2))/(Lper1+Lper2);
        }

        return d_perpen;

    }
    public static double dist(Line lp , Line tp)
    {

        TrackPointItems si;
        TrackPointItems ei;
        TrackPointItems sj;
        TrackPointItems ej;

        si=lp.getS();
        ei=lp.getE();
        sj=tp.getS();
        ej=tp.getE();

        double x1=sj.getLongitudeMultiple()-si.getLongitudeMultiple();
        double y1=sj.getLatitudeMultiple()-si.getLatitudeMultiple();

        double x2=ei.getLongitudeMultiple()-si.getLongitudeMultiple();
        double y2=ei.getLatitudeMultiple()-si.getLatitudeMultiple();

        double x3=ej.getLongitudeMultiple()-si.getLongitudeMultiple();
        double y3=ej.getLatitudeMultiple()-si.getLatitudeMultiple();

        double x=Math.pow(x2,2)+Math.pow(y2,2);

        double u1=(x1*x2+y1*y2)/x;
        double u2=(x3*x2+y3*y2)/x;

        double psx=si.getLongitudeMultiple()+u1*x2;
        double psy=si.getLatitudeMultiple()+u1*y2;

        double pex=si.getLongitudeMultiple()+u2*x2;
        double pey=si.getLatitudeMultiple()+u2*y2;

        double Lper1=Math.sqrt(Math.pow(psx-sj.getLongitudeMultiple(),2)+Math.pow(psy-sj.getLatitudeMultiple(),2));
        double Lper2=Math.sqrt(Math.pow(pex-ej.getLongitudeMultiple(),2)+Math.pow(pey-ej.getLatitudeMultiple(),2));
        double d_perpen;

        if(Lper1==0&&Lper2==0)
        {
            d_perpen=0;
        }
        else
        {
            d_perpen=(Math.pow(Lper1,2)+Math.pow(Lper2,2))/(Lper1+Lper2);
        }

        double x4=ej.getLongitudeMultiple()-sj.getLongitudeMultiple();
        double y4=ej.getLatitudeMultiple()-sj.getLatitudeMultiple();

        double xi=Math.sqrt(Math.pow(x4,2)+Math.pow(y4,2));
        double yi=Math.sqrt(Math.pow(x2,2)+Math.pow(y2,2));

        double cos=(x4*x2+y4*y2)/(xi*yi);
        if(xi == 0 || yi == 0)
            cos = 0;
        double t=Math.sqrt(1 - cos * cos);

        double angle=(Math.sqrt(Math.pow(x4,2)+Math.pow(y4,2)))*t;

        double Lpar1=Math.sqrt(Math.pow(psx-si.getLongitudeMultiple(),2)+Math.pow(psy-si.getLatitudeMultiple(),2));
        double Lpar2=Math.sqrt(Math.pow(pex-ei.getLongitudeMultiple(),2)+Math.pow(pey-ei.getLatitudeMultiple(),2));

        double d_parallel;

        if(Lpar1>Lpar2)
            d_parallel=Lpar2;
        else
            d_parallel=Lpar1;
        return (d_parallel+d_perpen+angle);
    }



//	public static double dist(Line l1 , Line l2){
//		double angle = 0.0;
//		double minPerpen = 0.0;
//		double parallel = 0.0;
//		if(distance(l1) > distance(l2)){
//			angle = distance_angle(l1, l2);
//			parallel = distance_perpen2(l1, l2);
//		}else{
//			angle = dist(l2, l1);
//			parallel = distance_perpen2(l2, l1);
//		}
//	}

}

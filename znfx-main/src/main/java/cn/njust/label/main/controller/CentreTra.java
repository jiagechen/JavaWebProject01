package cn.njust.label.main.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cn.njust.label.main.dto.*;

//计算聚类后的中心轨迹。
public class CentreTra {
    private int minlines = 1;
    private double radius = 0.0;
    private double sinv ;
    private double cosv ;
    private ArrayList<TrackPointItems> trackPointItems = new ArrayList<TrackPointItems>();
    private ArrayList<Line> tracluster = new ArrayList<Line>();
    private ArrayList<Rtra> rtrajectory = new ArrayList<Rtra>();
    public void setParameter(int minlines , double radius){
        this.minlines = minlines;
        this.radius = radius;
    }
    public void sortPoints(){
        try{
            Collections.sort(trackPointItems, new Comparator<TrackPointItems>(){
                @Override
                public int compare(TrackPointItems p1, TrackPointItems p2) {
                    if( p1.getLongitudeMultiple() >  p2.getLongitudeMultiple())
                        return 1;
                    if( p1.getLongitudeMultiple() < p2.getLongitudeMultiple())
                        return -1;
                    return 0;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sortLine(){
        try{
            Collections.sort(tracluster, new Comparator<Line>(){
                @Override
                public int compare(Line l1, Line l2) {
                    double v1 = getMinX(l1.getS(), l1.getE());
                    double v2 = getMinX(l2.getS(), l2.getE());
                    if(v1 > v2)
                        return 1;
                    if(v1 < v2)
                        return -1;
                    return 0;
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private double getMinX(TrackPointItems p1 , TrackPointItems p2){
        return Math.min(p1.getLongitudeMultiple(), p2.getLongitudeMultiple());
    }
    public ArrayList<Line> getCluster() {
        return tracluster;
    }
    public void setCluster(ArrayList<Line> cluster) {
        this.tracluster = new ArrayList<Line>(cluster);
    }
    public void clearData(){
        this.tracluster.clear();
        this.trackPointItems.clear();
    }

    public void getRTra(){
        compute_avgv();
        rotate_axes();
        sweepline();
    }
    public ArrayList<Rtra> getRTrajectory(){
        return this.rtrajectory;
    }
    /**
     * @description 计算所有线段的平均夹角
     */
    private void compute_avgv(){
        //所有线段的平均x轴长度
        double px=0;
        //所有线段的平均y轴长度
        double py=0;
        for (Line l : tracluster) {
            if (l.getS().getItemid() > l.getE().getItemid()) {
                px += (l.getS().getLongitudeMultiple() - l.getE().getLongitudeMultiple());
                py += (l.getS().getLatitudeMultiple() - l.getE().getLatitudeMultiple());;
            } else {
                px += (l.getE().getLongitudeMultiple() - l.getS().getLongitudeMultiple());
                py += (l.getE().getLatitudeMultiple() - l.getS().getLatitudeMultiple());;
            }
        }
        px /= tracluster.size();
        py /= tracluster.size();

        //平均线段长度
        double l=Math.sqrt(px*px+py*py);
        //cos夹角
        cosv=px/l;
        //sin夹角
        sinv=py/l;
    }
    /**
     * @description 旋转坐标系（点不动，坐标系动）
     */
    private void rotate_axes(){
        for(int i = 0 ; i < tracluster.size() ; i++){
            Line l = tracluster.get(i);
            TrackPointItems s = l.getS();
            TrackPointItems stemp = l.getS();
            stemp.setLongitudeMultiple(s.getLongitudeMultiple() * cosv + s.getLatitudeMultiple() * sinv);
            stemp.setLatitudeMultiple(-s.getLongitudeMultiple() * sinv + s.getLatitudeMultiple() * cosv );

            trackPointItems.add(stemp);
            TrackPointItems e = l.getE();
            TrackPointItems etemp = l.getE();
            etemp.setLongitudeMultiple(e.getLongitudeMultiple() * cosv + e.getLatitudeMultiple() * sinv);
            etemp.setLatitudeMultiple(-e.getLongitudeMultiple() * sinv + e.getLatitudeMultiple() * cosv );
            trackPointItems.add(etemp);

            Line save = new Line(stemp, etemp);
            tracluster.remove(i);
            tracluster.add(i, save);

            //原算法（只保留了扩大后的经纬度，丢失原经纬度和航班信息和角度信息）
//            Line l = tracluster.get(i);
//            TrackPointItems s = l.getS();
//            TrackPointItems stemp = new TrackPointItems();
//            stemp.setLongitudeMultiple(s.getLongitudeMultiple() * cosv + s.getLatitudeMultiple() * sinv);
//            stemp.setLatitudeMultiple(-s.getLongitudeMultiple() * sinv + s.getLatitudeMultiple() * cosv );
//
//            trackPointItems.add(stemp);
//            TrackPointItems e = l.getE();
//            TrackPointItems etemp = new TrackPointItems();
//            etemp.setLongitudeMultiple(e.getLongitudeMultiple() * cosv + e.getLatitudeMultiple() * sinv);
//            etemp.setLatitudeMultiple(-e.getLongitudeMultiple() * sinv + e.getLatitudeMultiple() * cosv );
//            trackPointItems.add(etemp);
//
//            Line save = new Line(stemp, etemp);
//            tracluster.remove(i);
//            tracluster.add(i, save);
        }
    }

    private void sweepline(){
        sortPoints();
        sortLine();
        Rtra tra = new Rtra();
        double preX = 0.0;
        boolean mark = false;
        for(int i = 0; i < trackPointItems.size() ; i++){
            TrackPointItems p = trackPointItems.get(i);
            CalcBean neighbor = getNeighbor(p);
            if(!mark && neighbor.getCount() > minlines){
                double avgy = neighbor.getSumy() / neighbor.getCount();
                preX = p.getLongitudeMultiple();
                //取旋转前的坐标
                p.setLongitudeMultiple(p.getLongitudeMultiple() * cosv-avgy*sinv);
                p.setLatitudeMultiple((p.getLongitudeMultiple() + avgy*sinv * cosv-p.getLongitudeMultiple() *cosv*cosv)/sinv);
                tra.getPoints().add(p);
//                tra.insert(new TrackPointItems(x , y));

                mark = true;
            }
            else if(neighbor.getCount() > minlines && mark){
                if(p.getLongitudeMultiple() - preX >= radius){
                    double avgy = neighbor.getSumy() / neighbor.getCount();
                    preX = p.getLongitudeMultiple();
                    //取旋转前的坐标
                    p.setLongitudeMultiple(p.getLongitudeMultiple() * cosv-avgy*sinv);
                    p.setLatitudeMultiple((p.getLongitudeMultiple() + avgy*sinv * cosv-p.getLongitudeMultiple() *cosv*cosv)/sinv);
                    tra.insert(p);

                }
            }
        }
        this.rtrajectory.add(tra);
    }

    private CalcBean getNeighbor(TrackPointItems p){
        CalcBean bean = new CalcBean();
        for(int i = 0 ; i < tracluster.size() ; i++){
            Line l = tracluster.get(i);
            double max = l.getS().getLongitudeMultiple();
            double min = l.getE().getLongitudeMultiple();
            if(max < min){
                double temp = max ;
                max = min;
                min = temp;
            }
            if(min > p.getLongitudeMultiple())
                break;
            if(min < p.getLongitudeMultiple() && max > p.getLongitudeMultiple()){
                bean.incrementCount();
                bean.incrementSumY( getY(l , p));
            }
        }
        return bean;
    }
    private double getY(Line l , TrackPointItems p){
        double disX = (l.getS().getLongitudeMultiple() - l.getE().getLongitudeMultiple());
        if(disX == 0){
            return (l.getE().getLatitudeMultiple() - l.getS().getLatitudeMultiple()) / 2;
        }
        //斜率
        double k = (l.getS().getLatitudeMultiple() - l.getE().getLatitudeMultiple()) / disX;
        return (l.getS().getLatitudeMultiple() + k * (p.getLongitudeMultiple() - l.getS().getLongitudeMultiple()));
    }
}

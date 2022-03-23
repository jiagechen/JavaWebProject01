package cn.njust.label.main.dto;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.stereotype.Service;

@Service
@Data

public class TrackPointItems implements Comparable<TrackPointItems>{
    @Field("itemid")
    private Integer itemid;
    @Field("heading")
    private String heading;
    @Field("height")
    private String height;
    @Field("latitude")
    private double latit;//维度
    @Field("longitude")
    private double longit;
    @Field("speed")
    private String speed;
    @Field("timeStamp")
    private String timeStamp;


    private double latitudeMultiple;
    private double longitudeMultiple;


    public double angle;//方位角:将经线至北向南方向作为基准方向，基准方向规定为。区间起点到终点方向作为该段区间航行方向，基准方向与航行方向的夹角定义区间夹角，
    public int ifturn;

//    int order = itemid-1 ;
    //点的序号（所属轨迹的序号）
    int num ;


    public TrackPointItems(double x , double y){
        this.longitudeMultiple = x;
        this.latitudeMultiple = y;
    }

//    int getOrder(){
//        return itemid;
//    }
    public TrackPointItems(){}//空构造函数
    @Override
    public int compareTo(TrackPointItems other) {
        return Integer.compare(this.itemid, other.itemid);
    }
}

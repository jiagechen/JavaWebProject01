package cn.njust.label.main.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;




@Data
//@Document(collection = "TinyTinyTest")  //2MB  分割前lines 49291  分割后lines 17336 partition分割时间5秒  总程序运行时间42秒
//@Document(collection = "TinyTest")  //15MB  分割前lines 351734  分割后lines 120972 partition分割时间46秒  总程序运行时间1904秒
//@Document(collection = "HalfTest") //分割前lines 974200 分割后lines 507191
//@Document(collection = "SameDataTest") // 在wolai上
@Document(collection = "CentreTra") //

//@Document(collection = "rds") // 分割前lines 2176783  分割后lines 1317544 partition分割时间168秒

public class Rtra {

    // private static final long serialVersionUID = -70067266044697552L;
    @Id
    private String Id;
    @Field("plane_id")
    private String planeId;
    @Field("track_point_items")
//    private ArrayList<TrackPointItems> trackPointItems;
    ArrayList<TrackPointItems> trackPointItems = new ArrayList<TrackPointItems>();



    public Rtra(){}
    public Rtra(ArrayList<TrackPointItems> trackPointItems) {
        this.trackPointItems.addAll(trackPointItems);
    }
    public void insert(TrackPointItems trackPointItems){
        this.trackPointItems.add(trackPointItems);
    }
    public TrackPointItems get(int index){
        if(index < trackPointItems.size() - 1)
            return trackPointItems.get(index);
        return null;
    }
    public ArrayList<TrackPointItems> getPoints() {
        return trackPointItems;
    }
    public void setPoints(ArrayList<TrackPointItems> trackPointItems) {
        this.trackPointItems = trackPointItems;
    }



}

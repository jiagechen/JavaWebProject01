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
//@Document(collection = "rds") // 分割前lines 2176783  分割后lines 1317544 partition分割时间168秒

//@Document(collection = "SameDataTest") //本地mongodb

//@Document(collection = "二次雷达航迹_内部") // 已录入    centreTra 累计7文档
//@Document(collection = "rds_2021-03-16_15-22-55.dat_stmsADSB航迹")     //centreTra 累计20文档
//@Document(collection = "subset_5")  // 总程序运行时间1975秒 30分钟  累计34文档  分割前lines 6111569；分割后lines 181029；平飞段的段数37085
//@Document(collection = "subset_8") //    累计68文档
@Document(collection = "subset_6")
public class Flight {

 // private static final long serialVersionUID = -70067266044697552L;
    @Id
    private String Id;
    @Field("plane_id")  //注意大小写
    private String planeId;
    @Field("Track_point_items") //注意大小写
    private ArrayList<TrackPointItems> trackPointItems;



    public Flight(){}
    public Flight(ArrayList<TrackPointItems> trackPointItems) {
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

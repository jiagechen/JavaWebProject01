package cn.njust.label.main;

import cn.njust.label.main.dto.Flight;
import cn.njust.label.main.dto.TrackPointItems;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;


@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
@SpringBootTest


//@CrossOrigin
//@RestController
//@RequestMapping("/mongorestore")
//@EnableAutoConfiguration(exclude= MongoAutoConfiguration.class)

public class ExactTest<pGPSArrayInit, ENPoint> {

    @Autowired
    public MongoTemplate mongoTemplate;
    public ArrayList<TrackPointItems> pGPSArrayInit= new ArrayList<>();//原纪录经纬度坐标数组
    public ArrayList<TrackPointItems> pGPSArrayFilter = new ArrayList<>();//过滤后的经纬度坐标数组
    public ArrayList<TrackPointItems> pGPSArrayFilterLevel = new ArrayList<>();//过滤后平飞段的经纬度坐标数组
    public ArrayList<TrackPointItems> pGPSArrayFilterTurn = new ArrayList<>();//过滤后平飞段的经纬度坐标数组

    @Test
    public void test(){
        String planeId = "7454504";
        HashMap<Integer , Flight> trajectorys = new HashMap<Integer, Flight>();
        trajectorys = getMapFromMongDB();
        pGPSArrayInit = getENPointFromMongDB(planeId);
        System.out.println("原始经纬度点坐标的个数:"+pGPSArrayInit.size());//输出原始经纬度点坐标的个数
    }




    public HashMap<Integer, Flight> getMapFromMongDB(){
        HashMap<Integer , Flight> trajectorys = new HashMap<Integer, Flight>();
        ArrayList<Flight> aa;//原纪录经纬度坐标数组
        Query query = new Query(Criteria.where("planeId").exists(true));
        aa = (ArrayList<Flight>) mongoTemplate.find(query, Flight.class);//从原始数据文件中获取转换后的经纬度坐标点数据，存放到ArrayList数组中
        for (int i = 0; i <aa.size(); i++) {
            trajectorys.put(Integer.valueOf(aa.get(i).getPlaneId()),aa.get(i));
        }

//        trajectorys.put(1,mongoTemplate.find(query, Flight.class));
        return  trajectorys;
    }

    public ArrayList<TrackPointItems> getENPointFromMongDB(String planeId){
        ArrayList<Flight> aa;//原纪录经纬度坐标数组
        Query query = new Query(Criteria.where("planeId").is(planeId));
        aa = (ArrayList<Flight>) mongoTemplate.find(query, Flight.class);//从原始数据文件中获取转换后的经纬度坐标点数据，存放到ArrayList数组中

        ArrayList<TrackPointItems> pGPSArrayInit = aa.get(0).getTrackPointItems();

        return  pGPSArrayInit; //我真正要用的，没错啦！！！
    }

}

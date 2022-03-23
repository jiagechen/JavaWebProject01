package cn.njust.label.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cn.njust.label.main.dto.Flight;

import java.util.List;

@RestController
@RequestMapping("/test")
public class MongoTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("/mongodbtest")
    @ResponseBody
    public String Mongodbtest() {
        Query query = new Query(Criteria.where("planeId").is("7866659"));
        List<Flight> test = mongoTemplate.find(query, Flight.class);
//        System.out.println(test);
        return "ok";
    }

}

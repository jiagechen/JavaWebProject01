package cn.njust.label.main.controller;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.io.*;
import java.util.ArrayList;

public class import_into_lbr_mongo {
    private static String mongo_server_address = "localhost";
    private static int mongo_server_port = 27017;
    static MongoClient my_client = MongoClients.create("mongodb://localhost:27017");
//    private static MongoClient my_client = new MongoClient(mongo_server_address, mongo_server_port);
    private static MongoDatabase my_db = my_client.getDatabase("Trajectory");

    private static String classification_path = "data\\classification";
    private static File classification_file = new File(classification_path);
    private static FileReader fr = null;
    private static BufferedReader br = null;

    public static void main(String[] args){
        // System.out.println(my_db);
        File[] classification_lists = classification_file.listFiles();
        System.out.println(classification_file);
        for(File each_classification_file: classification_lists){
            String cur_collection_name = each_classification_file.getName();
            // System.out.println(cur_collection);
            my_db.createCollection(cur_collection_name);
            MongoCollection cur_collection = my_db.getCollection(cur_collection_name);
            
            File[] flights = each_classification_file.listFiles();
            try{
                for(File each_flight: flights){
                    ArrayList<String> items = new ArrayList<>();
                    fr = new FileReader(each_flight);
                    br = new BufferedReader(fr);
                    // 按行读取字符串
                    String str;
                    while ((str = br.readLine()) != null) {
                        items.add(str);
                    }
                    br.close();
                    fr.close();

                    Document track_storage_table = new Document();
                    ArrayList<Document> track_point_items = new ArrayList<>();

                    int dot_index = each_flight.getName().lastIndexOf(".txt");
                    track_storage_table.append("plane_id", each_flight.getName().substring(0, dot_index));
                    
                    for(int i = 1; i < items.size(); ++i){
                        String[] cols = items.get(i).split("\t");

                        Document track_point_item = new Document();
                        track_point_item.append("itemid", i);
//                        track_point_item.append("longitude",1200*Double.parseDouble(cols[2]));
//                        track_point_item.append("latitude", 1200*Double.parseDouble(cols[3]));
//                          track_point_item.append("longitude",Double.parseDouble(cols[2]));
//                          track_point_item.append("latitude", Double.parseDouble(cols[3]));
                        track_point_item.append("longitude",String.valueOf(Double.parseDouble(cols[2])));
                        track_point_item.append("latitude", String.valueOf(Double.parseDouble(cols[3])));
//                        track_point_item.append("longitude",cols[2]);
//                        track_point_item.append("latitude", cols[3]);
                        track_point_item.append("height", cols[4]);
                        track_point_item.append("heading", null);
                        track_point_item.append("speed", null);
                        track_point_item.append("time_stamp", cols[0]);

                        track_point_items.add(track_point_item);
                    }
                    track_storage_table.append("Track_point_items", track_point_items);
                    cur_collection.insertOne(track_storage_table);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
}

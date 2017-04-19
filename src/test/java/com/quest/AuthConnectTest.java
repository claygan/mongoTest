package com.quest;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class AuthConnectTest {
	Logger logger = LoggerFactory.getLogger(AuthConnectTest.class);
	
	private static final String username = "xl";
	private static final String password = "xl";
	private static final String dbName = "xl";
	private static final String host = "192.168.20.41";
	
	public static void main(String[] args) {
		testMongoDBConn();
	}
	
	//junit无法连接mongo
	private static void testMongoDBConn() {  
	    MongoClient client = null;  
	    try {  
	        // 用户名 数据库 密码  
	        MongoCredential credential = MongoCredential.createCredential(username, dbName, password.toCharArray());  
	        //IP port  
	        ServerAddress addr = new ServerAddress(host, 27017);  
	        client = new MongoClient(addr,Arrays.asList(credential));  
	        //得到数据库  
	        MongoDatabase mdb = client.getDatabase(dbName);  
	        //得到Table  
	        MongoCollection<?> table = mdb.getCollection("mongotest_student");  
	        //查询所有  
	        FindIterable<?> fi = table.find();  
	        //遍历结果  
	        for (Object o : fi) {  
	            System.out.println(o);  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (client != null) {  
	            client.close();  
	        }  
	    }  
	}  
}

package com.quest.factorybean;

import com.mongodb.*;
import org.springframework.beans.factory.config.AbstractFactoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel
 */
public class MongoFactoryBean extends AbstractFactoryBean<Mongo> {

    // 表示服务器列表(主从复制或者分片)的字符串数组
    private String[] serverStrings;
    // mongoDB配置对象
    private MongoClientOptions mongoClientOptions;
    // 是否主从分离(读取从库)，默认读写都在主库 
    private boolean readSecondary = false;

    @Override
    public Class<?> getObjectType() {
        return Mongo.class;
    }

    @Override
    protected MongoClient createInstance() throws Exception {
        MongoClient mongo = initMongoClient();
        return mongo;
    }

    /**
     * 初始化mongo实例
     *
     * @return
     * @throws Exception
     */
    private MongoClient initMongoClient() throws Exception {
        // 根据条件创建Mongo实例
        MongoClient mongoClient = null;
        List<ServerAddress> serverList = getServerList();

        if (serverList.size() == 0) {
            mongoClient = new MongoClient();
        } else if (serverList.size() == 1) {
            if (mongoClientOptions != null) {
                mongoClient = new MongoClient(serverList.get(0), mongoClientOptions);
            } else {
                mongoClient = new MongoClient(serverList.get(0));
            }
        } else {
            if (mongoClientOptions != null) {
                mongoClient = new MongoClient(serverList, mongoClientOptions);
            } else {
                mongoClient = new MongoClient(serverList);
            }
        }
        return mongoClient;
    }


    /**
     * 根据服务器字符串列表，解析出服务器对象列表
     * <p>
     *
     * @return
     * @throws Exception
     * @Title: getServerList
     * </p>
     */
    private List<ServerAddress> getServerList() throws Exception {
        List<ServerAddress> serverList = new ArrayList<ServerAddress>();
        try {
            for (String serverString : serverStrings) {
                String[] temp = serverString.split(":");
                String host = temp[0];
                if (temp.length > 2) {
                    throw new IllegalArgumentException(
                            "Invalid server address string: " + serverString);
                }
                if (temp.length == 2) {
                    serverList.add(new ServerAddress(host, Integer
                            .parseInt(temp[1])));
                } else {
                    serverList.add(new ServerAddress(host));
                }
            }
            return serverList;
        } catch (Exception e) {
            throw new Exception("Error while converting serverString to ServerAddressList", e);
        }
    }

    public String[] getServerStrings() {
        return serverStrings;
    }

    public void setServerStrings(String[] serverStrings) {
        this.serverStrings = serverStrings;
    }

    public MongoClientOptions getMongoClientOptions() {
        return mongoClientOptions;
    }

    public void setMongoClientOptions(MongoClientOptions mongoClientOptions) {
        this.mongoClientOptions = mongoClientOptions;
    }

    public boolean isReadSecondary() {
        return readSecondary;
    }

    public void setReadSecondary(boolean readSecondary) {
        this.readSecondary = readSecondary;
    }
}
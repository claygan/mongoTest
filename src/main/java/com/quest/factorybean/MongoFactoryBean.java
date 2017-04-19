package com.quest.factorybean;

import com.mongodb.*;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Quest
 */
public class MongoFactoryBean extends AbstractFactoryBean<Mongo> {

    // 表示服务器列表(主从复制或者分片)的字符串数组
    private String[] serverStrings;
    // mongoDB配置对象
    private MongoClientOptions mongoClientOptions;
    // 是否主从分离(读取从库)，默认读写都在主库 
    private boolean readSecondary = false;
    private String dbName;    //数据库名
    private String username;    //用户名
    private String password;    //密码

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
    	
    	if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
    		throw new Exception("mongoDB连接：账号密码为空");
    	}
    	List<MongoCredential> credentials = new ArrayList<MongoCredential>();
    	MongoCredential credential = MongoCredential.createCredential(username, dbName, password.toCharArray());
    	if(credential != null){
    		credentials.add(credential);
    	}
    	
        // 根据条件创建Mongo实例
        MongoClient mongoClient = null;
        List<ServerAddress> serverList = getServerList();

        if (serverList.size() == 0) {
            mongoClient = new MongoClient();
        } else if (serverList.size() == 1) {
            if (mongoClientOptions != null) {
                mongoClient = new MongoClient(serverList.get(0), credentials, mongoClientOptions);
            } else {
                mongoClient = new MongoClient(serverList.get(0));
            }
        } else {
            if (mongoClientOptions != null) {
                mongoClient = new MongoClient(serverList, credentials, mongoClientOptions);
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

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
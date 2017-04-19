package com.quest.factorybean;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ReadPreference;

/** 
 * @ClassName: MongoClientOptionsFactoryBean
 * @Description: mongo客户端连接参数配置
 * 
 * @author ganshimin@zhongzhihui.com
 * @date: 2017年4月19日 下午2:58:57
 */  
public class MongoClientOptionsFactoryBean extends AbstractFactoryBean<MongoClientOptions>  {
	
	private int connectionsPerHost;// 每个地址最大请求数
	private int threadsAllowedToBlockForConnectionMultiplier;//一个socket最大的等待请求数
	private int connectTimeout;// 链接超时时间
	private int socketTimeout;//read数据超时时间
	private int maxWaitTime;// 长链接的最大等待时间
	private boolean socketKeepAlive;//是否保持长连接
	private String requiredReplicaSetName;//replicaSet名称

	@Override
	public Class<?> getObjectType() {
		return MongoClientOptions.class;
	}

	@Override
	protected MongoClientOptions createInstance() throws Exception {
		
		MongoClientOptions mongoClientOptions= getConfOptions();
		
		
		return mongoClientOptions;
	}
	
	
	private MongoClientOptions getConfOptions() {
		
		try {
			Builder builder = new MongoClientOptions.Builder();
			builder.connectionsPerHost(connectionsPerHost);
			builder.threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier);
			builder.socketKeepAlive(socketKeepAlive);
			builder.connectTimeout(connectTimeout);
			builder.socketTimeout(socketTimeout);
			builder.maxWaitTime(maxWaitTime);
			builder.socketKeepAlive(socketKeepAlive);
			builder.readPreference(ReadPreference.secondaryPreferred());//读偏好设置为：从secondary中读，读不出来就到primary上读
			builder.requiredReplicaSetName(requiredReplicaSetName);
			
			return builder.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getConnectionsPerHost() {
		return connectionsPerHost;
	}

	public void setConnectionsPerHost(int connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}

	public int getThreadsAllowedToBlockForConnectionMultiplier() {
		return threadsAllowedToBlockForConnectionMultiplier;
	}

	public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
		this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	public boolean isSocketKeepAlive() {
		return socketKeepAlive;
	}

	public void setSocketKeepAlive(boolean socketKeepAlive) {
		this.socketKeepAlive = socketKeepAlive;
	}

	public String getRequiredReplicaSetName() {
		return requiredReplicaSetName;
	}

	public void setRequiredReplicaSetName(String requiredReplicaSetName) {
		this.requiredReplicaSetName = requiredReplicaSetName;
	}
	
	
}

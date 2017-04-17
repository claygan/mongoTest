package com.quest.entity;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;


public abstract class BaseModel  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1317525436481469264L;
	
	@Id
	private ObjectId oid;
	
	public ObjectId getOid() {
		return oid;
	}

	public void setOid(ObjectId oid) {
		this.oid = oid;
	}
	
//	public String get_id(){
//		return oid.toStringMongod();
//	}
	
}

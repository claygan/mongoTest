package com.quest.entity;

import org.mongodb.morphia.annotations.Entity;

/** 
 * @ClassName: Student
 * @Description: 
 * @Entity实体类，name为collection的名称，noClassnameStored用来标识是否需要存储className字段来表示类名
 * @Indexes序列：
 * 
 * @author ganshimin@zhongzhihui.com
 * @date: 2017年4月17日 下午3:41:38
 */  
@Entity(value="mongotest_student",noClassnameStored=true)
public class Student extends BaseModel{
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String ADDRESS = "address";
	public static final String SCORE = "score";
	/** 
	 * @Description:TODO(用一句话描述这个变量表示什么). 
	 */  
	private static final long serialVersionUID = 8089970582557428404L;
	private String name;
	private int age;
	private String address;
	private int score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}

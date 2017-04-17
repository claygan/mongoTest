package com.quest.dao;

import javax.annotation.Resource;

import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import com.quest.entity.Student;

@Component
public class StudentDao{

	@Resource(name="datastore")
    AdvancedDatastore datastore;
	
	public void insert(Student student){
		this.datastore.insert(student);
	}
	
	public Student findStudentByName(String name){
		Query<Student> query = datastore.createQuery(Student.class);
		query.criteria(Student.NAME).containsIgnoreCase(name);
		return query.get();
	}
	
}

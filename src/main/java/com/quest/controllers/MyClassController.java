package com.quest.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quest.dao.StudentDao;
import com.quest.entity.Student;

@Controller
@RequestMapping("/myclass")
public class MyClassController {
	@Resource
	private StudentDao studentDao;
	
	@RequestMapping("/student")
	public String queryStudent(HttpServletRequest request, HttpServletResponse response, Model model){
		Student student = new Student();
		student.setName("Stephen.curry");
		student.setAge(29);
		student.setScore(98);
		student.setAddress("西海南路39号");
		try {
			studentDao.insert(student);
			System.out.println("=================>插入成功");
			
			System.out.println("开始查询");
			Student resultStudent = studentDao.findStudentByName("curry");
			model.addAttribute("student", resultStudent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "myclass/student";
	}
}

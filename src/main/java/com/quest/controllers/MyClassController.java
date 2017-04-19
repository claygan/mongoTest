package com.quest.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quest.entity.Student;
import com.quest.service.dao.StudentDao;

@Controller
@RequestMapping("/myclass")
public class MyClassController {
	@Resource
	private StudentDao studentDao;
	
	@RequestMapping("/index")
	public String toIndex(){
		return "myclass/student";
	}
	
	@ResponseBody
	@RequestMapping("/insert")
	public Map<String,String> insertStudent(HttpServletRequest request, HttpServletResponse response, Model model,Student student){
		Map<String,String> resultMap = new HashMap<String, String>();
		try {
			studentDao.insert(student);
			resultMap.put("msg", "插入成功");
			System.out.println("=================>插入成功");
			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("msg", "报错了");
			return resultMap;
		}
	}
	
	@RequestMapping("/select")
	public String queryStudent(HttpServletRequest request, HttpServletResponse response, Model model,String name){
		try {
			System.out.println("==================>开始查询");
			Student resultStudent = studentDao.findStudentByName(name);
			model.addAttribute("student", resultStudent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "myclass/result";
	}
	
}

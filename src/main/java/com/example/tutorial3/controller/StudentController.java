package com.example.tutorial3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.service.StudentService;
import com.example.tutorial3.model.StudentModel;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController() {
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value = "npm", required = true) String npm,
			          @RequestParam(value = "name", required = true) String name,
			          @RequestParam(value = "gpa", required = true) double gpa) {
		StudentModel student = new StudentModel (npm, name, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	@RequestMapping("/student/view")
	public String view(Model model, @RequestParam(value = "npm", required = true) String npm) {
		StudentModel student = studentService.selectStudent(npm);
		model.addAttribute("student", student);
		return "view";
	}
	@RequestMapping("/student/view/{npm1}")
	public String view1(Model model, @PathVariable String npm1) {
		StudentModel student = studentService.selectStudent(npm1);
		model.addAttribute("npmrequest", npm1);
		if(student != null) {
			model.addAttribute("student", student);
			return "view";			
		} 
			return "notfound";
	}
	
	@RequestMapping("/student/viewall")
	public String viewAll(Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewAll";
	}
	
	@RequestMapping("/student/delete/{npm}")
	public String delete(Model model, @PathVariable String npm) {
		StudentModel student = studentService.selectStudent(npm);
		model.addAttribute("npmrequest", npm);
		if(student !=null) {
			studentService.deleteStudent(npm);
			return "delete";
		}
			return "notfound";
	}
	
	
	
}

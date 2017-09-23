package com.example.tutorial3.service;

import java.util.ArrayList;
import java.util.List;
import com.example.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService {
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();
	
	@Override
	public StudentModel selectStudent (String npm) {
		
		for (int ii=0; ii<studentList.size(); ii++) {
			if (studentList.get(ii).getNpm().equals(npm)) {
				return studentList.get(ii);
			}
		}
		return null;
	}
	
	@Override
	public List<StudentModel> selectAllStudents(){
		return studentList;
	}
	
	@Override
	public void addStudent(StudentModel student) {
		studentList.add(student);
	}
	
	@Override
	public void deleteStudent(String npm) {
		for(int ii=0; ii<studentList.size(); ii++) {
			if(studentList.get(ii).getNpm().equals(npm)) {
				studentList.remove(ii);
			}
		}
	}
	
}

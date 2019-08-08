package service;


import entity.Student;

import java.util.List;


public interface StudentService {

	public Student studentLogin(String loginName, String pwd);

	public  Boolean addStudent(Student student);
	
	public List<Student> queryStudentList();

	public int deleteStudent(int id);
	 public Student  queryStudentById(int id);
	public Boolean updateStudent(Student student);

	
}

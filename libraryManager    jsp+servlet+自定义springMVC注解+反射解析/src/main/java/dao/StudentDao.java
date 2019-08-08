package dao;


import entity.Student;

import java.sql.Connection;
import java.util.List;


public interface StudentDao {

	public Student queryStudentByLoginNameAndPwd(String loginName, String pwd, Connection con) ;
	
	public Boolean addStudent(Student student,Connection con);
	public List<Student> queryAllStudent(Connection con);

	public Student queryStudentById(int id, Connection con);

	public int deleteStudentById(int id, Connection con);
	public Boolean updateStudent(Student student, Connection con);

}

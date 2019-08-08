package service.impl;


import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import entity.Student;
import service.StudentService;
import utils.DbUtil;

import java.sql.Connection;
import java.util.List;

public class StudentServiceImpl implements StudentService {
	
	private StudentDao studentDao = new StudentDaoImpl();

	@Override
	public Student studentLogin(String loginName, String pwd) {
		Connection con = DbUtil.getConnection();
		Student stu = studentDao.queryStudentByLoginNameAndPwd(loginName, pwd, con);
		DbUtil.close(con, null, null);
		return stu;
	}

	@Override
	public Boolean addStudent(Student student) {
		Connection connection = DbUtil.getConnection();
		return  studentDao.addStudent(student,connection);
	}

	@Override
	public List<Student> queryStudentList() {
		Connection con = DbUtil.getConnection();
		List<Student> studentList =  studentDao.queryAllStudent(con);
		DbUtil.close(con, null, null);
		return studentList;
	}

	public int deleteStudent(int id){
		Connection con = DbUtil.getConnection();
		int i = studentDao.deleteStudentById(id, con);
		return i;
	}

	@Override
	public Student queryStudentById(int id) {
		Connection con = DbUtil.getConnection();
		return studentDao.queryStudentById(id,con) ;
	}

	@Override
	public Boolean updateStudent(Student student) {
		Connection con = DbUtil.getConnection();

		return studentDao.updateStudent(student,con);
	}
}

package dao.impl;
import dao.StudentDao;
import entity.Student;
import utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

	@Override  //下面的Connection是由service层传过来的
	public Student queryStudentByLoginNameAndPwd(String loginName, String pwd,
												 Connection con) {
		PreparedStatement ps = null;
		ResultSet rs  = null;
		try{
			ps = con.prepareStatement("select * from student where loginName = ? and password = ?");
			ps.setString(1, loginName);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			Student stu = null;
			if(rs.next()){
				stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setAge(rs.getInt("age"));
				stu.setLoginName(rs.getString("loginName"));
				stu.setName(rs.getString("name"));
				stu.setPwd(rs.getString("password"));
				stu.setSex(rs.getInt("sex"));
			}
			return stu;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			DbUtil.close(con, ps, rs);
		}
	}

	@Override
	public Boolean addStudent(Student student, Connection con) {
		String sql="insert into student(name,age,sex,loginName,password) value(?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,student.getName());
			preparedStatement.setInt(2,student.getAge());
			preparedStatement.setInt(3,student.getSex());
			preparedStatement.setString(4,student.getLoginName());
			preparedStatement.setString(5,student.getPwd());
			int i = preparedStatement.executeUpdate();
			if(i>0){
				return true;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Student> queryAllStudent(Connection con) {
		PreparedStatement ps = null;
		ResultSet rs  = null;
		List<Student> studentList = new ArrayList<Student>();
		try{
			ps = con.prepareStatement("select * from student ");
			rs = ps.executeQuery();
			while(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setAge(rs.getInt("age"));
				stu.setLoginName(rs.getString("loginName"));
				stu.setName(rs.getString("name"));
				stu.setPwd(rs.getString("password"));
				studentList.add(stu);
			}
			return studentList;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			DbUtil.close(null, ps, rs);
		}
	}


	public Student queryStudentById(int id ,Connection con) {
		String sql="select * from  student where id=? ";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setAge(rs.getInt("age"));
				stu.setLoginName(rs.getString("loginName"));
				stu.setName(rs.getString("name"));
				stu.setPwd(rs.getString("password"));
				stu.setSex(rs.getInt("sex"));
				DbUtil.close(con,preparedStatement,rs);
				return stu;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int deleteStudentById(int id, Connection con){
		String sql="delete from student where id='"+id+"'";
		try {
			Statement statement = con.createStatement();
			int i = statement.executeUpdate(sql);
			return  i;
		} catch (SQLException e) {
			e.printStackTrace();
		}

        return  0;
	}

	@Override
	public Boolean updateStudent(Student student ,Connection con) {
	//	Student student1 = queryStudentById(student.getId(),con);
		String sql="update student set name=?,age=?,login_name=? where id=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1,student.getName());
			preparedStatement.setInt(2,student.getAge());
			preparedStatement.setString(3,student.getLoginName());
			preparedStatement.setInt(4,student.getId());
			int i = preparedStatement.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
return  false;
	}
}

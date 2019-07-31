package cn.easyBuy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import cn.easyBuy.entity.User;
import cn.easyBuy.utils.DBUtil;

public class UserDao {
	/*private QueryRunner qr=new */
//按用户名和密码查询
	public User findByLoginnameAndPassword(String loginname,String password) throws ClassNotFoundException, SQLException {
		String sql="select * from easybuy_user  where loginName=? and password=?";
	   Object[] obj= {loginname,password}; 
		User user=DBUtil.queryForBean(sql, obj, User.class);
		return user;	     
	}
	
/*校验用户名是否注册*/	
	public boolean validateLoginname(String loginname) {
		String sql="select * from easybuy_user where loginname=?";
	    User user=	DBUtil.queryForBean(loginname,new Object[] {loginname} , User.class);
	    if(user==null) {
	    	return true;
	    }else {
	    	return false;
	    }
	}

	
/*添加用户*/
	public void add(User formUser) throws SQLException {
		String sql="insert into easybuy_user(loginName,userName,password,identityCode,email,mobile) values(?,?,?,?,?,?)";
	/*	Connection conn=DBUtil.getConnection();
	     PreparedStatement ps=conn.prepareStatement(sql);*/
		Object[] obj= {formUser.getLoginName(),formUser.getUserName(),formUser.getPassword(),formUser.getIdentityCode(),formUser.getEmail(),formUser.getMobile()};
	    DBUtil.update(sql, obj);
	}
	
}

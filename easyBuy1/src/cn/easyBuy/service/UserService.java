package cn.easyBuy.service;

import java.sql.SQLException;

import cn.easyBuy.dao.UserDao;
import cn.easyBuy.entity.User;

public class UserService {
     private UserDao userDao=new UserDao();
 
     
     //登录查询
     public  User login(User user) {
    	 try {
			return userDao.findByLoginnameAndPassword(user.getLoginName(), user.getPassword());
		} catch (Exception e) {		
    	 throw new RuntimeException(e);
     }
}
     
     /*校验用户名是否注册*/	
 	public boolean validateLoginname(String loginname) {
 		return userDao.validateLoginname(loginname);
 	}

      /*注册功能*/	
	public void regist(User formUser) throws SQLException {
		// TODO Auto-generated method stub
		userDao.add(formUser);
	}
     
     
     
     
}

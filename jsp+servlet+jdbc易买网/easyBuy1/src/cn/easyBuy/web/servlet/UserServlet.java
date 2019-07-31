package cn.easyBuy.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.QueryRunner;

import cn.easyBuy.entity.User;
import cn.easyBuy.service.UserService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet{
	
private UserService userService=new UserService();

	/*public String index()*/
	
  //登录验证
public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*
	 * 1. 封装表单数据到User
	 * 2. 校验表单数据
	 * 3. 使用service查询，得到User
	 * 4. 查看用户是否存在，如果不存在：
	 *   * 保存错误信息：用户名或密码错误
	 *   * 保存用户数据：为了回显
	 *   * 转发到login.jsp
	 * 5. 如果存在，查看状态，如果状态为false：
	 *   * 保存错误信息：您没有激活
	 *   * 保存表单数据：为了回显
	 *   * 转发到login.jsp
	 * 6. 登录成功：
	 * 　　* 保存当前查询出的user到session中
	 *   * 保存当前用户的名称到cookie中，注意中文需要编码处理。
	 */
	/*
	 * 1. 封装表单数据到user
	 */
     String loginname  =request.getParameter("loginname");
     String password=request.getParameter("password");
       User formUser=new User();
       formUser.setLoginName(loginname);
       formUser.setPassword(password);
       /* 2. 调用userService#login()方法*/		 
      User user= userService.login(formUser);
       //3.开始判断
       if(user==null) {
    	   request.setAttribute("msg", "用户名或密码错误！");
    	   request.setAttribute("user", formUser);
    	  return "f:/login.jsp";
       }else {
    	   //保存用户到session
    	   request.getSession().setAttribute("sessionUser", user);
    	   //获取用户名保存到cookie中
    	 loginname=  URLEncoder.encode(loginname, "utf-8");
    	 Cookie cookie=new Cookie("loginname", loginname);
    	 response.addCookie(cookie);  
    	
    	return "r:/index.jsp";
    	
       }
}


/*注册功能*/
  
	public String  regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*1.封装表单数据到User对象中*/
	   User formUser=CommonUtils.toBean(request.getParameterMap(), User.class);
	 /*2.校验之，如果校验失败，保存错误信息，返回到regist.jsp显示*/	   
	   Map<String, String> errors=validateRegist(formUser, request.getSession());
	   if(errors.size()>0) {
		   request.setAttribute("form", formUser);
		   request.setAttribute("errors", errors);
		   return "f:/login.jsp";
	   }
	  /*3.使用service完成业务*/
	   try {
		userService.regist(formUser);
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
	  /*4.保存成功信息，转发到msg.jsp显示*/
	   request.setAttribute("code", "success");
	   request.setAttribute("msg", "注册成功！");
	   return "f:/msg.jsp";
	}

/*注册校验
 * 对表单的字段进行逐个校验，如果有错误，使用当前字段名称位key,错误信息为value,保存到map中
 * 返回map*/ 
   private Map<String,String> validateRegist(User formUser,HttpSession session){
	  Map<String,String> errors=new HashMap<String,String>();
	   //1.校验登录名
	   String loginname=formUser.getLoginName();
	   if(loginname==null||loginname.trim().isEmpty()) {
		   errors.put("loginname", "用户名不能为空!");
	   }else if(!userService.validateLoginname(loginname)) {
		   errors.put("loginname", "用户名已被注册!");
	   }
	/*2.校验登录密码*/
	   String  password=formUser.getPassword();
	   if(password==null||password.trim().isEmpty()) {
		   errors.put("password", password);
	   }
	/*3.确认密码校验*/
	   String repassword=formUser.getRepassword();
	   if(repassword==null||repassword.trim().isEmpty()) {
		   errors.put("repassword", "确认密码不能为空！");
	   }else if(!repassword.equals(password)) {
		   errors.put("repassword", "两次输入不一致！");
	   }
	 /*4.验证码校验*/
	   String verifyCode=formUser.getVerifyCode();
		String vcode=(String) session.getAttribute("vCode");	   
		if(verifyCode==null||verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空！");
		}else if(!verifyCode.equalsIgnoreCase(vcode)){
			errors.put("verifyCode", "验证码错误！");			
		}
	   return errors;	   
   }
   
   
   
   
}

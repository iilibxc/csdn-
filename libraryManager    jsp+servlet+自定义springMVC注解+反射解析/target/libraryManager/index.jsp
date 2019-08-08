<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common-header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/common-ui.jsp" %>
	<style type="text/css">
		#loginButton{
			margin-top: 20px;
		margin-right: 20px;
		}
		input{
			margin-bottom:5px;
			line-height: 20px;
		}

	</style>
<script type="text/javascript">
	//用户登录
$(document).ready(function(){
	$("#loginButton").on('click' , function(){
		$.ajax({
			url : '/StudentServlet/doLogin' ,
			dataType : 'json',
			type : 'POST' , 
			data : $("#loginForm").serialize(),//将#loginForm表格里面的数据进行序列化，然后带给访问的servlet
			success : function(data){
				if(data.success){
					window.location.href="/BookServlet/toMainPage";
				}else{
					alert(data.msg);
				}
			} , 
			error : function(data){
				alert('服务器端处理异常');
			}
		});
	});
});
//用户注册
function toRegister() {
    $.ajax({
        url : '/StudentServlet/toRegisterPage' ,
        dataType : 'json',
        type : 'POST' ,
        success : function(data){
            if(data.success){
                window.location.href="BookServlet/toMainPage";
            }else{
                alert(data.msg);
            }
        } ,
        error : function(data){
            alert('服务器端处理异常');
        }
    });
}
	
</script>
<title>登录</title>
</head>
<body>
	<center>
		<form id = "loginForm"  >
			<%--<input type="hidden" name="method" value="doLogin">--%><!-- 隐藏域的作用就是把method字段带给servlet,从而让servlet类调用具体的方法-->
			          <div align="center"><h1>欢迎大家登陆！</h1></div><br>
			<label>帐号</label><input type="text" id="loginName" name="loginName" /> <br/>
			<label>密码</label><input type="password" id="pwd" name="pwd" /> <br/>
			<input type="button" id="loginButton" value="登录"  >            <input type="reset" value="重置">
		   <input type="button" value="注册" onclick="window.location='/StudentServlet/toRegisterPage'">
		</form>



	</center>
</body>
</html>
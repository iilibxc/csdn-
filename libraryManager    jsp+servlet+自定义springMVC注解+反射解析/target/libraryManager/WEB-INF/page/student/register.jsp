<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/8/4
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="/common/jquery.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript">
    function  register() {
        $.ajax({
            url : '/StudentServlet/register' ,
            /*  dataType : 'text',*/
            type : 'POST' ,
            data :$("#registerForm").serialize(),
            success : function(data){
                alert("注册成功");
                window.location.href="/StudentServlet/toIndexPage";
            },
            error : function(data){
                console.log(data);
                alert('服务器端处理异常');
            }
        })
    }
</script>
<body>
<center>
<form id="registerForm"   ><%--action="/StudentServlet"--%>
   <%-- <input type="hidden" name="method" value="register">--%><!-- 隐藏域的作用就是把method字段带给servlet,从而让servlet类调用具体的方法-->
    <div align="center"><h1>欢迎大家登陆！</h1></div><br>
    <label>用户名</label><input type="text" id="loginName" name="loginName" /> <br/>
    <label>真实姓名</label><input type="text" id="name" name="name"/><br>
    <div>性别:
        <label><input type="radio" name="sex" value="1">男生</label>
        <label><input type="radio" name="sex" value="2">女生</label>
    </div>
     <label>年龄</label><input type="text" name="age"/><br>
    <label>密码</label><input type="password" id="pwd" name="pwd" /> <br/>
    <input type="reset" value="重置">
    <input type="button" value="注册" onclick="register()">
</form>
</center>
</body>
</html>

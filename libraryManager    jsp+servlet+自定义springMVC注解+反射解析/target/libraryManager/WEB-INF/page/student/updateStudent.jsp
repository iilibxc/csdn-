<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改学生信息</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
    <script type="text/javascript">
        var basePath = '${pageContext.request.contextPath}';

        function updateUser(){
            $.ajax({
                url : basePath + "/studentServer.jsp?method=addStudent" ,
                type : "GET" ,
                data : {
                    "name":$("#name").val(),
                    "age":$("#age").val(),
                    "loginName":$("#loginName").val()
                },
                dataType : "json",
                success : function(data){
                    alert(data.msg);
                    if(data.success){
                        //window.location.href= basePath + "/studentServer.jsp?method=queryStudentList";
                    }
                },
                error : function(){
                    alert("服务器处理异常");
                }
            });
        }
    </script>
</head>
<body>
<center>
    <form id="updateStudentForm" action="/student">
        <input type="hidden" name="id" value="${student.id}">
        <input type="hidden" name="method" value="updateStudentInfo">
        <label>学生姓名</label><input type="text" id="name" name="name" value="${student.name}"/> <br />
        <label>学生年龄</label><input type="text" id="age" name="age"value="${student.age}"/> <br />
        <label>登录帐号</label><input type="text" id="loginName" name="loginName" value="${student.loginName}"/> <br />
        <input type="submit" value="保存" > <input type="reset" value="重置">
    </form>


</center>
</body>
</html>
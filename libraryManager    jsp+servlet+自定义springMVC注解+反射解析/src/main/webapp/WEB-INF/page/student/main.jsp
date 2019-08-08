<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common-header.jsp" %>
<%@ include file="/common/common-ui.jsp" %>

<html>
<head>
    <title>借书详情页</title>
</head>
<script>
    //借书
    function  lendBookByBookId(id) {
        $.ajax({
            url : '/BookServlet/lendBook' ,
            dataType : 'json',
            type : 'POST' ,
            data :{id:id},
            success : function(data){
                if(data.success){
                    window.location.href="/BookServlet/toMainPage";
                }else{
                    alert(data.msg);
                }
            } ,
            error : function(data){
                console.log(data);
                alert('服务器端处理异常');
            }
        })
    }
    //还书
    function  returnBooks(bookId) {
        var returnNum= prompt("请输入还书数量","1");
        if (returnNum>0){
        $.ajax({
            url : '/BookServlet/returnBooks' ,
            dataType : 'json',
            type : 'POST' ,
            data :{bookId:bookId,returnNum:returnNum},
            success : function(data){
                if(data.success){
                    window.location.href="/BookServlet/toMainPage";
                }else{
                    alert(data.msg);
                }
            } ,
            error : function(data){
                console.log(data);
                alert('服务器端处理异常');
            }
        })}
    }
//查看图书详情
    function  checkBookDetail(id) {
        $.ajax({
            url : '/BookServlet/checkBookDetail' ,
          /*  dataType : 'text',*/
            type : 'POST' ,
            data :{bookId:id},
            success : function(data){
                console.log(data);
               alert(data);
            } ,
            error : function(data){
                console.log(data);
                alert('服务器端处理异常');
            }
        })
    }

    //退出登录
    function  logout() {
        $.ajax({
            url : '/StudentServlet/logout' ,
            /*  dataType : 'text',*/
            type : 'POST' ,
            data :{method:"logout"},
            success : function(data){
               window.location.href="http://localhost:8080/index.jsp";
            } ,
            error : function(data){
                console.log(data);
                alert('服务器端处理异常');
            }
        })
    }

</script>

<body>
<div>
<center><h1>您好，亲爱的${LOGIN_STUDENT.name}${LOGIN_STUDENT.sex==1?"先森":"女士"}  </h1></center>
<br><br>
<center>
<table border="1px">
       <h1>图书馆</h1>
    <tr>
        <td>图书编号</td>
        <td>图书名称</td>
        <td>图书封面</td>
        <td>剩余本数</td>
        <td>操作</td>
    </tr>
<c:forEach var="book" items="${bookList}">
    <c:if test="${book.bookNumber>0}">
    <tr>
        <td>${book.id}</td>
        <td>${book.bookName}</td>
        <td>1</td>
        <td>${book.bookNumber}</td>
        <td>
            <input type="button" onclick="lendBookByBookId(${book.id})" value="借书">
            <input type="button" onclick="checkBookDetail(${book.id})" value="查看借书详情">
        </td>
    </tr>
    </c:if>
</c:forEach>
</table>
    <%@ include file="/WEB-INF/pagerBar.jsp" %>
</center>
</div>
<br><br><br>
<center>
<h1>我的书架</h1>
<table border="1px">
    <tr>
        <td>图书名称</td>
        <td>图书编号</td>
        <td>图书封面</td>
        <td>图书数量</td>
        <td>操作</td>
    </tr>
    <c:forEach var="myBookInfo" items="${myBookInfos}">
        <c:if test="${myBookInfo.lendNum>0}">
        <tr>
        <td>${myBookInfo.bookName}</td>
            <td>${myBookInfo.id}</td>
        <td>1</td>
            <td>${myBookInfo.lendNum}</td>
        <td><input type="button" value="还书" onclick="returnBooks(${myBookInfo.id})"> </td>
        </tr>
        </c:if>
    </c:forEach>
</table>
    <input type="button" onclick="logout(${LOGIN_STUDENT.id})" value="退出登录">
</center>


</body>
</html>

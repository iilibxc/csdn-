<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
  function toPointPage() {
      var val = $("#pointPage").val();
      if(val==''){
          window.location="/BookServlet/toMainPage&currentPage=1";
      }else{
          window.location="/BookServlet/toMainPage&currentPage="+val;
      }
      
  }
</script>
<style type="text/css">
    .cur{
        color: blue;
    }
</style>
<div class="pages">
    <c:if test="${pager.pageCount>=1}">
      <a href="/BookServlet/toMainPage&currentPage=1" class="p_pre">首页</a>
        <c:if test="${pager.currentPage>1}">
            <a href="/BookServlet/toMainPage&currentPage=${pager.currentPage-1}" class="p_pre">上一页</a>
        </c:if>
        <c:forEach  var="temp" begin="${pager.currentPage>3?pager.currentPage-3:1}" end="${pager.pageCount-pager.currentPage>3?pager.currentPage+3:pager.pageCount}" step="1">
            <c:if test="${pager.currentPage==temp}">
                <a href="/BookServlet/toMainPage&currentPage=${temp}" class="cur">${temp}</a>
            </c:if>
            <c:if test="${pager.currentPage!=temp}">
                <a href="/BookServlet/toMainPage&currentPage=${temp}">${temp}</a>
            </c:if>
        </c:forEach>
      <c:if test="${pager.currentPage<pager.pageCount}">
         <a href="/BookServlet/toMainPage&currentPage=${pager.currentPage+1}" class="p_pre">下一页</a>
      </c:if>
      <a href="/BookServlet/toMainPage&currentPage=${pager.pageCount}" class="p_pre">尾页</a>
        跳到<input type="text" id="pointPage" size="1" value="${pager.currentPage}"> 页
        <input type="button" onclick="toPointPage()" value="跳转" >
    </c:if>
    <c:if test="${pager.pageCount==0}">
        暂无记录
    </c:if>
</div>

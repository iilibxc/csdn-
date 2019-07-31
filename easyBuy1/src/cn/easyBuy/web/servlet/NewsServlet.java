package cn.easyBuy.web.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.easyBuy.entity.News;
import cn.easyBuy.service.NewsService;
@WebServlet("/NewsServlet")
public class NewsServlet extends BaseServlet{
	private NewsService newsService=new NewsService();
	/**
	 * 获取当前id
	 * @param req
	 * @return
	 */
	private int getCurrentId(HttpServletRequest request) {
		int id = 1;
		String param = request.getParameter("id");
		if(param != null && !param.trim().isEmpty()) {
			try {
				id = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		return id;
	}
	
/*查询接下来的5条新闻记录*/
  public String queryNextList(HttpServletRequest request,HttpServletResponse response) {
	  /*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
	       int id= getCurrentId(request);
	      List<News> nList=newsService.queryNextList(id,5);
	      request.setAttribute("nList", nList);
	      return "f:/backend/news/newsList.jsp";
  }
  
 /*根据id查新闻内容*/
  public String newsDeatil(int id){
	  return "f:/backend/news/newDetail.jsp";
  }
  
}

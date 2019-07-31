package cn.easyBuy.service;

import java.util.List;

import cn.easyBuy.dao.NewsDao;
import cn.easyBuy.entity.News;

public class NewsService {
     private NewsDao newsDao=new NewsDao();
	
	//
	public List<News> queryNextList(int begin,int amount) {
		return newsDao.findNewsByIdAndAmount( begin,amount);
		
	}

}

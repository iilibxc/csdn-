package cn.easyBuy.dao;

import java.util.List;

import cn.easyBuy.entity.News;
import cn.easyBuy.utils.DBUtil;

public class NewsDao  {
	/*查询下5条新闻记录
	public List<News> searchNextfive(int number){//number代表当前在页面展示的最后一个新闻的id值
		String sql="select * from easybuy_news limit ?,5";
		
	}*/

	public List<News> findNewsByIdAndAmount(int begin, int amount) {
		String sql="select * from easybuy_news limit ?,?";
		Object[] obj= {begin,amount}; 
		List<News> nList=DBUtil.queryForList(sql, obj, News.class);
		return nList;
	}
	
	
}

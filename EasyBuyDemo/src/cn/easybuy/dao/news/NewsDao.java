package cn.easybuy.dao.news;

import java.util.List;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.News;
import cn.easybuy.param.NewsParams;

/**
 * 新闻的dao
 */
public interface NewsDao extends IBaseDao {

	public void save(News news) throws Exception;

	public void update(News news) throws Exception;
	
	public void deleteById(Integer id) throws Exception;
	
	public News getNewsById(Integer id)throws Exception; 
	
	public List<News> queryNewsList(NewsParams params)throws Exception; 
	
	public Integer queryNewsCount(NewsParams params)throws Exception; 
}

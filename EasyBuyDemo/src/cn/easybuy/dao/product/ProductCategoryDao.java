package cn.easybuy.dao.product;

import cn.easybuy.dao.IBaseDao;
import cn.easybuy.entity.OrderDetail;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.entity.User;
import cn.easybuy.param.OrderDetailParam;
import cn.easybuy.param.ProductCategoryParam;
import cn.easybuy.utils.Params;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * addObject(UserAddress userAddress)
 * getRowList(params)
 * getRowCount(params)
 * getById(Integer id)
 * updateByQuery(params)
 */
public interface ProductCategoryDao extends IBaseDao {

	void deleteById(Integer parseLong);//删除商品分类
	
	public List<ProductCategory> queryProductCategorylist(ProductCategoryParam param);

	public List<ProductCategory> queryAllProductCategorylist(ProductCategoryParam param);
	
	public ProductCategory queryProductCategoryById(Integer id);
	
	public Integer save(ProductCategory productCategory) ;
	
	public Integer queryProductCategoryCount(ProductCategoryParam param);
	
	public void update(ProductCategory productCategory) ;
}

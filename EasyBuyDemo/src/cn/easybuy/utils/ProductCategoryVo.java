package cn.easybuy.utils;

import cn.easybuy.entity.Product;
import cn.easybuy.entity.ProductCategory;

import java.io.Serializable;
import java.util.List;
/*产品分类实体：包括分类下的所有产品
*/
public class ProductCategoryVo implements Serializable {

    private ProductCategory productCategory;
    private List<ProductCategoryVo> productCategoryVoList;//该分类下的所有子分类的
    private List<Product> productList;//该分类下的所有产品

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductCategoryVo> getProductCategoryVoList() {
        return productCategoryVoList;
    }

    public void setProductCategoryVoList(List<ProductCategoryVo> productCategoryVoList) {
        this.productCategoryVoList = productCategoryVoList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

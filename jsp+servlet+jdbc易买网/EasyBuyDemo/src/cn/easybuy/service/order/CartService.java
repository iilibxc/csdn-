package cn.easybuy.service.order;

import cn.easybuy.utils.ShoppingCart;


public interface CartService {

    public ShoppingCart modifyShoppingCart(String productId,String quantityStr,ShoppingCart cart) throws Exception;

    public ShoppingCart calculate(ShoppingCart cart)throws Exception;
}

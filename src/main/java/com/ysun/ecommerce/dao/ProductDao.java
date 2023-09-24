package com.ysun.ecommerce.dao;
import com.ysun.ecommerce.Product;
import com.ysun.ecommerce.PurchaseOrder;

import java.util.List;

public interface ProductDao {

    List<Product> getListProduct();
    void addProduct(Product product);

    void purchaseProduct(PurchaseOrder order);
}

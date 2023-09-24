package com.ysun.ecommerce.service;
import com.ysun.ecommerce.Product;
import com.ysun.ecommerce.PurchaseOrder;

import java.util.List;

public interface ProductService {
    List<Product> getListProduct();

    void addProduct(Product product);

    void purchaseProduct(PurchaseOrder purchaseOrder);
}

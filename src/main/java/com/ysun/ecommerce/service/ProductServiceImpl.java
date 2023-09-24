package com.ysun.ecommerce.service;
import com.ysun.ecommerce.Product;
import com.ysun.ecommerce.PurchaseOrder;
import com.ysun.ecommerce.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> getListProduct() {
        //Call Dao
        return productDao.getListProduct();
    }

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);
    }
    @Override
    public void purchaseProduct(PurchaseOrder purchaseOrder) {
        productDao.purchaseProduct(purchaseOrder);
    }
}

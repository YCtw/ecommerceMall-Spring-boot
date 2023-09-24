package com.ysun.ecommerce.controller;
import com.ysun.ecommerce.Product;
import com.ysun.ecommerce.PurchaseOrder;
import com.ysun.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "http://localhost:5173")
public class ProductController {
    @Autowired
    private ProductService productService;



    //Get all products controller
    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getListProduct();
    }

    //Add new products
    @PostMapping("/add")
    public String addProduct(@RequestBody Product product){
        productService.addProduct(product);
        return "item being added successfully.";
    }


    //Purchase new products -
    @PostMapping("/purchaseProducts")
    public String purchaseProduct(@RequestBody PurchaseOrder purchaseOrder){
        productService.purchaseProduct(purchaseOrder);
        return "place new order";
    }
}

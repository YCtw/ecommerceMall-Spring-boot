package com.ysun.ecommerce.controller;
import com.ysun.ecommerce.Order;
import com.ysun.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@CrossOrigin(origins= "http://localhost:5173")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //Get all orders controller
    @GetMapping("/orders")
    public List<Order> getAllOrder(){
        return orderService.getListOrder();
    }
}

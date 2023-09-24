package com.ysun.ecommerce.service;
import com.ysun.ecommerce.Order;
import com.ysun.ecommerce.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<Order> getListOrder() {

        //Call Dao
        return orderDao.getListOrder();
    }
}

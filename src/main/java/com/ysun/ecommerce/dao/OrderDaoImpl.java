package com.ysun.ecommerce.dao;

import com.ysun.ecommerce.Order;
import com.ysun.ecommerce.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public List<Order> getListOrder() {
        String sql = "SELECT order_id, member_id, price, pay_status FROM `order`";
        Map<String, Object> map = new HashMap<>();
        List<Order> list = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
        return list;
    }
}

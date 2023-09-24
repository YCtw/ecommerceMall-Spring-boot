package com.ysun.ecommerce;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        //從資料庫中取得數據
        String oid = rs.getString("order_id");
        String oMemId = rs.getString("member_id");
        Integer price = rs.getInt("price");
        Integer payStatus = rs.getInt("pay_status");

        //將數據轉為Java object
        Order order = new Order();
        order.setOrderID(oid);
        order.setMemberID(oMemId);
        order.setPrice(price);
        order.setPayStatus(payStatus);
        return order;
    }
}

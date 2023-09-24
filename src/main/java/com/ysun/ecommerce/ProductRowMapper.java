package com.ysun.ecommerce;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        //從資料庫中取得數據
        String pid = rs.getString("product_id");
        String pname = rs.getString("product_name");
        Integer price = rs.getInt("price");
        Integer quantity = rs.getInt("quantity");

        //將數據轉為Java object
        Product product = new Product();
        product.setProductID(pid);
        product.setProductName(pname);
        product.setPrice(price);
        product.setQuantity(quantity);
        return product;
    }
}

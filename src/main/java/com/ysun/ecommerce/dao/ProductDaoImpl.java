package com.ysun.ecommerce.dao;

import com.ysun.ecommerce.Product;
import com.ysun.ecommerce.ProductRowMapper;
import com.ysun.ecommerce.PurchaseOrder;
import com.ysun.ecommerce.PurchaseProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//變成可被注入的bean
@Component
public class ProductDaoImpl implements ProductDao{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    //Generate random String for Order ID
    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();

        // Initialize the random number generator
        Random random = new Random();

        // Generate random characters and append them to the string
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
    @Override
    public List<Product> getListProduct() {
        String sql = "SELECT product_id, product_name, price, quantity FROM product";

        Map<String, Object> map = new HashMap<>();

        List<Product> list = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
        return list;
    }

    @Override
    public void addProduct(Product product) {
        String sql = "INSERT INTO product(product_id, product_name, price, quantity) VALUE (:productID, :productName, :price, :quantity)";
        Map<String, Object> map = new HashMap<>();

        map.put("productID", product.getProductID());
        map.put("productName", product.getProductName());
        map.put("price", product.getPrice());
        map.put("quantity", product.getQuantity());
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void purchaseProduct(PurchaseOrder order) {
        //Create new order
        String orderID = generateRandomString(10);
        String sql = "INSERT INTO `order`(order_id, member_id, price, pay_status) VALUE (:orderID, :customerID, :totalAmount, :payStatus)";
        Map<String, Object> map = new HashMap<>();
        map.put("orderID", orderID);
        map.put("customerID", order.getCustomerID());
        map.put("totalAmount", Integer.parseInt(order.getTotalAmount()));
        map.put("payStatus", Integer.parseInt(order.getPayStatus()));
        namedParameterJdbcTemplate.update(sql, map);

        //Update quantity of each product
        for (PurchaseProduct p : order.getPurchaseProducts()){
            String itemName = p.getProductName();
            Integer purchaseQuantity = Integer.parseInt(p.getQuantity());

            String getAmountsql = "SELECT quantity FROM product WHERE product_name = :itemName";
            Map<String, Object> mapT = new HashMap<>();
            mapT.put("itemName", itemName);
            int quantity = namedParameterJdbcTemplate.queryForObject(getAmountsql, mapT, Integer.class);

            int updatedQuantity = quantity-purchaseQuantity;
            if (updatedQuantity <= 0){
                //Drop the product
                String deleteProductsql = "DELETE FROM product WHERE product_name = :itemName";
                namedParameterJdbcTemplate.update(deleteProductsql, mapT);
            }
            else{
                //Update the quantity of the product
                String updateQuantitysql = "UPDATE product SET quantity = :updatedQuantity WHERE product_name = :itemName";
                Map<String, Object> updateParams = new HashMap<>();
                updateParams.put("updatedQuantity", updatedQuantity);
                updateParams.put("itemName", itemName);
                namedParameterJdbcTemplate.update(updateQuantitysql, updateParams);
            }
        }

    }
}

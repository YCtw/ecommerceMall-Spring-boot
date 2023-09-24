#Get all products
SELECT product_id, product_name, price, quantity FROM product

#Add new product
INSERT INTO product(product_id, product_name, price, quantity) VALUE (:productID, :productName, :price, :quantity)

#Create new order
INSERT INTO order(order_id, member_id, price, pay_status) VALUE (:orderID, :customerID, :totalAmount, :payStatus)

#Get quantity base on product_name
SELECT quantity FROM product WHERE product_name = :itemName

#Delete product base on product_name
DELETE FROM product WHERE product_name = :itemName

#Update product quantity base on product_name
UPDATE product SET quantity = :updatedQuantity WHERE product_name = :itemName

#Get all orders
SELECT order_id, member_id, price, pay_status FROM order
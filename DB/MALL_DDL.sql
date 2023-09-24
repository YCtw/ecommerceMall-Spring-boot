#product Table
CREATE TABLE product
(
    product_id         VARCHAR(32)  NOT NULL PRIMARY KEY,
    product_name       VARCHAR(128) NOT NULL,
    price              INT          NOT NULL,
    quantity           INT          NOT NULL,
);

#order Table
CREATE TABLE order
(
    order_id           VARCHAR(128) NOT NULL PRIMARY KEY,
    product_name       VARCHAR(128) NOT NULL,
    price              INT          NOT NULL,
    pay_status         INT          NOT NULL,
);

#orderdetail Table
CREATE TABLE orderdetail
(
    orderitemsn        int	    NOT NULL PRIMARY KEY AUTO INCREMENT,
    order_id           VARCHAR(128) NOT NULL,
    product_id         VARCHAR(128) NOT NULL,
    quantity           INT          NOT NULL,
    standprice         INT          NOT NULL,
    price              INT          NOT NULL,
);

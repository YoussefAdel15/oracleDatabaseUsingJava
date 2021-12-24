CREATE USER market IDENTIFIED BY PASSWORD;

GRANT CREATE TABLE TO market;

grant all PRIVILEGES to market;

--- first Table (Customer)
CREATE table market.customer(
    customer_id number(5) CONSTRAINT market_customer_customer_id_pk PRIMARY KEY ,
    last_name VARCHAR(25) not NULL,
    phone_number VARCHAR(25),
    email VARCHAR(25) CONSTRAINT market_customer_email_uk unique
);

--- Second Table (Product)
CREATE TABLE market.product (
product_id NUMBER(9)PRIMARY KEY,
name VARCHAR2(25) NOT NULL,
price NUMBER(10)CONSTRAINT positive_price CHECK (price > 0)
);

--- Third Table (Orders)
CREATE TABLE market.orders (
order_id NUMBER(9)PRIMARY KEY,
customer_id number(5)CONSTRAINT ord_cus_fk REFERENCES market.customer(customer_id) ,
price NUMBER(10) CONSTRAINT price_nn  not null CONSTRAINT price_CK CHECK (price > 0)
);

--- Fourth Table (Order Itenms)
CREATE TABLE market.order_items (
order_items_id NUMBER(7) CONSTRAINT order_items_id_pk PRIMARY KEY,
order_id NUMBER(7) CONSTRAINT order_id_fk REFERENCES market.orders(order_id) on DELETE CASCADE,
product_id NUMBER(7) CONSTRAINT product_id_fk REFERENCES market.product(product_id) ,
quantity NUMBER(10) CONSTRAINT quantity_ck CHECK (quantity > 0)
);

--- test
SELECT * FROM MARKET.customer;
SELECT * FROM MARKET.product;
SELECT * FROM MARKET.orders;
SELECT * FROM MARKET.order_items;


COMMIT;


package com.sci.utils;

public interface Constant {

  String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
  String USERNAME = "system";
  String PASSWORD = "system";

  // CUSTOMERS
  String SELECT_ALL_CUSTOMERS="SELECT * FROM MARKET.customer";
  String SELECT_CUSTOMER_BY_ID ="SELECT * FROM MARKET.customer WHERE customer_id = ?";
  String INSERT_CUSTOMER ="INSERT INTO MARKET.customer VALUES(? ,? ,?,? )";
  String DELETE_CUSTOMER_BY_ID="DELETE FROM MARKET.customer WHERE customer_id = ?";
  String UPDATE_CUSTOMER_BY_ID="UPDATE MARKET.customer SET last_name=? ,phone_number=? , email=? WHERE customer_id = ?";

  // ORDERS
  String SELECT_ALL_ORDERS="SELECT * FROM MARKET.orders";
  String SELECT_ORDER_BY_ID="SELECT * FROM MARKET.orders WHERE order_id = ?";
  String INSERT_ORDER="INSERT INTO MARKET.orders VALUES(? ,? ,? ,? )";
  String DELETE_ORDER_BY_ID="DELETE FROM MARKET.orders WHERE order_id = ?";
  String UPDATE_ORDER_BY_ID="UPDATE MARKET.orders SET customer_id =? ,price=?  , name =? WHERE order_id = ?";

  // ORDER ITEMS
  String SELECT_ALL_ORDER_ITEMS="SELECT * FROM MARKET.order_items";
  String SELECT_BY_ORDER_ID="SELECT * FROM MARKET.order_items WHERE order_items_id = ?";
  String INSERT_ORDER_ITEM="INSERT INTO MARKET.order_items VALUES(? ,? ,? ,?)";
  String DELETE_ORDER_ITEM_BY_ID="DELETE FROM MARKET.order_items WHERE order_items_id = ?";
  String UPDATE_ORDER_ITEM_BY_ID="UPDATE MARKET.order_items SET order_id =? ,product_id=? ,quantity=? WHERE order_items_id = ?";

  // PRODUCTS
  String SELECT_ALL_PRODUCTS="SELECT * FROM MARKET.product";
  String SELECT_BY_ID="SELECT * FROM MARKET.product WHERE product_id = ?";
  String INSERT_PRODUCT="INSERT INTO MARKET.product VALUES(? ,? ,? )";
  String DELETE_PRODUCT_BY_ID="DELETE FROM MARKET.product WHERE product_id = ?";
  String UPDATE_PRODUCT_BY_ID="UPDATE MARKET.order_items SET name =? ,price=? WHERE product_id = ?";
}

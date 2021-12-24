package com.sci.services;

import com.sci.models.Order;
import com.sci.models.OrderItem;
import com.sci.utils.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrderItemsService implements AutoCloseable{

    private final PreparedStatement selectOrderItems;
    private final PreparedStatement selectOrderItemsById;
    private final PreparedStatement insertOrderItems;
    private final PreparedStatement deleteOrderItemsById;
    private final PreparedStatement updateOrderItemsById;

    /*
    String SELECT_ALL_ORDER_ITEMS="SELECT * FROM MARKET.order_items;";
  String SELECT_BY_ORDER_ID="SELECT * FROM MARKET.order_items WHERE order_items_id = ?;";
  String INSERT_ORDER_ITEM="INSERT INTO MARKET.order_items VALUES(? ,? ,? ,?);";
  String DELETE_ORDER_ITEM_BY_ID="DELETE FROM MARKET.order_items WHERE order_items_id = ?;";
  String UPDATE_ORDER_ITEM_BY_ID="UPDATE MARKET.order_items SET order_id =? ,product_id=? ,quantity=? WHERE order_items_id = ?;";
     */
    public OrderItemsService(Connection connection) throws SQLException {
        selectOrderItems = connection.prepareStatement(Constant.SELECT_ALL_ORDERS);
        selectOrderItemsById = connection.prepareStatement(Constant.SELECT_BY_ORDER_ID);
        insertOrderItems = connection.prepareStatement(Constant.INSERT_ORDER_ITEM);
        deleteOrderItemsById = connection.prepareStatement(Constant.DELETE_ORDER_ITEM_BY_ID);
        updateOrderItemsById = connection.prepareStatement(Constant.UPDATE_ORDER_ITEM_BY_ID);
    }

    @Override
    public void close() throws Exception {
        selectOrderItems.close();
    }

    public List<OrderItem> getAll() {
        List<OrderItem> res = new ArrayList<>();
        try {
            //String SELECT_ALL_ORDER_ITEMS="SELECT * FROM MARKET.order_items;";
            ResultSet resultSet = selectOrderItems.executeQuery();
            while (resultSet.next()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderItemID(resultSet.getInt("order_items_id"));
                orderItem.setOrderID(resultSet.getInt("order_id"));
                orderItem.setProductID(resultSet.getInt("product_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                res.add(orderItem);
            }
        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
        return res;
    }

    public OrderItem getById(int id) {
        OrderItem orderItem = null;
        try {
            selectOrderItems.clearParameters();
            //String SELECT_BY_ORDER_ID="SELECT * FROM MARKET.order_items WHERE order_items_id = ?;";
            selectOrderItems.setInt(1, id);
            ResultSet resultSet = selectOrderItems.executeQuery();
            if (resultSet.next()) {
                orderItem = new OrderItem();
                orderItem.setOrderItemID(resultSet.getInt("order_items_id"));
                orderItem.setOrderID(resultSet.getInt("order_id"));
                orderItem.setProductID(resultSet.getInt("product_id"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
        }
        return orderItem;
    }

    public void insert(OrderItem orderItem) throws SQLException {
        //String INSERT_ORDER_ITEM="INSERT INTO MARKET.order_items VALUES(? ,? ,? ,?);";
        insertOrderItems.clearParameters();
        insertOrderItems.setInt(1, orderItem.getOrderItemID());
        insertOrderItems.setInt(2, orderItem.getOrderID());
        insertOrderItems.setInt(3, orderItem.getProductID());
        insertOrderItems.setInt(4, orderItem.getQuantity());
        selectOrderItems.executeQuery();
    }

    public void deleteById(int id) throws SQLException {
        deleteOrderItemsById.clearParameters();
        //String DELETE_ORDER_ITEM_BY_ID="DELETE FROM MARKET.order_items WHERE order_items_id = ?;";
        deleteOrderItemsById.setInt(1 , id);
        deleteOrderItemsById.executeQuery();
    }

    public void updateById(OrderItem orderItem) throws SQLException {
        insertOrderItems.clearParameters();
        //String UPDATE_ORDER_ITEM_BY_ID="UPDATE MARKET.order_items SET order_id =? ,product_id=? ,quantity=? WHERE order_items_id = ?;
        insertOrderItems.setInt(1, orderItem.getOrderID());
        insertOrderItems.setInt(2, orderItem.getProductID());
        insertOrderItems.setInt(3, orderItem.getQuantity());
        insertOrderItems.setInt(4, orderItem.getOrderItemID());
        selectOrderItems.executeQuery();
    }
}

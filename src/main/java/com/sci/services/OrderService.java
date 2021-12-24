package com.sci.services;

import com.sci.models.Customer;
import com.sci.models.Order;
import com.sci.utils.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements AutoCloseable{

    /*
      String SELECT_ALL_ORDERS="SELECT * FROM MARKET.orders;";
      String SELECT_ORDER_BY_ID="SELECT * FROM MARKET.orders WHERE order_id = ?;";
      String INSERT_ORDER="INSERT INTO MARKET.orders VALUES(? ,? ,? ,? );";
      String DELETE_ORDER_BY_ID="DELETE FROM MARKET.orders WHERE order_id = ?;";
      String UPDATE_ORDER_BY_ID="UPDATE MARKET.orders SET customer_id =? ,price=? WHERE order_id = ?;";
     */
    private final PreparedStatement selectOrder;
    private final PreparedStatement selectOrderById;
    private final PreparedStatement insertOrder;
    private final PreparedStatement deleteOrderById;
    private final PreparedStatement updateOrderById;


    public OrderService(Connection connection) throws SQLException {
        selectOrder = connection.prepareStatement(Constant.SELECT_ALL_ORDERS);
        selectOrderById = connection.prepareStatement(Constant.SELECT_ORDER_BY_ID);
        insertOrder = connection.prepareStatement(Constant.INSERT_ORDER);
        deleteOrderById = connection.prepareStatement(Constant.DELETE_ORDER_BY_ID);
        updateOrderById = connection.prepareStatement(Constant.UPDATE_ORDER_BY_ID);

    }

    @Override
    public void close() throws Exception {
        selectOrder.close();
    }

    public List<Order> getALL(){
        List<Order> result = new ArrayList<>();
        try {
            //String SELECT_ALL_ORDERS="SELECT * FROM MARKET.orders;";
            ResultSet resultSet = selectOrder.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setOrderID(resultSet.getInt("order_id"));
                order.setCustomer_id(resultSet.getInt("customer_id"));
                order.setPrice(resultSet.getInt("pricr"));
                order.setName(resultSet.getString("name"));
                result.add(order);
            }
        }
        catch (SQLException e) {
            System.out.println("ERROR "+e.getMessage());
        }
        return result;
    }

    public Order getByID(int id){
        Order order = null;
        //String SELECT_ORDER_BY_ID="SELECT * FROM MARKET.orders WHERE order_id = ?;";
        try {
            selectOrderById.clearParameters();
            selectOrderById.setInt(1, id);
            ResultSet resultSet = selectOrderById.executeQuery();
            if (resultSet.next()){
                order = new Order();
                order.setOrderID(id);
                order.setPrice(resultSet.getInt("price"));
                order.setName(resultSet.getString("name"));
                order.setCustomer_id(resultSet.getInt("customer_id"));
            }
        }
        catch (SQLException e){
            System.out.println("ERROR "+e.getMessage());
        }
        return order;
    }

    public void insert(Order order) throws SQLException {
        insertOrder.clearParameters();
        //String INSERT_ORDER="INSERT INTO MARKET.orders VALUES(? ,? ,? ,? );";
        insertOrder.setInt(1,order.getOrderID());
        insertOrder.setInt(2, order.getCustomer_id());
        insertOrder.setInt(3,order.getPrice());
        insertOrder.setString(4,order.getName());
        insertOrder.executeQuery();
    }

    public void deleteByID(int id) throws SQLException {
        deleteOrderById.clearParameters();
        //String DELETE_ORDER_BY_ID="DELETE FROM MARKET.orders WHERE order_id = ?;";
        deleteOrderById.setInt(1,id);
        deleteOrderById.executeQuery();
    }

    public void updateByID(Order order) throws SQLException {
        updateOrderById.clearParameters();
        //String UPDATE_ORDER_BY_ID="UPDATE MARKET.orders SET customer_id =? ,price=? , name=? WHERE order_id = ?;";
        updateOrderById.clearParameters();

        updateOrderById.setInt(1, order.getCustomer_id());
        updateOrderById.setInt(2, order.getPrice());
        updateOrderById.setString(3, order.getName());
        updateOrderById.setInt(4, order.getOrderID());
        updateOrderById.executeQuery();
    }
}

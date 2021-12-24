package com.sci.services;

import com.sci.models.Customer;
import com.sci.models.Order;
import com.sci.models.OrderItem;
import com.sci.models.Product;
import com.sci.utils.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DatabaseClient implements AutoCloseable {

    private final Connection connection;
    private final CustomerService CustomerService;
    private final ProductService ProductService;
    private final OrderService OrderService;
    private final OrderItemsService OrderItemsService;


    public DatabaseClient() throws SQLException {
        connection = DriverManager.getConnection(Constant.DB_URL, Constant.USERNAME, Constant.PASSWORD);
        CustomerService = new CustomerService(connection);
        ProductService = new ProductService(connection);
        OrderService =new OrderService(connection);
        OrderItemsService =new OrderItemsService(connection);

    }

    @Override
    public void close() throws Exception {
        connection.close();
        CustomerService.close();
        ProductService.close();
        OrderService.close();
        OrderItemsService.close();

    }
    //CUSTOMERS METHODS
    public List<Customer> getAllCustomers() {
        return CustomerService.getALL();
    }

    public Customer getCustomerByID(int id){
        return CustomerService.getByID(id);
    }

    public void insertCustomer(Customer customer) throws SQLException {
        CustomerService.insert(customer);
    }

    public  void deleteCustomerById(int id) throws SQLException {
        CustomerService.deleteByID(id);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        CustomerService.updateByID(customer);
    }

    // PRODUCTS METHODS

    public List<Product> getAllProducts() {
        return ProductService.getALL();
    }

    public Product getProductByID(int id){
        return ProductService.getByID(id);
    }

    public void insertProduct(Product product) throws SQLException {
        ProductService.insert(product);
    }

    public void deleteProductById(int id) throws SQLException {
        ProductService.deleteByID(id);
    }

    public void updateProduct(Product product) throws SQLException {
        ProductService.updateByID(product);
    }

    // ORDER METHODS

    public List<Order> getAllOrder() {
        return OrderService.getALL();
    }
    public Order getOrderById(int id) {
        return OrderService.getByID(id);
    }
    public void insertOrder(Order order) throws SQLException {
        OrderService.insert(order);
    }
    public void deleteOrderById(int id) throws SQLException {
        OrderService.deleteByID(id);
    }
    public void updateOrderById(Order order) throws SQLException {
        OrderService.updateByID(order);
    }

    // ORDER ITEM METHODS

    public List<OrderItem> getAllOrderItem() {
        return OrderItemsService.getAll();
    }
    public OrderItem getOrderItemById(int id) {
        return OrderItemsService.getById(id);
    }
    public void insertOrderItem(OrderItem orderItem) throws SQLException {
        OrderItemsService.insert(orderItem);
    }
    public void deleteOrderItemById(int id) throws SQLException {
        OrderItemsService.deleteById(id);
    }
    public void updateOrderItemById(OrderItem orderItem) throws SQLException {
        OrderItemsService.updateById(orderItem);
    }

}

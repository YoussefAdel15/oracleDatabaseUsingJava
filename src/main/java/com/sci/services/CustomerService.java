package com.sci.services;

import com.sci.models.Customer;
import com.sci.utils.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CustomerService implements AutoCloseable {
    private final PreparedStatement selectCustomer;
    private final PreparedStatement selectCustomerById;
    private final PreparedStatement insertCustomer;
    private final PreparedStatement deleteCustomerById;
    private final PreparedStatement updateCustomerById;

    public CustomerService(Connection connection) throws SQLException {
        selectCustomer = connection.prepareStatement(Constant.SELECT_ALL_CUSTOMERS);
        selectCustomerById = connection.prepareStatement(Constant.SELECT_CUSTOMER_BY_ID);
        insertCustomer = connection.prepareStatement(Constant.INSERT_CUSTOMER);
        deleteCustomerById = connection.prepareStatement(Constant.DELETE_CUSTOMER_BY_ID);
        updateCustomerById = connection.prepareStatement(Constant.UPDATE_CUSTOMER_BY_ID);
    }


    @Override
    public void close() throws Exception {
        selectCustomer.close();
    }

    public List<Customer> getALL(){
        List<Customer> result = new ArrayList<>();
        try {
            ResultSet resultSet = selectCustomer.executeQuery();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerID(resultSet.getInt("customer_id"));
                customer.setLastName(resultSet.getString("last_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
                result.add(customer);
            }
        }
        catch (SQLException e) {
            System.out.println("ERROR "+e.getMessage());
        }
        return result;
    }

    public Customer getByID(int id){
        Customer customer = null;
        //SELECT * FROM MARKET.customer WHERE customer_id = ?;
        try {
            selectCustomerById.clearParameters();
            selectCustomerById.setInt(1, id);
            ResultSet resultSet = selectCustomerById.executeQuery();
            if (resultSet.next()){
                customer = new Customer();
                customer.setCustomerID(id);
                customer.setLastName(resultSet.getString("last_name"));
                customer.setPhoneNumber(resultSet.getString("phone_number"));
                customer.setEmail(resultSet.getString("email"));
            }
        }
        catch (SQLException e){
            System.out.println("ERROR "+e.getMessage());
        }
        return customer;
    }

    public void insert(Customer customer) throws SQLException {
        insertCustomer.clearParameters();
        //INSERT INTO MARKET.customer VALUES(? ,? ,?,? );
        insertCustomer.setInt(1,customer.getCustomerID());
        insertCustomer.setString(2, customer.getLastName());
        insertCustomer.setString(3,customer.getPhoneNumber());
        insertCustomer.setString(4,customer.getEmail());
        insertCustomer.executeQuery();
    }

    public void deleteByID(int id) throws SQLException {
        deleteCustomerById.clearParameters();
        //DELETE FROM MARKET.customer WHERE customer_id = ?;
        deleteCustomerById.setInt(1,id);
        deleteCustomerById.executeQuery();
    }

    public void updateByID(Customer customer) throws SQLException {
        updateCustomerById.clearParameters();
        //UPDATE MARKET.customer SET last_name=? ,phone_number=? , email=? WHERE customer_id = ?;

        updateCustomerById.setString(1, customer.getLastName());
        updateCustomerById.setString(2, customer.getPhoneNumber());
        updateCustomerById.setString(3, customer.getEmail());
        updateCustomerById.setInt(4, customer.getCustomerID());
        updateCustomerById.executeQuery();
    }

}

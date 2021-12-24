package com.sci;

import com.sci.models.Customer;
import com.sci.services.DatabaseClient;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Customers {
    static Scanner input;
    static DatabaseClient db;
    public void CustomersTest() throws SQLException {
        input = new Scanner(System.in);
        db = new DatabaseClient();
//  0 = exit , 1 = select all , 2 = select by id and get values
//  3 = insert and get values , 4 = delete by Id and get values
//  5 = update by id and get values
        int operation ;
        System.out.println("0 = exit\n1 = select all\n2 = select by id and get values\n3 = insert and get values " +
                "\n4 = delete by Id and get values\n5 = update by id and get values");
        System.out.println("Enter number of operation : ");
        while((operation = input.nextInt()) != 0){

            switch (operation){
                case 1:
                    handleSelectAll();
                    break;
                case 2 :
                    handleSelectById();
                    break;
                case 3 :
                    handleInsert();
                    break;
                case 4:
                    handleDeleteById();
                    break;
                case 5:
                    handleUpdateCustomerById();
                    break;
            }
            System.out.println("0 = exit\n1 = select all\n2 = select by id and get values\n3 = insert and get values " +
                    "\n4 = delete by Id and get values\n5 = update by id and get values");
        }
    }

    private void handleUpdateCustomerById() throws SQLException {

        String customer_name = input.next();
        String phone = input.next();
        String email = input.next();
        int customerId = input.nextInt();
        com.sci.models.Customer customer = new com.sci.models.Customer();
        customer.setCustomerID(customerId);
        customer.setLastName(customer_name);
        customer.setPhoneNumber(phone);
        customer.setEmail(email);
        db.updateCustomer(customer);
    }

    private void handleDeleteById() throws SQLException {
        int id = input.nextInt();
        db.deleteCustomerById(id);
    }

    private void handleInsert() throws SQLException {
        int customerId = input.nextInt();
        String customer_name = input.next();
        String phone = input.next();
        String email = input.next();
        Customer customer = new Customer();
        customer.setCustomerID(customerId);
        customer.setLastName(customer_name);
        customer.setPhoneNumber(phone);
        customer.setEmail(email);
        db.insertCustomer(customer);

    }

    private void handleSelectById() {
        int id = input.nextInt();
        System.out.println(db.getCustomerByID(id));
    }

    private void handleSelectAll() {
        List<Customer> CustomerList = db.getAllCustomers();
        for(Customer customer : CustomerList){
            System.out.println(customer);
        }
    }
}
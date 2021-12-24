package com.sci;

import com.sci.models.Order;
import com.sci.models.Product;
import com.sci.services.DatabaseClient;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Orders {
    static Scanner input;
    static DatabaseClient db;
    public void OrderTest() throws SQLException {
        input = new Scanner(System.in);
        db = new DatabaseClient();
//  0 = exit , 1 = select all , 2 = select by id and get values
//  3 = insert and get values , 4 = delete by Id and get values
//  5 = update by id and get values
        int operation ;
        System.out.println("0 = exit\n1 = select all\n2 = select by id and get values\n3 = insert and get values " +
                "\n4 = delete by Id and get values\n5 = update by id and get values");
        System.out.println("Enter number or operation : ");
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
                    handleUpdateOrdersById();
                    break;
            }
            System.out.println("0 = exit\n1 = select all\n2 = select by id and get values\n3 = insert and get values " +
                    "\n4 = delete by Id and get values\n5 = update by id and get values");
        }
    }

    private void handleUpdateOrdersById() throws SQLException {


        int customer_id = input.nextInt();
        int price = input.nextInt();
        int order_id = input.nextInt();

        Order order = new Order();
        order.setOrderID(order_id);
        order.setCustomer_id(customer_id);
        order.setPrice(price);
        db.updateOrderById(order);
    }

    private void handleDeleteById() throws SQLException {
        int id = input.nextInt();
        db.deleteOrderById(id);
    }

    private void handleInsert() throws SQLException {
        int order_Id = input.nextInt();
        int customer_ID = input.nextInt();
        int price = input.nextInt();
        Order order = new Order();
        order.setOrderID(order_Id);
        order.setCustomer_id(customer_ID);
        order.setPrice(price);
        db.insertOrder(order);

    }

    private void handleSelectById() {
        int id = input.nextInt();
        System.out.println(db.getOrderById(id));
    }

    private void handleSelectAll() {
        List<Order> productList = db.getAllOrder();
        for(Order order : productList){
            System.out.println(order);
        }
    }

}

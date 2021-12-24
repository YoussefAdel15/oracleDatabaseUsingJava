package com.sci;

import com.sci.models.Order;
import com.sci.models.OrderItem;
import com.sci.models.Product;
import com.sci.services.DatabaseClient;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderItems {
    static Scanner input;
    static DatabaseClient db;
    public void OrderItemTest() throws SQLException {
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
                    handleUpdateOrderItemsById();
                    break;
            }
            System.out.println("0 = exit\n1 = select all\n2 = select by id and get values\n3 = insert and get values " +
                    "\n4 = delete by Id and get values\n5 = update by id and get values");
        }
    }

    private void handleUpdateOrderItemsById() throws SQLException {

        int order_id = input.nextInt();
        int product_id = input.nextInt();
        int quantity = input.nextInt();
        int order_item_id = input.nextInt();

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemID(order_item_id);
        orderItem.setOrderID(order_id);
        orderItem.setProductID(product_id);
        orderItem.setQuantity(quantity);
        db.updateOrderItemById(orderItem);
    }

    private void handleDeleteById() throws SQLException {
        int id = input.nextInt();
        db.deleteOrderItemById(id);
    }

    private void handleInsert() throws SQLException {

        int order_id = input.nextInt();
        int product_id = input.nextInt();
        int quantity = input.nextInt();
        int order_item_id = input.nextInt();

        OrderItem orderItem = new OrderItem();;
        orderItem.setOrderItemID(order_item_id);
        orderItem.setOrderID(order_id);
        orderItem.setProductID(product_id);
        orderItem.setQuantity(quantity);
        db.insertOrderItem(orderItem);

    }

    private void handleSelectById() {
        int id = input.nextInt();
        System.out.println(db.getOrderItemById(id));
    }

    private void handleSelectAll() {
        List<OrderItem> productList = db.getAllOrderItem();
        for(OrderItem orderItem : productList){
            System.out.println(orderItem);
        }
    }

}

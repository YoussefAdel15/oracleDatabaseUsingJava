package com.sci;

import com.sci.models.Customer;
import com.sci.models.Order;
import com.sci.models.OrderItem;
import com.sci.models.Product;
import com.sci.services.DatabaseClient;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {
  static Scanner input;
  public static void main(String[] args) throws SQLException {
    //0-EXIT  1-CUSTOMERS TABLE  2-PRODUCTS TABLE  3-ORDERS TABLE  4-ORDER ITEMS TABLE
    input = new Scanner(System.in);
    Customers c = new Customers();
    Products p = new Products();
    Orders o = new Orders();
    OrderItems oi = new OrderItems();
    int operation ;
    System.out.println("0-EXIT\n1-CUSTOMERS TABLE\n2-PRODUCTS TABLE\n3-ORDERS TABLE\n4-ORDER ITEMS TABLE");
    System.out.println("Enter number of table : ");
    while((operation = input.nextInt()) != 0){
      switch (operation){
        case 1:
          c.CustomersTest();
          break;
        case 2 :
          p.ProductTest();
          break;
        case 3 :
          o.OrderTest();
          break;
        case 4:
          oi.OrderItemTest();
          break;
      }
      System.out.println("0-EXIT\n1-CUSTOMERS TABLE\n2-PRODUCTS TABLE\n3-ORDERS TABLE\n4-ORDER ITEMS TABLE");
      System.out.println("Enter number of table : ");
    }
  }
}


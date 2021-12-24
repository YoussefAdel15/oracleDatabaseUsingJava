package com.sci;

import com.sci.models.Product;
import com.sci.services.DatabaseClient;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Products {
    static Scanner input;
    static DatabaseClient db;
    public void ProductTest() throws SQLException {
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
                    handleUpdateProductById();
                    break;
            }
            System.out.println("0 = exit\n1 = select all\n2 = select by id and get values\n3 = insert and get values " +
                    "\n4 = delete by Id and get values\n5 = update by id and get values");
        }
    }

    private void handleUpdateProductById() throws SQLException {

        String name = input.next();
        int price = input.nextInt();
        int productId = input.nextInt();

        Product product = new com.sci.models.Product();
        product.setProductID(productId);
        product.setName(name);
        product.setPrice(price);
        db.updateProduct(product);
    }

    private void handleDeleteById() throws SQLException {
        int id = input.nextInt();
        db.deleteProductById(id);
    }

    private void handleInsert() throws SQLException {
        int productId = input.nextInt();
        String name = input.next();
        int price = input.nextInt();
        Product product = new Product();
        product.setProductID(productId);
        product.setName(name);
        product.setPrice(price);
        db.insertProduct(product);

    }

    private void handleSelectById() {
        int id = input.nextInt();
        System.out.println(db.getProductByID(id));
    }

    private void handleSelectAll() {
        List<Product> productList = db.getAllProducts();
        for(Product product : productList){
            System.out.println(product);
        }
    }

}

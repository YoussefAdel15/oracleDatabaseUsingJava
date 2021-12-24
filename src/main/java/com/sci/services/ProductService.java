package com.sci.services;

import com.sci.models.Customer;
import com.sci.models.Product;
import com.sci.utils.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements AutoCloseable{

    private final PreparedStatement selectProduct;
    private final PreparedStatement selectProductById;
    private final PreparedStatement insertProduct;
    private final PreparedStatement deleteProductById;
    private final PreparedStatement updateProductById;

    public ProductService(Connection connection) throws SQLException {
        selectProduct = connection.prepareStatement(Constant.SELECT_ALL_PRODUCTS);
        selectProductById = connection.prepareStatement(Constant.SELECT_BY_ID);
        insertProduct=connection.prepareStatement(Constant.INSERT_PRODUCT);
        deleteProductById=connection.prepareStatement(Constant.DELETE_PRODUCT_BY_ID);
        updateProductById=connection.prepareStatement(Constant.UPDATE_PRODUCT_BY_ID);
    }

    @Override
    public void close() throws Exception {
        selectProduct.close();
    }

    public List<Product> getALL(){
        List<Product> result = new ArrayList<>();
        try {
            ResultSet resultSet = selectProduct.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProductID(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                result.add(product);
            }
        }
        catch (SQLException e){
            System.out.println("ERROR "+e.getMessage());
        }

        return result;
    }

    public Product getByID(int id){
        Product product = null;
        //SELECT * FROM MARKET.product WHERE customer_id = ?;
        try {
            selectProductById.clearParameters();
            selectProductById.setInt(1, id);
            ResultSet resultSet = selectProductById.executeQuery();
            if (resultSet.next()){
                product = new Product();
                product.setProductID(id);
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
            }
        }
        catch (SQLException e){
            System.out.println("ERROR "+e.getMessage());
        }
        return product;
    }

    public void insert(Product product) throws SQLException {
        insertProduct.clearParameters();
        //INSERT INTO MARKET.product VALUES(? ,? ,? );
        insertProduct.setInt(1,product.getProductID());
        insertProduct.setInt(2, product.getPrice());
        insertProduct.setString(3,product.getName());
        insertProduct.executeQuery();
    }

    public void deleteByID(int id) throws SQLException {
        deleteProductById.clearParameters();
        //DELETE FROM MARKET.customer WHERE product_id = ?;
        deleteProductById.setInt(1,id);
        deleteProductById.executeQuery();
    }

    public void updateByID(Product product) throws SQLException {
        updateProductById.clearParameters();
        //UPDATE MARKET.order_items SET name =? ,price=? WHERE product_id = ?;
        updateProductById.setString(1, product.getName());
        updateProductById.setInt(2, product.getPrice());
        updateProductById.setInt(3, product.getProductID());
        updateProductById.executeQuery();
    }
}

package com.sci.models;

public class Product {
    private int productID;
    private String name;
    private int price;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

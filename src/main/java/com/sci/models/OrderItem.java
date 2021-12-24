package com.sci.models;

public class OrderItem {
    private int orderID;
    private int orderItemID;
    private int productID;
    private int quantity;

    public int getOrderItemID() {
        return orderItemID;
    }

    public void setOrderItemID(int orderItemID) {
        this.orderItemID = orderItemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "order_items_id=" + orderItemID +
                ", order_id=" + orderID +
                ", product_id=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}

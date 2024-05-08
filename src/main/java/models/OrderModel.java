package models;

public class OrderModel {
    private String userID;
    private String orderID;



    public OrderModel() {}

    public OrderModel(String orderID, String userID) {
        this.userID = userID;
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public String getOrderID() {
        return orderID;
    }

}
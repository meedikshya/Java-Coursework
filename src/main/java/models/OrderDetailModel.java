package models;

public class OrderDetailModel {
    private String orderID;
    private String productID;


    public OrderDetailModel() {}

    public OrderDetailModel(String orderID, String productID) {
        this.orderID = orderID;
        this.productID = productID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }


}
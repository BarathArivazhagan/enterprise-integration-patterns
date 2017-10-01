package com.barath.app;

/**
 * Created by barath on 01/10/17.
 */
public class Order {

    private Long orderId;

    private String productName;

    private String locationName;

    private int quantity;

    private Double amount;

    private OrderStatus status;

    public enum OrderStatus{
        SUCCESS,
        CANCELLED,
        PENDING
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order(Long orderId, String productName, String locationName, int quantity, Double amount, OrderStatus status) {
        this.orderId = orderId;
        this.productName = productName;
        this.locationName = locationName;
        this.quantity = quantity;
        this.amount = amount;
        this.status = status;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", locationName='" + locationName + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}

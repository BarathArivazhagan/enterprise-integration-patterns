package com.barath.app;

/**
 * Created by barath on 01/10/17.
 */
public class Order {

	/** The order id. */
	private Long orderId;

	/** The product name. */
	private String productName;

	/** The location name. */
	private String locationName;

	/** The quantity. */
	private int quantity;

	/** The amount. */
	private Double amount;

	/** The status. */
	private OrderStatus status;

	/**
	 * The Enum OrderStatus.
	 */
	public enum OrderStatus {

		/** The success. */
		SUCCESS,
		/** The cancelled. */
		CANCELLED,
		/** The pending. */
		PENDING
	}

	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the location name.
	 *
	 * @return the location name
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * Sets the location name.
	 *
	 * @param locationName the new location name
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public OrderStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	/**
	 * Instantiates a new order.
	 *
	 * @param orderId      the order id
	 * @param productName  the product name
	 * @param locationName the location name
	 * @param quantity     the quantity
	 * @param amount       the amount
	 * @param status       the status
	 */
	public Order(Long orderId, String productName, String locationName, int quantity, Double amount,
			OrderStatus status) {
		this.orderId = orderId;
		this.productName = productName;
		this.locationName = locationName;
		this.quantity = quantity;
		this.amount = amount;
		this.status = status;
	}

	/**
	 * Instantiates a new order.
	 */
	public Order() {
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Order{" + "orderId=" + orderId + ", productName='" + productName + '\'' + ", locationName='"
				+ locationName + '\'' + ", quantity=" + quantity + ", amount=" + amount + ", status=" + status + '}';
	}
}

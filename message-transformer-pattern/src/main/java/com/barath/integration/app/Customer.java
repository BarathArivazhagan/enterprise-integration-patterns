package com.barath.integration.app;

/**
 * The Class Customer.
 */
public class Customer {

	/** The customer id. */
	private Long customerId;

	/** The customer name. */
	private String customerName;

	/**
	 * Gets the customer id.
	 *
	 * @return the customer id
	 */
	public Long getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the customer id.
	 *
	 * @param customerId the new customer id
	 */
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets the customer name.
	 *
	 * @return the customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name.
	 *
	 * @param customerName the new customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + "]";
	}

	/**
	 * Instantiates a new customer.
	 *
	 * @param customerId   the customer id
	 * @param customerName the customer name
	 */
	public Customer(Long customerId, String customerName) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
	}

	/**
	 * Instantiates a new customer.
	 */
	public Customer() {
		super();

	}

}

package main_package;

public class Customer {
	private String customerFullName;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmailAddress;
	private String customerPhoneNumber;

	public Customer(String customerFirstName, String customerLastName, 
			String customerEmailAddress,String customerPhoneNumber) {
		customerFullName = customerFirstName + " " +customerLastName;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerEmailAddress = customerEmailAddress;
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}
	
	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}
}

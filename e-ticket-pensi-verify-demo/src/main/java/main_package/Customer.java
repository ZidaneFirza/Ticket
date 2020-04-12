package main_package;

public class Customer {
	private String customerFullName;
	private String customerEmailAddress;
	private String customerPhoneNumber;

	public Customer(String customerFullName, String customerEmailAddress,
			String customerPhoneNumber) {
		this.customerFullName = customerFullName;
		this.customerEmailAddress = customerEmailAddress;
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public String getCustomerEmailAddress() {
		return customerEmailAddress;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

}

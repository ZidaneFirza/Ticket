package main_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class CustomerDao {
	private Connection connection;

	public CustomerDao() {
		try {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setServerName("localhost");
			dataSource.setUser("root");
			dataSource.setPassword("");
			dataSource.setDatabaseName("e_ticket_demo");
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void insertCustomer(Customer customer) throws SQLException {
		PreparedStatement ps = connection
				.prepareStatement("INSERT INTO customer (customer_full_name, customer_email_address, "
						+ "customer_phone_number) values(?,?,?)");
		ps.setString(1, customer.getCustomerFullName());
		ps.setString(2, customer.getCustomerEmailAddress());
		ps.setString(3, customer.getCustomerPhoneNumber());
		ps.execute();

	}

	public int getCustomerId() throws SQLException {
		int count = 0;
		PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS count FROM customer");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			count = rs.getInt("count");
		}

		connection.close();
		return count;
	}

}

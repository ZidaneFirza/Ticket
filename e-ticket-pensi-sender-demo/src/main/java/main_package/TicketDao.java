package main_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class TicketDao {
	private Connection connection;

	public TicketDao() {
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

	public void insertTicket(Ticket ticket, int customerId) throws SQLException {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO ticket "
					+ "(ticket, customer_id) values(?,?)");

			ps.setString(1, ticket.getTicket());
			ps.setInt(2, customerId);
			ps.execute();

			connection.close();
	}

}

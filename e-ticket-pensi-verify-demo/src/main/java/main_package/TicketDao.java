package main_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class TicketDao {
	Connection connection;

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

	public boolean ticketIsVerified(String ticket) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("SELECT * FROM e_ticket_demo.ticket_verify_table where  ticket = ?");
				ps.setString(1, ticket);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				}
				connection.close();
				return false;
			} catch (SQLException e) {
				// TODO: handle exception
			}
			return false;
	}

	public int verifyTicket(String ticket)  {
			try {
				if (ticketIsExist(ticket)) {
					PreparedStatement ps = connection.prepareStatement(
							"INSERT INTO `e_ticket_demo`.`ticket_verify_table`\r\n" + "(ticket) values (?)");

					ps.setString(1, ticket);
					ps.execute();
					connection.close();
					return 1;
				}
				connection.close();
				return 0;
			} catch (SQLException e) {
				// TODO: handle exception
			}
			return 0;
	}
	
	public boolean ticketIsExist(String ticket) {
			try {
				PreparedStatement ps = connection
						.prepareStatement("SELECT * FROM e_ticket_demo.ticket where  ticket = ?");
				ps.setString(1, ticket);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				// TODO: handle exception
			}
			return false;
	}

}

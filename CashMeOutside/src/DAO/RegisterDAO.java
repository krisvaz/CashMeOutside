package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import bean.Client;
import DAO.ClientDAO;

public class RegisterDAO {
	DataSource ds;

	public RegisterDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/bookstore");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public Client registerCustomer(String username, String password, String fname, String lname)
			throws SQLException, ClassNotFoundException {
		String query1 = "INSERT INTO client (username, password, fname, lname, type, isActive) " + "VALUES ('"
				+ username + "', '" + password + "', '" + fname + "', '" + lname + "', 'Customer', 0)";
		Connection con = this.ds.getConnection();
		PreparedStatement p1 = con.prepareStatement(query1);
		int success = p1.executeUpdate(query1);
		Client client = null;
		if (success == 1) {
			client = new ClientDAO().getClientByUsername(username);
		}
		p1.close();
		con.close();
		return client;
	}

}

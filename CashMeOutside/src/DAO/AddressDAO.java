package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class AddressDAO {
	DataSource ds;

	public AddressDAO() throws ClassNotFoundException {
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/bookstore");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public boolean registerAddress(int userid, String street, String province, String city, String country,
			String zip, String phone) throws SQLException, ClassNotFoundException {
		String query1 = "INSERT INTO address (userid, street, province, city, country, zip, phone)"
				+ "VALUES ('"+userid+"', '"+street+"', '"+province+"', '"+city+"', '"+country+"', '"+zip+"', '"+phone+"')";
		Connection con = this.ds.getConnection();
		PreparedStatement p1 = con.prepareStatement(query1);
		int success = p1.executeUpdate(query1);
		if (success == 1) {
			return true;
		} else {
			return false;
		}
	}
}

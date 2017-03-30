package service;

import java.sql.SQLException;
import java.util.Map;

import DAO.*;
import bean.Client;

public class Service {

	ClientDAO visitorDAO;
	RegisterDAO registerDAO;
	AddressDAO addressDAO;

	public Service() throws ClassNotFoundException {
		visitorDAO = new ClientDAO();
		registerDAO = new RegisterDAO();
		addressDAO = new AddressDAO();
	}

	public Map<Integer, Client> getVisitors() throws SQLException {
		return visitorDAO.getClients();
	}

	public Client login(String username, String password) throws SQLException {
		return visitorDAO.login(username, password);
	}

	public Boolean validateUser(String username) throws SQLException {
		return visitorDAO.validateUser(username);
	}

	public Client registerCustomer(String username, String password, String fname, String lname)
			throws SQLException, ClassNotFoundException {
		return registerDAO.registerCustomer(username, password, fname, lname);

	}

	public boolean registerAddress(int userid, String street, String province, String city, String country, String zip,
			String phone) throws SQLException, ClassNotFoundException {
		return addressDAO.registerAddress(userid, street, province, city, country, zip, phone);
	}
}

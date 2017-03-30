package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.RegisterDAO;
import bean.Client;
import service.Service;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterCustomer")
public class RegisterCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Service service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			Service service = new Service();
			this.getServletContext().setAttribute("service", service);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/JSP/RegisterCustomer.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if ("Next".equalsIgnoreCase(request.getParameter("submit"))) {
			registerCustomer(request, response);
		}

		if ("Login".equalsIgnoreCase(request.getParameter("submit"))) {
			login(request, response);
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = (Service) this.getServletContext().getAttribute("service");
		try {
			if (service.validateUser(request.getParameter("username"))) {
				Client user = service.login(request.getParameter("username"), request.getParameter("password"));
				if (user == null) {
					request.setAttribute("username", request.getParameter("username"));
					request.setAttribute("invalidPassword", "Password Incorrect");
					request.getRequestDispatcher("/JSP/RegisterCustomer.jspx").forward(request, response);
				} else {
					request.setAttribute("user", user);
					request.getRequestDispatcher("/JSP/success.jspx").forward(request, response);
				}
			} else {
				request.setAttribute("invalidUser", "Username Incorrect");
				request.getRequestDispatcher("/JSP/RegisterCustomer.jspx").forward(request, response);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void registerCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service = (Service) this.getServletContext().getAttribute("service");
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			Client client = service.registerCustomer(username, password, fname, lname);
			request.setAttribute("userid", client.getUserid());
			request.setAttribute("fname", client.getFname());
			request.getRequestDispatcher("/JSP/RegisterAddress.jspx").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

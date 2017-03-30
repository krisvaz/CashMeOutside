package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AddressDAO;
import bean.Client;
import service.Service;

/**
 * Servlet implementation class RegisterAddress
 */
@WebServlet("/RegisterAddress")
public class RegisterAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Service service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterAddress() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		service = (Service) this.getServletContext().getAttribute("service");
		if ("Finish".equalsIgnoreCase(request.getParameter("submit"))) {
			try {
				int userid = Integer.parseInt(request.getParameter("userid"));
				String street = request.getParameter("street");
				String city = request.getParameter("city");
				String province = request.getParameter("province");
				String country = request.getParameter("country");
				String zip = request.getParameter("zip");
				String phone = request.getParameter("phone");
				service.registerAddress(userid, street, province, city, country, zip, phone);
				request.getRequestDispatcher("/JSP/RegisterAddress.jspx").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

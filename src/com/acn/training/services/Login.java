package com.acn.training.services;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.orm.PersistentException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.acn.training.model.Users;
import com.acn.training.utilities.SessionManager;

import org.springframework.security.core.userdetails.User;

import orm.com.acn.training.model.UsersDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, PersistentException {
		try {
			User cre = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String email = cre.getUsername();
			Users user = UsersDAO.loadUsersByQuery("email = '" + email + "'" , null);
			
			HttpSession sess = request.getSession();
			
			SessionManager.logParametersInSession(user, sess);
			
			/*if(user.getRole().getName().equals("user")) {
				RequestDispatcher rd = request.getRequestDispatcher("/user.jsp");
				rd.forward(request, response);
			} else if (user.getRole().getName().equals("admin")) {
				RequestDispatcher rd = request.getRequestDispatcher("/admin.jsp");
				rd.forward(request, response);
			} */
			RequestDispatcher rd = request.getRequestDispatcher("Home");
			rd.forward(request, response);
			
		} catch (ClassCastException e) {
			RequestDispatcher rd = request.getRequestDispatcher("Home");
			rd.forward(request, response);
			//response.sendRedirect("/" + request.getContextPath());
			//e.printStackTrace();
		} catch (Exception ex) {
			RequestDispatcher rd = request.getRequestDispatcher("Home");
			rd.forward(request, response);
			//ex.printStackTrace();
			//response.sendRedirect("/" + request.getContextPath());
		}
	}


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			processRequest(request, response);
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

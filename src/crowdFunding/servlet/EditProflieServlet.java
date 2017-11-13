package crowdFunding.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crowdFunding.model.*;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String username = (String) session.getAttribute("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int credit = Integer.parseInt(request.getParameter("credit"));
		String password  = request.getParameter("password");
		String imageUrl = request.getParameter("imageUrl");
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUsername(username);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setCredit(credit);
		user.setPassword(password);
		user.setImageUrl(imageUrl);
		userDao.updateUser(username, user);
		User newUser = new User();
		newUser = userDao.findUserByUsername(username);
		session.setAttribute("user", newUser);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
		request.setAttribute("user", newUser);
		rd.forward(request, response);		
	}
}
package crowdFunding.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crowdFunding.model.*;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		int credit = Integer.parseInt(request.getParameter("credit"));
		String password  = request.getParameter("password");
		String imageUrl = request.getParameter("imageUrl");
		Date createdDate = new Date();
		
		UserDao userDao = new UserDao();
		User user = null;
		user = userDao.findUserByUsername(username);
		// If the username has been used by others
		if(user != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
			request.setAttribute("errorMessage", "Username has been used!");
			rd.forward(request,response);
		}else{
			user = new User();
			UserDao dao = new UserDao();
			user.setUsername(username);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setCredit(credit);
			user.setPassword(password);
			user.setCreatedDate(createdDate);
			user.setImageUrl(imageUrl);
			dao.createUser(user);
			//Give the registerd user session and cookie
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(30*60);
			Cookie cookie = new Cookie("username", username);
			cookie.setMaxAge(30*60);
			response.addCookie(cookie);
			String encodedURL = response.encodeRedirectURL("index.jsp");
			response.sendRedirect(encodedURL);
		}	
	}
}
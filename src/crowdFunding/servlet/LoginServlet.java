package crowdFunding.servlet;

import java.io.IOException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String password = request.getParameter("password");
		System.out.println(request.getParameter("originPage")+"hello from loginservlet");
		UserDao dao = new UserDao();
		User user =  dao.findUserByUsername(username);
		if(user != null && password.equals(user.getPassword())){
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());
			session.setMaxInactiveInterval(30*60);
			Cookie cookie = new Cookie("username",user.getUsername());
			cookie.setMaxAge(30*60);
			response.addCookie(cookie);
			//Get the encoded URL String if the cookie is disabled in browser
			//Redirect back to the page where user click the login button
			String encodedURL = response.encodeRedirectURL("/CrowdFunding/index.jsp");
			response.sendRedirect(encodedURL);
		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("errorMessage", "Username or Password is not correct");
			rd.forward(request, response);
		}
	}
}
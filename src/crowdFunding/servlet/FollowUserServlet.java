package crowdFunding.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crowdFunding.model.*;

/**
 * Servlet implementation class FollowUserServlet
 */
@WebServlet("/FollowUserServlet")
public class FollowUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUsername(username);
		HttpSession session = request.getSession(false);
		User master = (User)session.getAttribute("user");
		FollowUserDao dao = new FollowUserDao();
		FollowUser follow = new FollowUser();
		follow.setMasterForUser(master);
		follow.setFollowedUser(user);
		dao.createFollowUser(follow);
		String success = "success";
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(success);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUsername(username);
		HttpSession session = request.getSession(false);
		User master = (User)session.getAttribute("user");
		FollowUserDao dao = new FollowUserDao();
		FollowUser follow = dao.findFollowByMaster(master, user);
		dao.deleteFollow(follow);
		String success = "success";
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(success);
	}
}
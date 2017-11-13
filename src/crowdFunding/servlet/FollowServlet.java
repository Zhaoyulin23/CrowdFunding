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
 * Servlet implementation class FollowServlet
 */
@WebServlet("/FollowServlet")
public class FollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowServlet() {
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
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao.findById(projectId);
		HttpSession session = request.getSession(false);
		User master = (User)session.getAttribute("user");
		FollowProjectDao dao = new FollowProjectDao();
		FollowProject follow = new FollowProject();
		follow.setFollowedProject(project);
		follow.setMasterForProject(master);
		dao.createFollowProject(follow);
		String success = "success";
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(success);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao.findById(projectId);
		HttpSession session = request.getSession(false);
		User master = (User)session.getAttribute("user");
		FollowProjectDao dao = new FollowProjectDao();
		FollowProject follow = dao.findFollowByMaster(master, project);
		dao.deleteFollow(follow);
		String success = "success";
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(success);
	}
}
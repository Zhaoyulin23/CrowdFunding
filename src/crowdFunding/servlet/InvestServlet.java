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
 * Servlet implementation class InvestServlet
 */
@WebServlet("/InvestServlet")
public class InvestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("username") == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("errorMessage", "You need login first");
			rd.forward(request, response);
		}
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao.findById(projectId);
		User user = (User)session.getAttribute("user");
		request.setAttribute("project", project);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/invest.jsp");
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int amount =  Integer.parseInt(request.getParameter("amount"));
		int projectId = Integer.parseInt(request.getParameter("projectId"));
		String type = request.getParameter("investType");
		InvestType investType = new InvestType();
		investType.setInvestType(type);
		ProjectDao projectDao = new ProjectDao();
		Project project = projectDao.findById(projectId);
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		if(amount > user.getCredit()) {
			request.setAttribute("project", project);
			request.setAttribute("errorMessage", "your credit is not enough for this funding amount");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/invest.jsp");
			rd.forward(request, response);
		}
		int left = user.getCredit() - amount;
		user.setCredit(left);
		UserDao userDao = new UserDao();
		String username = user.getUsername();
		userDao.updateUser(username, user);
		User newUser = userDao.findUserByUsername(username);
		
		long raised = project.getRaised() + (long)amount;
		project.setRaised(raised);
		projectDao.updateProject(projectId, project);
		Project newProject = projectDao.findById(projectId);
//		Create invest entity
		InvestDao investDao = new InvestDao();
		Invest invest = new Invest();
		invest.setAmount(amount);
		invest.setDate(new Date());
		invest.setInvestor(newUser);
		invest.setProject(newProject);
		//invest.setInvestType(investType);
		investDao.createInvest(invest);
		Message message = new Message();
		message.setAmount(amount);
		message.setSender(newProject.getOwner());
		message.setProject(newProject);
		message.setReceiver(newUser);
		message.setUnread(1);
		MessageDao msgDao = new MessageDao();
		msgDao.createMessage(message);
		
		
		session.setAttribute("user", newUser);
		session.setAttribute("username", newUser.getUsername());
		session.setMaxInactiveInterval(30*60);
		Cookie cookie = new Cookie("username",newUser.getUsername());
		cookie.setMaxAge(30*60);
		response.addCookie(cookie);
		request.setAttribute("project", newProject);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/project.jsp");
		rd.forward(request, response);
	}	
}
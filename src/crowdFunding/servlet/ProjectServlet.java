package crowdFunding.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import crowdFunding.model.*;

@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     *   THis servlet is to deal with project service, like create a project(doPost), look for a project(doGet), update a project(doPut) and delete a project(doDelete)
     */
    public ProjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectId = request.getParameter("projectId");
		ProjectDao dao = new ProjectDao();
		Project project = dao.findById(Integer.parseInt(projectId));
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/project.jsp");
		request.setAttribute("project", project);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String shortDescription = request.getParameter("shortDescription");
		String description = request.getParameter("description");
		long goal = Long.parseLong(request.getParameter("goal"));
		Date createdDate = new Date();
		Category category = new Category();
		if(request.getParameter("category") != null){
			category.setCategory(request.getParameter("category"));
		}
		String imageUrl = request.getParameter("imageUrl");
		HttpSession session = request.getSession(false);
		if(session == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("errorMessage", "You need login");
			rd.forward(request, response);
		}
		if((name ==null) || (shortDescription == null) || (description == null) || (category == null)){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/createProject.jsp");
			request.setAttribute("errorMessage", "You need to fill all fields");
			rd.forward(request, response);
		}
		User owner = (User) session.getAttribute("user");
		
		Project project = new Project();
		project.setName(name);
		project.setShortDescription(shortDescription);
		project.setDescription(description);
		project.setGoal(goal);
		project.setCreatedDate(createdDate);
		project.setCategory(category);
		project.setOwner(owner);
		project.setImageUrl(imageUrl);
		
		ProjectDao dao = new ProjectDao();
		int id = dao.createProject(project);
		Project newProject = dao.findById(id);
		request.setAttribute("project", newProject);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/project.jsp");
		rd.forward(request, response);
		
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String shortDescription = request.getParameter("shortDescription");
		String description = request.getParameter("description");
		long goal = Long.parseLong(request.getParameter("goal"));
		Date createdDate = new Date();
		Category category = new Category();
		if(request.getParameter("category") != null){
			category.setCategory(request.getParameter("category"));
		}
		String imageUrl = request.getParameter("imageUrl");
		HttpSession session = request.getSession(false);
		if(session.getAttribute("username") == null) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			request.setAttribute("errorMessage", "You need login first");
			rd.forward(request, response);
		}
		if((name ==null) || (shortDescription == null) || (description == null) || (category == null)){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/editProject.jsp");
			request.setAttribute("errorMessage", "You need to fill all fields");
			rd.forward(request, response);
		}
		User owner = (User) session.getAttribute("user");
		
		Project project = new Project();
		project.setName(name);
		project.setShortDescription(shortDescription);
		project.setDescription(description);
		project.setGoal(goal);
		project.setCreatedDate(createdDate);
		project.setCategory(category);
		project.setOwner(owner);
		project.setImageUrl(imageUrl);
		int id = Integer.parseInt(request.getParameter("projectId"));
		ProjectDao dao = new ProjectDao();
		dao.updateProject(id, project);
		Project newProject = dao.findById(id);
		request.setAttribute("project", newProject);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/project.jsp");
		rd.forward(request, response);		
	}	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
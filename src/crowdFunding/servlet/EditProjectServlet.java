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

import crowdFunding.model.Category;
import crowdFunding.model.Project;
import crowdFunding.model.ProjectDao;
import crowdFunding.model.User;

/**
 * Servlet implementation class EditProjectServlet
 */
@WebServlet("/EditProjectServlet")
public class EditProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProjectServlet() {
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
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/editProject.jsp");
			request.setAttribute("errorMessage", "You need to fill all fields");
			rd.forward(request, response);
		}
		User owner = (User) session.getAttribute("user");
		
		Project project = new Project();
		Project newProject = new Project();
		project.setName(name);
		project.setShortDescription(shortDescription);
		project.setDescription(description);
		project.setGoal(goal);
		project.setCreatedDate(createdDate);
		project.setCategory(category);
		project.setOwner(owner);
		project.setImageUrl(imageUrl);
		int id = Integer.parseInt(request.getParameter("projectId"));
		System.out.println(id);
		ProjectDao dao = new ProjectDao();
		dao.updateProject(id, project);
		newProject = dao.findById(id);
		request.setAttribute("project", newProject);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/project.jsp");
		rd.forward(request, response);
	}

}

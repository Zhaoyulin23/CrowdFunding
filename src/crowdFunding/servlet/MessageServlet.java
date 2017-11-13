package crowdFunding.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crowdFunding.model.*;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		User receiver = new User();
		UserDao userDao = new UserDao();
		receiver = userDao.findUserByUsername(username);
		List<Message>unreadMessages = new ArrayList<Message>();
		MessageDao dao = new MessageDao();
		unreadMessages = dao.findAllUnreadMessage(receiver);
		if(unreadMessages != null) {
			for(Message msg: unreadMessages){
				int msgId = msg.getId();
				msg.setUnread(0);
				dao.update(msgId, msg);
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/message.jsp");
		request.setAttribute("msgs", unreadMessages);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
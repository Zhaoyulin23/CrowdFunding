package crowdFunding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MessageDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;
	
	public MessageDao() {
		super();
	}
	
	
	public void createMessage(Message msg) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(msg);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Message> findAllUnreadMessage(User receiver) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		List<Message> msgs = new ArrayList();
		Query q = em.createNamedQuery("Message.findUnreadMessages");
		msgs = q.getResultList();
		em.close();
		return msgs;
	}
	
	public void update(int msgId, Message msg) {
		em =factory.createEntityManager();
		em.getTransaction().begin();
		msg.setId(msgId);
		em.merge(msg);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public static void main(String[] args) {

		Message msg = new Message();
		UserDao userDao = new UserDao();
		ProjectDao projectDao = new ProjectDao();
		Project project = new Project();
		project = projectDao.findById(1);
		User user1 = userDao.findUserByUsername("rachel");
		User user2 = userDao.findUserByUsername("leo");
		MessageDao dao = new MessageDao();
		msg.setSender(user1);
		msg.setReceiver(user2);
		msg.setAmount(100);
		msg.setUnread(1);
		msg.setProject(project);
		dao.createMessage(msg);		
	}
}

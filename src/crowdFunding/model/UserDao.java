package crowdFunding.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class UserDao{
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;

	

	public UserDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createUser(User user){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public User findUserByUsername(String username){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		try{
			Query q = em.createNamedQuery("User.findByUsername");
			q.setParameter("username", username);
			User user = (User) q.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return user;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public void updateUser(String username, User user) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		user.setUsername(username);
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<User> findAllUsers() {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("User.findAllUsers");
		List<User> users = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return users;
	}
	
//	public static void main(String[] args) {
//		
//		UserDao dao = new UserDao();
//		User user = new User();
//		user.setUsername("leomax");
//		user.setCredit(111111);
//		user.setFirstName("leo");
//		user.setLastName("ma");
//		user.setEmail("leo@gmail.com");
//		user.setPassword("111");
//		dao.createUser(user);
//		
//	}
}
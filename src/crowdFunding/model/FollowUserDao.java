package crowdFunding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FollowUserDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;
	
	

	public FollowUserDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createFollowUser(FollowUser follow) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(follow);
		em.getTransaction().commit();
		em.close();
	}
	
	public FollowUser findFollowByMaster (User master, User user) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		try{
			Query q = em.createNamedQuery("FollowUser.findByMaster");
			q.setParameter("master", master);
			q.setParameter("user", user);
			FollowUser follow = (FollowUser)q.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return follow;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<FollowUser> findAllFollow (User master) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("FollowUser.findAll");
		q.setParameter("master", master);
		List<FollowUser> follows = new ArrayList();
		follows = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return follows;
	}
	
	public void deleteFollow(FollowUser follow) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		FollowUser removedFollow = em.getReference(FollowUser.class, follow.getId());
		em.remove(removedFollow);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
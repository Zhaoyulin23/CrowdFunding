package crowdFunding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FollowProjectDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;

	public FollowProjectDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createFollowProject(FollowProject follow) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(follow);
		em.getTransaction().commit();
		em.close();
	}
	
	public FollowProject findFollowByMaster (User master, Project project) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		try{
			Query q = em.createNamedQuery("findByMaster");
			q.setParameter("master", master);
			q.setParameter("project", project);
			FollowProject follow = (FollowProject)q.getSingleResult();
			em.getTransaction().commit();
			em.close();
			return follow;
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<FollowProject> findAllFollow (User master) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("findAll");
		q.setParameter("master", master);
		List<FollowProject> follows = new ArrayList();
		follows = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return follows;
	}
	
	public void deleteFollow(FollowProject follow) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		FollowProject removedProject = em.getReference(FollowProject.class, follow.getId());
		em.remove(removedProject);
		em.getTransaction().commit();
		em.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
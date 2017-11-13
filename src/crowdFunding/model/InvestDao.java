package crowdFunding.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class InvestDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;
	
	
	
	public InvestDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void createInvest(Invest invest){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(invest);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Invest> findInvestByInvestor(User investor){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Invest.findInvestByInvestor");
		q.setParameter("investor", investor);
		List<Invest> invests = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return invests;
	}
	
//	public static void main(String[] args) {
//		UserDao userDao = new UserDao();
//		ProjectDao projectDao = new ProjectDao();
//		InvestDao investDao = new InvestDao();
//		User investor = userDao.findUserByUsername("rachel");
//		Project project = projectDao.findById(3);
//		Invest invest = new Invest();
//		invest.setInvestor(investor);
//		invest.setProject(project);
//		invest.setAmount(1000);
//		investDao.createInvest(invest);
//	}
}
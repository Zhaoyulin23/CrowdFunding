package crowdFunding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class InvestTypeDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;
	
	
	
	public InvestTypeDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void CreateInvestType(InvestType type) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(type);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<InvestType> findAll() {
		List<InvestType> types = new ArrayList();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("InvestType.findAll");
		types = q.getResultList();
		return types;
	}

	public static void main(String[] args) {
		InvestType type = new InvestType();
		type.setInvestType("Equity");
		InvestTypeDao dao = new InvestTypeDao();
		dao.CreateInvestType(type);
	}
}
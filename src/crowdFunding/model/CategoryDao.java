package crowdFunding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CategoryDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;
	
	public CategoryDao(){
		em = factory.createEntityManager();
	}
	
	public void CreateCategory(Category category){
		em.getTransaction().begin();
		em.persist(category);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Category> findAll(){
		List<Category> categories = new ArrayList();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Category.findAll");
		categories = q.getResultList();
		return categories;
	}
	
	public static void main(String[] args) {

		Category category  = new Category();
		category.setCategory("Medical");
		CategoryDao dao = new CategoryDao();
		dao.CreateCategory(category);
	}
}
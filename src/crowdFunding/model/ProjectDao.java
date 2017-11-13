package crowdFunding.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ProjectDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("CrowdFunding");
	EntityManager em = null;
	
//	public ProjectDao(){
//		em = factory.createEntityManager();
//	}
	
	
	public int createProject(Project project){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(project);
		em.flush();
		int id = project.getId();
		em.getTransaction().commit();
		em.close();
		return id;
	}
	
	public ProjectDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project findById(int id){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Project project = em.find(Project.class, id);
		em.getTransaction().commit();
		em.close();
		return project;
	}
	
	public List<Project> findProjectByOwner(User owner){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Project.findProjectByOwner");
		q.setParameter("owner", owner);
		List<Project> projects = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return projects;
	}
	
	public void updateProject(int id, Project project) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		project.setId(id);
		em.merge(project);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Project> findAllProject(){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Project.findAllProjects");
		List<Project> projects = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return projects;
	}
	
	public List<Project> search(String pt) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createNamedQuery("Project.search");
		q.setParameter("pattern", "%" + pt + "%");
		List<Project> projects = q.getResultList();
		em.getTransaction().commit();
		em.close();
		return projects;
	}
	
	
//	public static void main(String[] args) {
//		
//		ProjectDao dao = new ProjectDao();
//		Project project = dao.findById(1);
//		System.out.println(project.getGoal());
//		
//		
//		ProjectDao dao = new ProjectDao();
//		UserDao userDao = new UserDao();
//		Project project = new Project();
//		project.setName("jpa");
//		project.setDescription("CS5200 Project");
//		project.setGoal(800);
//		project.setRaised(0);
//		User owner = userDao.findUserByUsername("leo");
//		project.setOwner(owner);
//		dao.createProject(project);	
//		UserDao userdao = new UserDao();
//		ProjectDao projectDao = new ProjectDao();
//		List<Project> projects = new ArrayList<Project>();
//		User owner = new User();
//		owner = userdao.findUserByUsername("leo");
//		projects = projectDao.findProjectByOwner(owner);
//		for (Project project : projects) {
//			if(project != null)
//				System.out.println(project.getName());
//		}
//		
//		
//	}
}
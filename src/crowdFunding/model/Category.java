package crowdFunding.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="Category.findAll", query="select c from Category c")})
public class Category implements Serializable {

	private String category;
	private List<Project> projects = new ArrayList<Project>();
	
		
	@Id
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@OneToMany(mappedBy="category")
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	private static final long serialVersionUID = 1L;

	public Category() {
		super();
	}   
}
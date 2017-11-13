package crowdFunding.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Project
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="Project.findProjectByOwner", query="select p from Project p where p.owner = :owner"),
	@NamedQuery(name="Project.findAllProjects", query="select p from Project p"),
	@NamedQuery(name="Project.search", query="select p from Project p where p.name LIKE :pattern")})
public class Project<String, Date> implements Serializable {

	private int id;
	private String name;
	private String shortDescription;
	private String description;
	private long goal;
	private long raised;
	private Date createdDate;
	private Category category;
	private User owner;
	private String imageUrl; 
	private List<Invest> invests = new ArrayList<Invest>();
	private List<FollowProject> followedProjects = new ArrayList<FollowProject>();


	private static final long serialVersionUID = 1L;

	public Project() {
		super();
	}


	@OneToMany(mappedBy="followedProject")
	public List<FollowProject> getFollowedProjects() {
		return followedProjects;
	}



	public void setFollowedProjects(List<FollowProject> followedProjects) {
		this.followedProjects = followedProjects;
	}



	@Column(columnDefinition="TEXT")
	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
			return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getShortDescription() {
		return shortDescription;
	}


	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Column(columnDefinition="TEXT")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getGoal() {
		return goal;
	}

	public void setGoal(long goal) {
		this.goal = goal;
	}

	public long getRaised() {
		return raised;
	}

	public void setRaised(long raised) {
		this.raised = raised;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@ManyToOne
	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	//Foreign key reference User(username)
	@ManyToOne
	@JoinColumn(name="OWNER")
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@OneToMany(mappedBy="project")
	public List<Invest> getInvests() {
		return invests;
	}


	public void setInvests(List<Invest> invests) {
		this.invests = invests;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
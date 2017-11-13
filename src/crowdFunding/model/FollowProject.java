package crowdFunding.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FollowProject
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="findByMaster", query="select f from FollowProject f where f.masterForProject = :master and f.followedProject = :project"),
				@NamedQuery(name="findAll", query="select f from FollowProject f where f.masterForProject = :master")})
public class FollowProject implements Serializable {

	private int id;
	private User masterForProject;
	private Project followedProject;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public User getMasterForProject() {
		return masterForProject;
	}

	public void setMasterForProject(User masterForProject) {
		this.masterForProject = masterForProject;
	}

	@ManyToOne
	public Project getFollowedProject() {
		return followedProject;
	}

	public void setFollowedProject(Project followedProject) {
		this.followedProject = followedProject;
	}

	private static final long serialVersionUID = 1L;

	public FollowProject() {
		super();
	}   
}
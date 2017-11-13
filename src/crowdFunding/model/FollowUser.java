package crowdFunding.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FollowUser
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="FollowUser.findByMaster", query="select f from FollowUser f where f.masterForUser = :master and f.followedUser = :user"),
	@NamedQuery(name="FollowUser.findAll", query="select f from FollowUser f where f.masterForUser = :master")})
public class FollowUser implements Serializable {

	private int id;
	private User masterForUser;
	private User followedUser;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public User getMasterForUser() {
		return masterForUser;
	}

	public void setMasterForUser(User masterForUser) {
		this.masterForUser = masterForUser;
	}

	@ManyToOne
	public User getFollowedUser() {
		return followedUser;
	}

	public void setFollowedUser(User followedUser) {
		this.followedUser = followedUser;
	}



	private static final long serialVersionUID = 1L;

	public FollowUser() {
		super();
	}   
}
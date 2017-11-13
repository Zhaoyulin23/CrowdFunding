package crowdFunding.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: user
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="User.findByUsername", query="SELECT u FROM User u WHERE u.username = :username"),
				@NamedQuery(name="User.findAllUsers", query="select u from User u")})

public class User implements Serializable {

	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private int credit;
	private String password;
	private Date createdDate;
	private String imageUrl;
	private List<Project> CreatedProjects = new ArrayList<Project>();
	private List<Invest> invests = new ArrayList<Invest>();
	private List<FollowUser> followUsers = new ArrayList<FollowUser>();
	private List<FollowUser> followedUsers = new ArrayList<FollowUser>();
	private List<FollowProject> followProjects = new ArrayList<FollowProject>();
	private List<Message> messagesForSender = new ArrayList<Message>();
	private List<Message> messagesForReceiver = new ArrayList<Message>();

	

	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}

	
	@OneToMany(mappedBy="sender")
	public List<Message> getMessagesForSender() {
		return messagesForSender;
	}



	public void setMessagesForSender(List<Message> messagesForSender) {
		this.messagesForSender = messagesForSender;
	}


	@OneToMany(mappedBy="receiver")
	public List<Message> getMessagesForReceiver() {
		return messagesForReceiver;
	}



	public void setMessagesForReceiver(List<Message> messagesForReceiver) {
		this.messagesForReceiver = messagesForReceiver;
	}



	@OneToMany(mappedBy="masterForProject")
	public List<FollowProject> getFollowProjects() {
		return followProjects;
	}



	public void setFollowProjects(List<FollowProject> followProjects) {
		this.followProjects = followProjects;
	}



	@OneToMany(mappedBy="followedUser")
	public List<FollowUser> getFollowedUsers() {
		return followedUsers;
	}



	public void setFollowedUsers(List<FollowUser> followedUsers) {
		this.followedUsers = followedUsers;
	}



	@OneToMany(mappedBy="masterForUser")
	public List<FollowUser> getFollowUsers() {
		return followUsers;
	}

	public void setFollowUsers(List<FollowUser> followUser) {
		this.followUsers = followUser;
	}

	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Id
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	@OneToMany(mappedBy = "owner")
	public List<Project> getCreatedProjects() {
		return CreatedProjects;
	}


	public void setCreatedProjects(List<Project> createdProjects) {
		CreatedProjects = createdProjects;
	}
	
	
	@OneToMany(mappedBy="investor")
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
     


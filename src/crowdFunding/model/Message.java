package crowdFunding.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Message
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="Message.findUnreadMessages", query="select m from Message m where m.unread = 1")})
public class Message implements Serializable {

	private int id;
	private User sender;
	private User receiver;
	private Project project;
	private int amount;
	private int unread;
	
	
	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@ManyToOne
	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}



	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	}   
}
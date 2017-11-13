package crowdFunding.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Invest
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="Invest.findInvestByInvestor", query="select i from Invest i where i.investor = :investor")})
public class Invest implements Serializable {

	private int id;
	private int amount;
	private User investor;
	private Project project;
	private Date date;
	private InvestType investType;
	
	@ManyToOne
	public InvestType getInvestType() {
		return investType;
	}

	public void setInvestType(InvestType investType) {
		this.investType = investType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@ManyToOne
	public User getInvestor() {
		return investor;
	}

	public void setInvestor(User investor) {
		this.investor = investor;
	}
	@ManyToOne
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	public Invest() {
		super();
	}   
}
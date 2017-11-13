package crowdFunding.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: InvestType
 *
 */
@Entity
@NamedQueries({@NamedQuery(name="InvestType.findAll", query="select t from InvestType t")})
public class InvestType implements Serializable {

	private String investType;
	private List<Invest> invests = new ArrayList<Invest>();
	
	
	@Id
	public String getInvestType() {
		return investType;
	}
	
	public void setInvestType(String investType) {
		this.investType = investType;
	}

	@OneToMany(mappedBy="investType")
	public List<Invest> getInvests() {
		return invests;
	}

	public void setInvests(List<Invest> invests) {
		this.invests = invests;
	}

	private static final long serialVersionUID = 1L;

	public InvestType() {
		super();
	}  
}
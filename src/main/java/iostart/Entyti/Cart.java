package iostart.Entyti;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "Cart")

@NamedQuery(name = "Cart.findAll",query = "SELECT c FROM Cart c")
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "userid")
	private int userid;
	
	@Column(name = "buyDate")
	private String buyDate;
	
	@Column(name = "status")
	private int status;
	
	@OneToOne
	@JoinColumn(name = "userid",insertable = false, updatable = false)
	private Users user;

	public Cart() {
		super();
	}

	public Cart(int id, int userid, String buyDate, int status) {
		super();
		this.id = id;
		this.userid = userid;
		this.buyDate = buyDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
}

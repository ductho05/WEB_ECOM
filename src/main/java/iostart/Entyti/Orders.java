package iostart.Entyti;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity

@Table(name = "Orders")

@NamedQuery(name = "Orders.findAll",query = "SELECT o FROM Orders o Order by o.created DESC")
public class Orders implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "paid")
	private boolean paid;
	
	@Column(name = "created")
	private String created;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "total_price")
	private double total_price;
	
	@Column(name = "user_id")
	private int user_id;

	public Orders() {
		super();
	}

	public Orders(int id, String name, String address, String phone, String city, boolean paid, String created,
			String status, double total_price, int user_id) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.city = city;
		this.paid = paid;
		this.created = created;
		this.status = status;
		this.total_price = total_price;
		this.user_id = user_id;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}

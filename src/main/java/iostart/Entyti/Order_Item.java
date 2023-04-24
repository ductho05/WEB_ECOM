package iostart.Entyti;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "Order_Item")

@NamedQuery(name = "Order_Item.findAll", query = "SELECT oi FROM Order_Item oi")
public class Order_Item implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "total")
	private float total;
	
	@Column(name = "order_id", nullable = false)
	private int order_id;
	
	@Column(name = "product_id")
	private int product_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "order_id",insertable = false, updatable = false)
	private Orders orders;

	public Order_Item() {
		super();
	}

	public Order_Item(int id, int quantity, float total, int order_id, int product_id, Product product, Orders orders) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.total = total;
		this.order_id = order_id;
		this.product_id = product_id;
		this.product = product;
		this.orders = orders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	
}

package iostart.Entyti;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "CartItem")
@NamedQuery(name="CartItem.findAll",query = "SELECT ci FROM CartItem ci")
public class CartItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "unitprice")
	private float unitprice;
	
	@Column(name ="productid")
	private int productid;
	
	@Column(name = "cartid")
	private int cartid;
	
	@OneToOne
	@JoinColumn(name = "productid",insertable = false, updatable = false)
	private Product product;
	
	@OneToOne
	@JoinColumn(name = "cartid",insertable = false, updatable = false)
	private Cart cart;

	public CartItem() {
		super();
	}

	public CartItem(int id, int quantity, float unitprice, int productid, int cartid) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.unitprice = unitprice;
		this.productid = productid;
		this.cartid = cartid;
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

	public float getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(float unitprice) {
		this.unitprice = unitprice;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public int getCartid() {
		return cartid;
	}

	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
}
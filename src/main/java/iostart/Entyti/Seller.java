package iostart.Entyti;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity

@Table(name = "Seller")

@NamedQuery(name = "Seller.findAll",query = "SELECT s FROM Seller s")
public class Seller implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name = "sellerid")
	private int sellerid;
	
	@Column(name = "sellername")
	private String sellername;
	
	@Column(name = "images")
	private String images;
	
	@Column(name = "bio")
	private String bio;
	
	@Column(name = "ownerid")
	private int ownerid;
	
	@Column(name = "isactive")
	private Boolean isactive;
	
	@Column(name = "isopen")
	private Boolean isopen;
	
	@Column(name = "createat")
	private Date createat;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ownerid", insertable = false, updatable = false)
	private Users user;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sellerid", insertable = false, updatable = false)
	private List<Product> products;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "sellerid", insertable = false, updatable = false)
	private List<Slides> slides;
	public List<Slides> getSlides() {
		return slides;
	}

	public void setSlides(List<Slides> slides) {
		this.slides = slides;
	}

	public Seller() {
		super();
	}

	public Seller(int sellerid, String sellername, String images, String bio, int ownerid, Boolean isactive,
			Boolean isopen, Date createat) {
		super();
		this.sellerid = sellerid;
		this.sellername = sellername;
		this.images = images;
		this.bio = bio;
		this.ownerid = ownerid;
		this.isactive = isactive;
		this.isopen = isopen;
		this.createat = createat;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Boolean getIsopen() {
		return isopen;
	}

	public void setIsopen(Boolean isopen) {
		this.isopen = isopen;
	}

	public Date getCreateat() {
		return createat;
	}

	public void setCreateat(Date createat) {
		this.createat = createat;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}

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

@Table(name = "Slides")

@NamedQuery(name = "Slides.findAll",query = "SELECT sl FROM Slides sl")
public class Slides implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "slideid")
	private int slideid;
	
	@Column(name = "slidedescription")
	private String slidedescription;
	
	@Column(name = "slideimages")
	private String slideimages;
	
	@Column(name = "sellerid")
	private int sellerid;

	public Slides() {
		super();
	}

	public Slides(int slideid, String slidedescription, String slideimages, int sellerid) {
		super();
		this.slideid = slideid;
		this.slidedescription = slidedescription;
		this.slideimages = slideimages;
		this.sellerid = sellerid;
	}

	public int getSlideid() {
		return slideid;
	}

	public void setSlideid(int slideid) {
		this.slideid = slideid;
	}

	public String getSlidedescription() {
		return slidedescription;
	}

	public void setSlidedescription(String slidedescription) {
		this.slidedescription = slidedescription;
	}

	public String getSlideimages() {
		return slideimages;
	}

	public void setSlideimages(String slideimages) {
		this.slideimages = slideimages;
	}

	public int getSellerid() {
		return sellerid;
	}

	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}

}

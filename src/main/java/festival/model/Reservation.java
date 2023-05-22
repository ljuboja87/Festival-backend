package festival.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private int purchasedTickets;
	
	@Column
	private double totalPrice;
	
	@ManyToOne
	private Festival festival;

	public Reservation() {
		super();
	}

	public Reservation(int purchasedTickets, double totalPrice, Festival festival) {
		super();
		this.purchasedTickets = purchasedTickets;
		this.totalPrice = totalPrice;
		this.festival = festival;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPurchasedTickets() {
		return purchasedTickets;
	}

	public void setPurchasedTickets(int purchasedTickets) {
		this.purchasedTickets = purchasedTickets;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}
}

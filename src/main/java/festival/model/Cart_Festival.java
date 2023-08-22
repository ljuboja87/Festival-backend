package festival.model;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class Cart_Festival {
	
	@EmbeddedId
    CartFestivalid id = new CartFestivalid();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
	@MapsId("cartId")
	private Cart cart;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "festival_id", referencedColumnName = "id")
	@MapsId("festivalId")
	private Festival festival;

	@Column(name="no_tickets")
	private int noTickets;

	public Cart_Festival() {
		super();
	}

	public Cart_Festival(Cart cart, Festival festival, int noTickets) {
		this.id = new CartFestivalid(cart.getId(), festival.getId());
		this.cart = cart;
		this.festival = festival;
		this.noTickets = noTickets;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public int getNoTickets() {
		return noTickets;
	}

	public void setNoTickets(int noTickets) {
		this.noTickets = noTickets;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart_Festival other = (Cart_Festival) obj;
		return Objects.equals(id, other.id);
	}
}

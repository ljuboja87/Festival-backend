package festival.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	private Set<Cart_Festival> festivals = new HashSet<Cart_Festival>();

	@OneToOne(cascade=CascadeType.ALL)
	private User user;

	public Cart() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Cart_Festival> getFestivals() {
		return festivals;
	}

	public void setFestivals(Set<Cart_Festival> festivals) {
		this.festivals = festivals;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void addFestival(Festival festival, int noTickets) {
		Cart_Festival cart_Festival = new Cart_Festival(this, festival, noTickets);
        this.festivals.add(cart_Festival);
	}

	public void removeFestival(Long id) {
		for(Cart_Festival f: this.festivals) {
			if(f.getFestival().getId() == id) {
				this.festivals.remove(f);
				return;
			}
		}
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
		Cart other = (Cart) obj;
		return Objects.equals(id, other.id);
	}
}

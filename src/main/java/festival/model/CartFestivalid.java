package festival.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CartFestivalid implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long cartId;

	private Long festivalId;

	public CartFestivalid() {
		super();
	}

	public CartFestivalid(Long cartId, Long festivalId) {
		this.cartId = cartId;
		this.festivalId = festivalId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cartId, festivalId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartFestivalid other = (CartFestivalid) obj;
		return Objects.equals(cartId, other.cartId) && Objects.equals(festivalId, other.festivalId);
	}
}

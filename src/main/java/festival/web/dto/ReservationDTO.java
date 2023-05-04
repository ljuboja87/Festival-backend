package festival.web.dto;

public class ReservationDTO {
	
	private Long id;
	
	private int purchasedTickets;
	
	private double totalPrice;
	
	private FestivalDTO festival;

	public ReservationDTO() {
		super();
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

	public FestivalDTO getFestival() {
		return festival;
	}

	public void setFestival(FestivalDTO festival) {
		this.festival = festival;
	}
}

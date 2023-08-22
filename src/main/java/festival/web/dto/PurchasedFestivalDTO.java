package festival.web.dto;

public class PurchasedFestivalDTO {

	private Long id;
	
	private Long festivalId;
	
	private int noTickets;

	private String userName;

	public PurchasedFestivalDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFestivalId() {
		return festivalId;
	}

	public void setFestivalId(Long festivalId) {
		this.festivalId = festivalId;
	}

	public int getNoTickets() {
		return noTickets;
	}

	public void setNoTickets(int noTickets) {
		this.noTickets = noTickets;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}

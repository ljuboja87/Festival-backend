package festival.web.dto;


public class Cart_FestivalDto {
	
	private int noTickets;

	private FestivalDTO festival;

	public int getNoTickets() {
		return noTickets;
	}

	public void setNoTickets(int noTickets) {
		this.noTickets = noTickets;
	}

	public FestivalDTO getFestival() {
		return festival;
	}

	public void setFestival(FestivalDTO festival) {
		this.festival = festival;
	}
}

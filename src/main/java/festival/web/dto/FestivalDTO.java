package festival.web.dto;

import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public class FestivalDTO {
	
	private Long id;
	
	@Length(max = 50)
	private String name;
	
	private String startDate;
	
	private String endDate;
	
	@Positive(message = "Price is negative value!")
	private double price;
	
	private int numberOfAvailableTickets;
	
	private VenueDTO venue;

	public FestivalDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfAvailableTickets() {
		return numberOfAvailableTickets;
	}

	public void setNumberOfAvailableTickets(int numberOfAvailableTickets) {
		this.numberOfAvailableTickets = numberOfAvailableTickets;
	}

	public VenueDTO getVenue() {
		return venue;
	}

	public void setVenue(VenueDTO venue) {
		this.venue = venue;
	}
}

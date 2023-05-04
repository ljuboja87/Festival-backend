package festival.web.dto;

import org.hibernate.validator.constraints.Length;

public class VenueDTO {
	
	private Long id;
	
	private String city;
	
	@Length(max = 3)
	private String country;

	public VenueDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}

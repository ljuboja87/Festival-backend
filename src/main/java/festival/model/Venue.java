package festival.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String city;
	
	@Column(nullable = false, length = 3)
	private String country;
	
	@OneToMany(mappedBy = "venue", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Festival> festivals = new ArrayList<>();

	public Venue() {
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

	public List<Festival> getFestivals() {
		return festivals;
	}

	public void setFestivals(List<Festival> festivals) {
		this.festivals = festivals;
	}
	
	public void removeFestival(Long id) {
		for(Festival f: this.festivals) {
			if(f.getId() == id) {
				this.festivals.remove(f);
				return;
			}
		}
	}
	
	public void addFestival(Festival festival) {
		this.festivals.add(festival);
	}
}

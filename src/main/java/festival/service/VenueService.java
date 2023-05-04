package festival.service;

import java.util.List;
import java.util.Optional;

import festival.model.Venue;

public interface VenueService {	
	
	List<Venue> findAll();
	Optional<Venue> findOne(Long id);
	Venue save(Venue venue);
}

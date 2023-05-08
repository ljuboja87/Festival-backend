package festival.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festival.model.Venue;
import festival.repository.VenueRepository;
import festival.service.VenueService;

@Service
public class JpaVenueService implements VenueService {
	
	@Autowired
	private VenueRepository venueRepository;
	
	@Override
	public List<Venue> findAll() {
		return venueRepository.findAll();
	}

	@Override
	public Optional<Venue> findOne(Long id) {
		return venueRepository.findById(id);
	}

	@Override
	public Venue save(Venue venue) {
		return venueRepository.save(venue);
	}
}

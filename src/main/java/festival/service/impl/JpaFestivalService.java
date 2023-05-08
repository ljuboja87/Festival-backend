package festival.service.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import festival.model.Festival;
import festival.model.Reservation;
import festival.model.Venue;
import festival.repository.FestivalRepository;
import festival.repository.ReservationRepository;
import festival.repository.VenueRepository;
import festival.service.FestivalService;
import festival.web.dto.FestivalDTO;

@Service
@Transactional
public class JpaFestivalService implements FestivalService {
	
	@Autowired
	private FestivalRepository festivalRepository;
	
	@Autowired
	private VenueRepository venueRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public Page<Festival> search(String name, Long venueId, Integer pageNo) {
		
		if(name != null) {
			name = "%" + name + "%";
		}
		
		return festivalRepository.search(name, venueId, PageRequest.of(pageNo, 3));
	}

	@Override
	public Page<Festival> findAll(Integer pageNo) {
		return festivalRepository.findAll(PageRequest.of(pageNo, 3));
	}

	@Override
	public Optional<Festival> findOne(Long id) {
		return festivalRepository.findById(id);
	}

	@Override
	public Festival save(FestivalDTO festivalDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Festival delete(Long id) {
		
		Optional<Festival> festivalOptional = festivalRepository.findById(id);
		if(festivalOptional.isPresent()) {
			Festival festival = festivalOptional.get();
			Venue venue = festival.getVenue();
			venue.removeFestival(festival.getId());
			venueRepository.save(venue);
			List<Reservation> reservations = festival.getReservations();
			reservationRepository.deleteAll(reservations);
			festivalRepository.deleteById(id);
			return festival;
		}
		return null;
	}
}

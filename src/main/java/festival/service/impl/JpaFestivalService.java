package festival.service.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import festival.model.Cart;
import festival.model.Cart_Festival;
import festival.model.Festival;
import festival.model.Reservation;
import festival.model.User;
import festival.model.Venue;
import festival.repository.CartFestivalRepository;
import festival.repository.FestivalRepository;
import festival.repository.ReservationRepository;
import festival.repository.UserRepository;
import festival.repository.VenueRepository;
import festival.service.FestivalService;
import festival.support.FestivalDtoToFestival;
import festival.web.dto.Cart_FestivalDto;
import festival.web.dto.FestivalDTO;

@Service
@Transactional
public class JpaFestivalService implements FestivalService {

	@Autowired
	private FestivalRepository festivalRepository;

	@Autowired
	private CartFestivalRepository cartFestivalRepository;

	@Autowired
	private FestivalService festivalService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VenueRepository venueRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private FestivalDtoToFestival toFestival;

	@Override
	public Page<Festival> search(String name, Long venueId, Integer pageNo) {

		if(name != null) {
			name = "%" + name + "%";
		}

		return festivalRepository.search(name, venueId, PageRequest.of(pageNo, 10));
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

		Festival festival = toFestival.convert(festivalDTO);

		if(festivalDTO.getId() != null) {
			Festival oldFestival = findOne(festivalDTO.getId()).get();
			if(oldFestival != null) {
				Venue oldVenue = oldFestival.getVenue();
				oldVenue.removeFestival(oldFestival.getId());
				venueRepository.save(oldVenue);
			}
		}

		Venue venue = festival.getVenue();
		venue.addFestival(festival);
		Festival savedFestival = festivalRepository.save(festival);
		venueRepository.save(venue);
		return savedFestival;
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

	@Override
	@Transactional
	public Festival changing(String userName, List<Cart_FestivalDto> cart_FestivalDtos) {

		User user = userRepository.findFirstByUserName(userName).get();
		Cart cart = user.getCart();
		
		for(Cart_Festival cf: cart.getFestivals()) {
			Festival festival = festivalService.findOne(cf.getFestival().getId()).get();
			if(festival.getAvailableTickets() > 0 && festival.getAvailableTickets() >= cf.getNoTickets()) {
				Reservation reservation = new Reservation(0, 0.0, festival, user);
				reservation.setPurchasedTickets(cf.getNoTickets());
				double newTotalPrice = reservation.getPurchasedTickets() * festival.getPrice();
				reservation.setTotalPrice(newTotalPrice);
				reservationRepository.save(reservation);
				int newNoAvailableTickets = festival.getAvailableTickets() - cf.getNoTickets();
				festival.setAvailableTickets(newNoAvailableTickets);
				festival.addReservations(reservation);
				festivalRepository.save(festival);
			}
		}
		cartFestivalRepository.deleteAll(cart.getFestivals());
		return null;
	}
}

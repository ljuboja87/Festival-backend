package festival.service;

import java.util.List;
import java.util.Optional;

import festival.model.Reservation;

public interface ReservationService {
	
	List<Reservation> findAll();
	Optional<Reservation> findOne(Long id);
	Reservation save(Reservation reservation);
}

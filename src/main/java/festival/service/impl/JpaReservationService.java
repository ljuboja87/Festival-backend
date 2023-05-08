package festival.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import festival.model.Reservation;
import festival.repository.ReservationRepository;
import festival.service.ReservationService;

@Service
public class JpaReservationService implements ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> findAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Optional<Reservation> findOne(Long id) {
		return reservationRepository.findById(id);
	}

	@Override
	public Reservation save(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

}

package festival.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import festival.model.Reservation;
import festival.service.ReservationService;
import festival.support.ReservationToReservationDto;
import festival.web.dto.ReservationDTO;

@RestController
@Validated
@RequestMapping(value = "/api/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationsController {

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private ReservationToReservationDto toReservationDto;

	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<ReservationDTO> getOne(@PathVariable Long id) {

		Optional<Reservation> optionalReservation = reservationService.findOne(id);
		if(optionalReservation != null) {
			return new ResponseEntity<>(toReservationDto.convert(optionalReservation.get()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}

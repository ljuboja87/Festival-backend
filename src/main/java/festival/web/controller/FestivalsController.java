package festival.web.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import festival.model.Festival;
import festival.repository.ReservationRepository;
import festival.service.FestivalService;
import festival.support.FestivalToFestivalDto;
import festival.web.dto.Cart_FestivalDto;
import festival.web.dto.FestivalDTO;

@RestController
@Validated
@RequestMapping(value = "/festivals", produces = MediaType.APPLICATION_JSON_VALUE)
public class FestivalsController {

	@Autowired
	private FestivalService festivalService;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private FestivalToFestivalDto toFestivalDto;

	@GetMapping
	public ResponseEntity<List<FestivalDTO>> getAll(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Long venueId,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo) {

		Page<Festival> page = null;

		if(name != null || venueId != null) {
			page = festivalService.search(name, venueId, pageNo);
		} else {
			page = festivalService.findAll(pageNo);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

		return new ResponseEntity<>(toFestivalDto.convert(page.getContent()), headers, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<FestivalDTO> getOne(@PathVariable Long id) {
		Optional<Festival> festivalOptional = festivalService.findOne(id);

		if(festivalOptional != null) {
			Festival festival = festivalOptional.get();
			return new ResponseEntity<>(toFestivalDto.convert(festival), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {

		Festival deletedFestival = festivalService.delete(id);

		if(deletedFestival != null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FestivalDTO> create(@Validated @RequestBody FestivalDTO festivalDTO) {

		Festival savedFestival = festivalService.save(festivalDTO);
		return new ResponseEntity<>(toFestivalDto.convert(savedFestival), HttpStatus.CREATED);
	}

	//@PreAuthorize("hasRole('USER')")
	@PostMapping(value = "/{userName}/make_reservation")
	public ResponseEntity<Void> changeReservation(@PathVariable String userName, @RequestBody List<Cart_FestivalDto> cart_FestivalDtos) {

		Festival festival = festivalService.changing(userName, cart_FestivalDtos);

		if(festival != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FestivalDTO> update(@PathVariable Long id, @Valid @RequestBody FestivalDTO festivalDTO) {

		if(!id.equals(festivalDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Festival festival = festivalService.findOne(id).get();

		if(festival.getVenue() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Festival savedFestival = festivalService.save(festivalDTO);
		return new ResponseEntity<>(toFestivalDto.convert(savedFestival), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/{id}/totalIncome")
	public ResponseEntity<Double> totalIncome(@PathVariable Long id) {

		if(reservationRepository.totalIncome(id) == null) {
			double totalIncome = 0.0;
			return new ResponseEntity<>(totalIncome, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(reservationRepository.totalIncome(id), HttpStatus.OK);
		}
	}

	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}

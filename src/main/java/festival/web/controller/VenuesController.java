package festival.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import festival.model.Venue;
import festival.service.VenueService;
import festival.support.VenueToVenueDto;
import festival.web.dto.VenueDTO;

@RestController
@Validated
@RequestMapping(value = "/venues", produces = MediaType.APPLICATION_JSON_VALUE)
public class VenuesController {
	
	@Autowired
	private VenueService venueService;
	
	@Autowired
	private VenueToVenueDto toVenueDto;

	//@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping
	public ResponseEntity<List<VenueDTO>> getAll() {
		
		List<Venue> venues = venueService.findAll();
		
		return new ResponseEntity<>(toVenueDto.convert(venues), HttpStatus.OK);
	}
}

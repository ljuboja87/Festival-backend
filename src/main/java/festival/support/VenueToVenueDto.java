package festival.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import festival.model.Venue;
import festival.web.dto.VenueDTO;

@Component
public class VenueToVenueDto implements Converter<Venue, VenueDTO> {

	@Override
	public VenueDTO convert(Venue venue) {
		
		VenueDTO venueDTO = new VenueDTO();
		
		venueDTO.setId(venue.getId());
		venueDTO.setCity(venue.getCity());
		venueDTO.setCountry(venue.getCountry());
		
		return venueDTO;
	}
	
	public List<VenueDTO> convert(List<Venue> venues) {
		
		List<VenueDTO> venueDTOs = new ArrayList<>();
		for(Venue v: venues) {
			venueDTOs.add(convert(v));
		}
		return venueDTOs;
	}
}

package festival.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import festival.model.Festival;
import festival.web.dto.FestivalDTO;

@Component
public class FestivalToFestivalDto implements Converter<Festival, FestivalDTO> {
	
	@Autowired
	private VenueToVenueDto toVenueDto;
	
	@Override
	public FestivalDTO convert(Festival festival) {
		
		FestivalDTO festivalDTO = new FestivalDTO();
		
		festivalDTO.setId(festival.getId());
		festivalDTO.setName(festival.getName());
		festivalDTO.setStartDate(festival.getStartDate().toString());
		festivalDTO.setEndDate(festival.getEndDate().toString());
		festivalDTO.setPrice(festival.getPrice());
		festivalDTO.setNumberOfAvailableTickets(festival.getAvailableTickets());
		festivalDTO.setVenue(toVenueDto.convert(festival.getVenue()));
		
		return festivalDTO;
	}
	
	public List<FestivalDTO> convert(List<Festival> festivals) {
		
		List<FestivalDTO> festivalDTOs = new ArrayList<>();
		
		for(Festival f: festivals) {
			festivalDTOs.add(convert(f));
		}
		
		return festivalDTOs;
	}
}

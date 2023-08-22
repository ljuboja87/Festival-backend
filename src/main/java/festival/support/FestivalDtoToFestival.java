package festival.support;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import festival.model.Festival;
import festival.service.FestivalService;
import festival.service.VenueService;
import festival.web.dto.FestivalDTO;

@Component
public class FestivalDtoToFestival implements Converter<FestivalDTO, Festival> {
	
	@Autowired
	private FestivalService festivalService;
	
	@Autowired
	private VenueService venueService;
	
	@Override
	public Festival convert(FestivalDTO dto) {
		Festival festival;
		
		if(dto.getId() == null) {
			festival = new Festival();
		} else {
			festival = festivalService.findOne(dto.getId()).get();
		}
		
		if(festival != null) {
			festival.setId(dto.getId());
			festival.setName(dto.getName());
			festival.setStartDate(getLocalDate(dto.getStartDate()));
			festival.setEndDate(getLocalDate(dto.getEndDate()));
			festival.setPrice(dto.getPrice());
			festival.setAvailableTickets(dto.getAvailableTickets());
			festival.setDescription(dto.getDescription());
			festival.setVenue(venueService.findOne(dto.getVenue().getId()).get());
			
		}
		return festival;
	}
	
	private LocalDate getLocalDate(String sDate) throws DateTimeParseException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate startDate = LocalDate.parse(sDate, dtf);
		return startDate;
	}
}

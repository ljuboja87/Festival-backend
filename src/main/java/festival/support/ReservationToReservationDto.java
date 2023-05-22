package festival.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import festival.model.Reservation;
import festival.web.dto.ReservationDTO;

@Component
public class ReservationToReservationDto implements Converter<Reservation, ReservationDTO> {
	
	@Autowired
	private FestivalToFestivalDto toFestivalDto;
	
	@Override
	public ReservationDTO convert(Reservation reservation) {
		
		ReservationDTO reservationDTO = new ReservationDTO();
		
		reservationDTO.setId(reservation.getId());
		reservationDTO.setPurchasedTickets(reservation.getPurchasedTickets());
		reservationDTO.setTotalPrice(reservation.getTotalPrice());
		reservationDTO.setFestival(toFestivalDto.convert(reservation.getFestival()));
		
		return reservationDTO;
	}
	
	public List<ReservationDTO> converter(List<Reservation> reservations) {
		
		List<ReservationDTO> reservationDTOs = new ArrayList<>();
		
		for(Reservation r: reservations) {
			reservationDTOs.add(convert(r));
		}
		return reservationDTOs;
	}
}

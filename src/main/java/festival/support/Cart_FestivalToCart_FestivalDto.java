package festival.support;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import festival.model.Cart_Festival;
import festival.web.dto.Cart_FestivalDto;

@Component
public class Cart_FestivalToCart_FestivalDto implements Converter<Cart_Festival, Cart_FestivalDto> {
	
	@Autowired
	private FestivalToFestivalDto toFestivalDto;
	
	@Override
	public Cart_FestivalDto convert(Cart_Festival cart_Festival) {

		Cart_FestivalDto cart_FestivalDto = new Cart_FestivalDto();
		cart_FestivalDto.setNoTickets(cart_Festival.getNoTickets());
		cart_FestivalDto.setFestival(toFestivalDto.convert(cart_Festival.getFestival()));
		
		return cart_FestivalDto;
	}

	public Set<Cart_FestivalDto> convert(Set<Cart_Festival> cart_Festivals) {

		Set<Cart_FestivalDto> festivalDTOs = new HashSet<>();

		for(Cart_Festival f: cart_Festivals) {
			festivalDTOs.add(convert(f));
		}
		return festivalDTOs;
	}
}

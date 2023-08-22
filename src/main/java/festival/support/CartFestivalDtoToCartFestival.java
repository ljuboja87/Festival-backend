package festival.support;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import festival.model.Cart_Festival;
import festival.web.dto.Cart_FestivalDto;

@Component
public class CartFestivalDtoToCartFestival implements Converter<Cart_FestivalDto, Cart_Festival> {

	@Autowired
	private FestivalDtoToFestival toFestival;

	@Override
	public Cart_Festival convert(Cart_FestivalDto dto) {

		Cart_Festival cart_Festival = new Cart_Festival();
		cart_Festival.setFestival(toFestival.convert(dto.getFestival()));
		cart_Festival.setNoTickets(dto.getNoTickets());

		return cart_Festival;
	}

	public List<Cart_Festival> convert(List<Cart_FestivalDto> cart_FestivalDtos) {

		List<Cart_Festival> cart_Festivals = new ArrayList<>();

		for(Cart_FestivalDto cf: cart_FestivalDtos) {
			cart_Festivals.add(convert(cf));
		}
		return cart_Festivals;
	}
}

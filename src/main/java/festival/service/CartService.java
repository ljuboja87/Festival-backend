package festival.service;

import java.util.List;
import java.util.Set;
import festival.model.Cart;
import festival.model.Cart_Festival;
import festival.web.dto.Cart_FestivalDto;

public interface CartService {

	Set<Cart_Festival> findOne(String userName);
	Cart save(String UserName, List<Cart_FestivalDto> cart_FestivalDtos);
}
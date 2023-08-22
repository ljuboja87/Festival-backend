package festival.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import festival.model.Cart;
import festival.model.Cart_Festival;
import festival.model.User;
import festival.repository.CartFestivalRepository;
import festival.repository.CartRepository;
import festival.service.CartService;
import festival.service.UserService;
import festival.support.CartFestivalDtoToCartFestival;
import festival.web.dto.Cart_FestivalDto;

@Service
@Transactional
public class JpaCartService implements CartService {

	@Autowired
	private UserService userService;

	@Autowired
	private CartFestivalRepository cartFestivalRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartFestivalDtoToCartFestival toCartFestival;

	@Override
	public Set<Cart_Festival> findOne(String userName) {

		User user = userService.findByUserName(userName).get();
		Set<Cart_Festival> festivals = user.getCart().getFestivals();

		return festivals;
	}

	@Override
	@Transactional
	public Cart save(String userName, List<Cart_FestivalDto> cart_FestivalDtos) {

		List<Cart_Festival> cart_Festivals = toCartFestival.convert(cart_FestivalDtos);
		User user = userService.findByUserName(userName).get();
		Cart oldCart = user.getCart();
		Set<Cart_Festival> oldCartFestivals = oldCart.getFestivals();
		List<Cart_Festival> added = new ArrayList<Cart_Festival>();
		List<Cart_Festival> removed = new ArrayList<Cart_Festival>();
		if(!oldCart.getFestivals().isEmpty()) {
			oldCartFestivals.forEach(f -> {
				cart_Festivals.forEach(cf -> {
					if (f.getFestival().getId() == cf.getFestival().getId()) {
						f.setNoTickets(cf.getNoTickets());
					} else if(f.getFestival().getId() != cf.getFestival().getId()) {
						Cart_Festival cart_Festival = new Cart_Festival(oldCart, cf.getFestival(), cf.getNoTickets());
						added.add(cart_Festival);
					}
				});
			});

			for(Cart_Festival f: oldCartFestivals) {
				if(!cart_Festivals.stream().filter(cf -> cf.getFestival().getId().equals(f.getFestival().getId())).findFirst().isPresent()) {
					removed.add(f);
				}
			}
			oldCartFestivals.addAll(added);
			oldCartFestivals.removeAll(removed);
			cartFestivalRepository.deleteAll(removed);
			cartFestivalRepository.saveAll(oldCartFestivals);

			if(cart_Festivals.isEmpty()) {
				cartFestivalRepository.deleteAll(oldCartFestivals);
			}
			cartRepository.save(oldCart);
		} else {
			for(Cart_Festival z: cart_Festivals) {
				Cart_Festival cart_Festival = new Cart_Festival(oldCart, z.getFestival(), z.getNoTickets());
				oldCartFestivals.add(cart_Festival);
			}
			cartFestivalRepository.saveAll(oldCartFestivals);
			cartRepository.save(oldCart);
		}
		return null;
	}
}

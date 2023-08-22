package festival.web.controller;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import festival.model.Cart;
import festival.model.Cart_Festival;
import festival.service.CartService;
import festival.support.Cart_FestivalToCart_FestivalDto;
import festival.web.dto.Cart_FestivalDto;

@RestController
@Validated
@RequestMapping(value = "/carts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartsController {

	@Autowired
	private CartService cartService;

	@Autowired
	private Cart_FestivalToCart_FestivalDto toCart_FestivalDto;

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{userName}")
	public ResponseEntity<Set<Cart_FestivalDto>> getFestivals(@PathVariable String userName) {

		Set<Cart_Festival> festivals = cartService.findOne(userName);
		
		if(festivals == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(toCart_FestivalDto.convert(festivals), HttpStatus.OK);
		}
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/{userName}")
	public ResponseEntity<Void> update(@PathVariable String userName, @RequestBody List<Cart_FestivalDto> cart_FestivalDtos) {

		Cart savedCart = cartService.save(userName, cart_FestivalDtos);

		if(savedCart != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}











}

package festival.web.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import festival.model.User;
import festival.security.TokenUtils;
import festival.service.UserService;
import festival.support.UserDtoToUser;
import festival.support.UserToUserDto;
import festival.web.dto.AuthUserDTO;
import festival.web.dto.UserChangePasswordDTO;
import festival.web.dto.UserDTO;
import festival.web.dto.UserRegistrationDTO;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserDtoToUser toUser;

	@Autowired
	private UserToUserDto toUserDto;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	//@PreAuthorize("permitAll()")
	@PostMapping
	public ResponseEntity<UserDTO> create(@RequestBody @Validated UserRegistrationDTO dto){

		String name = dto.userName;

		if(dto.getId() != null || !dto.getPassword().equals(dto.getRepeatedPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		User user = toUser.convert(dto);

		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		user.setPassword(encodedPassword);

		return new ResponseEntity<>(toUserDto.convert(userService.save(user)), HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO){

		if(!id.equals(userDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User user = toUser.convert(userDTO);

		return new ResponseEntity<>(toUserDto.convert(userService.save(user)),HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable Long id){
		Optional<User> user = userService.findOne(id);

		if(user.isPresent()) {
			return new ResponseEntity<>(toUserDto.convert(user.get()), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserDTO>> get(@RequestParam(defaultValue="0") int page){
		Page<User> users = userService.findAll(page);
		return new ResponseEntity<>(toUserDto.converter(users.getContent()), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value="/{id}", method = RequestMethod.PUT, params = "changePassword")
	public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody UserChangePasswordDTO dto){

		if(!dto.getPassword().equals(dto.getRepeatedPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		boolean result;
		try {
			result = userService.changePassword(id, dto);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		if(result) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	//@PreAuthorize("permitAll()")
	@RequestMapping(path = "/auth", method = RequestMethod.POST)
	public ResponseEntity authenticateUser(@RequestBody AuthUserDTO dto) {
		// Perform the authentication
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		try {
			// Reload user details so we can generate token
			UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
			return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}

package festival.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import festival.model.User;
import festival.service.UserService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByUserName(username).orElse(null);

		if(user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%' .", username));
		} else {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			String role = "ROLE_" + user.getUserRole().toString();
			grantedAuthorities.add(new SimpleGrantedAuthority(role));

			return new org.springframework.security.core.userdetails.User(
					user.getUserName().trim(),
					user.getPassword().trim(),
					grantedAuthorities);
		}
	}
}

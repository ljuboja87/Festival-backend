package festival.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import festival.model.User;
import festival.service.UserService;
import festival.web.dto.UserDTO;

@Component
public class UserDtoToUser implements Converter<UserDTO, User> {
	
	@Autowired
	private UserService userService;
	
	@Override
	public User convert(UserDTO userDTO) {
		
		User user = null;
		
		if(userDTO.getId() == null) {
			user = new User();
		} else {
			user = userService.findOne(userDTO.getId()).get();
		}
		
		user.setUserName(userDTO.getUserName());
		user.seteMail(userDTO.geteMail());
		user.setName(userDTO.getName());
		user.setSurname(userDTO.getSurname());
		
		return user;
	}
}

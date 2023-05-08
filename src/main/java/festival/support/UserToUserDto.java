package festival.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import festival.model.User;
import festival.web.dto.UserDTO;

@Component
public class UserToUserDto implements Converter<User, UserDTO> {

	@Override
	public UserDTO convert(User user) {
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.seteMail(user.geteMail());
		userDTO.setName(user.getName());
		userDTO.setSurname(user.getSurname());
		userDTO.setUserName(user.getUserName());
		
		return userDTO;
	}
	
	public List<UserDTO> converter(List<User> users) {
		
		List<UserDTO> userDTOs = new ArrayList<>();
		
		for(User u: users) {
			userDTOs.add(convert(u));
		}
		
		return userDTOs;
	}
}

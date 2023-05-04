package festival.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import festival.model.User;
import festival.web.dto.UserChangePasswordDTO;

public interface UserService {
	
	Optional<User> findOne(Long id);
	Optional<User> findByUserName(String userName);
	List<User> findAll();
	Page<User> findAll(int noPage);
	User save(User user);
	void delete(Long id);
	boolean changePassword(Long id, UserChangePasswordDTO userChangePasswordDTO);
}

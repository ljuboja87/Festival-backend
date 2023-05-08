package festival.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import festival.enumeration.UserRole;
import festival.model.User;
import festival.repository.UserRepository;
import festival.service.UserService;
import festival.web.dto.UserChangePasswordDTO;

@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> findByUserName(String userName) {
		return userRepository.findFirstByUserName(userName);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findAll(int noPage) {
		return userRepository.findAll(PageRequest.of(noPage, 10));
	}

	@Override
	public User save(User user) {
		user.setUserRole(UserRole.USER);
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public boolean changePassword(Long id, UserChangePasswordDTO userChangePasswordDTO) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		User user = userOptional.get();
		
		if(!user.getUserName().equals(userChangePasswordDTO.getUserName())
				|| user.getPassword().equals(userChangePasswordDTO.getPassword())) {
			return false;
		}
		
		String password = userChangePasswordDTO.getPassword();
		if(!userChangePasswordDTO.equals("")) {
			password = passwordEncoder.encode(userChangePasswordDTO.getPassword());
		}
		
		user.setPassword(password);
		
		userRepository.save(user);
		
		return true;
	}
}

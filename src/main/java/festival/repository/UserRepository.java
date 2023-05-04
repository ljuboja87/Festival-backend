package festival.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import festival.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findFirstByUserName(String userName);
	Optional<User> findFirstByUserNameAndPassword(String userName, String password);
}

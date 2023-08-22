package festival.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import festival.model.CartFestivalid;
import festival.model.Cart_Festival;

@Repository
public interface CartFestivalRepository extends JpaRepository<Cart_Festival, CartFestivalid> {
	
}

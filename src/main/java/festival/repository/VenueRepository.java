package festival.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import festival.model.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

}

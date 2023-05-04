package festival.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import festival.model.Festival;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
	
	@Query("SELECT f FROM Festival WHERE " +
			"(:name = NULL OR f.name LIKE :name) AND " +
			"(:venueId = NULL OR f.venue.id = :venueId)")
	Page<Festival> search(@Param("name") String name, @Param("venueId") Long venueId, Pageable pageable);
}

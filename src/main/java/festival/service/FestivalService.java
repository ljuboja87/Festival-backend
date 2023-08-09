package festival.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import festival.model.Festival;
import festival.web.dto.FestivalDTO;

public interface FestivalService {
	
	Page<Festival> search(String name, Long venueId, Integer pageNo);
	Page<Festival> findAll(Integer pageNo);
	Optional<Festival> findOne(Long id);
	Festival save(FestivalDTO festivalDTO);
	Festival delete(Long id);
	Festival changing(Long id, Integer noTickets, String userName);
}

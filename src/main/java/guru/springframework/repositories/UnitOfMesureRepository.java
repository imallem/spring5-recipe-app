package guru.springframework.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.UnitOfMesure;

public interface UnitOfMesureRepository extends CrudRepository<UnitOfMesure, Long> {

	Optional<UnitOfMesure> findByDescription(String description);
	
}

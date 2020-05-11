package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.UnitOfMesure;

public interface UnitOfMesureRepository extends CrudRepository<UnitOfMesure, Long> {

}

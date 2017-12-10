package ch.unibe.eseteam2.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.eseteam2.model.Animal;

public interface AnimalRepository extends CrudRepository<Animal, String> {

}

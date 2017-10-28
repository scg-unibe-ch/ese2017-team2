package ch.unibe.eseteam2.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.eseteam2.model.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {

	public Driver findByName(String name);

}

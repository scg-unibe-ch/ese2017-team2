package ch.unibe.eseteam2.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.eseteam2.model.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {

	public Driver findByEmail(String email);
	
	public Driver findByEmailAndActive(String email, boolean active);

	public Iterable<Driver> findByActive(boolean active);

	public Driver findByIdAndActive(Long id, boolean active);


}

package ch.unibe.eseteam2.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.eseteam2.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
	
}

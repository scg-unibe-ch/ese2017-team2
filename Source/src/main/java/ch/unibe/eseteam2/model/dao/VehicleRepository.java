package ch.unibe.eseteam2.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.unibe.eseteam2.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
	
	@Query("SELECT v FROM Vehicle v WHERE v.available > 0")
	Iterable<Vehicle> findAvailable();
	
	@Query("SELECT v FROM Vehicle v WHERE v.available > 0 or v.id = :id")
	Iterable<Vehicle> findAvailable(@Param("id") Long id);

}

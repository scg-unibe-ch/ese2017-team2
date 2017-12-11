package ch.unibe.eseteam2.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ch.unibe.eseteam2.model.Vehicle;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

	Iterable<Vehicle> findByActive(Boolean active);

	@Query("SELECT v FROM Vehicle v WHERE v.active = 1 and v.used < v.count")
	Iterable<Vehicle> findAvailable();

	@Query("SELECT v FROM Vehicle v WHERE v.active = 1 and ( v.used < v.count or v.id = :id ) ")
	Iterable<Vehicle> findAvailableIncluding(@Param("id") Long id);

	Vehicle findByIdAndActive(Long vehicleId, Boolean active);

}

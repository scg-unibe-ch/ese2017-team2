package ch.unibe.eseteam2.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;

public interface TripRepository extends CrudRepository<Trip, Long> {
	public Iterable<Trip> findByTripState(TripState tripState);
}

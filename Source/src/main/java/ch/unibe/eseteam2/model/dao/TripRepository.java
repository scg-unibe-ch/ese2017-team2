package ch.unibe.eseteam2.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.unibe.eseteam2.model.Trip;

public interface TripRepository extends CrudRepository<Trip, Long> {

}

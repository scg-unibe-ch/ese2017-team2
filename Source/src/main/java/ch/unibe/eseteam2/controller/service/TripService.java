package ch.unibe.eseteam2.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Service
public class TripService {
	@Autowired
	private TripRepository tripRepository;

	public void updateTripStates() {
		for (Trip trip : tripRepository.findAll()) {
			trip.updateState();
			tripRepository.save(trip);
		}
	}
	
	
	/**
	 * Deletes a given trip.
	 * 
	 * @param trip
	 * @throws IllegalArgumentException in case the given trip is {@literal null}.
	 */
	public void deleteTrip(Trip trip) {
		tripRepository.delete(trip);
	}

	/**
	 * Retrieves a trip by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the trip with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	public Trip findTrip(Long id){
		return tripRepository.findOne(id);
	}
	
	public Iterable<Trip> findTrip(TripState state) {
		return tripRepository.findByTripState(state);
	}

	public Iterable<Trip> findTrip(Driver driver, TripState state) {
		return tripRepository.findByDriverAndTripState(driver, state);
	}

}

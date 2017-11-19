package ch.unibe.eseteam2.service;

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
	 * @throws IllegalArgumentException
	 *             in case the given trip is {@literal null}.
	 */
	public void deleteTrip(Trip trip) {
		tripRepository.delete(trip);
		trip.onDelete();
	}


	public Trip findTrip(Long id) throws Exception {
		Trip trip;

		if (id == null) {
			throw new Exception("No trip selected.");
		}

		trip = tripRepository.findOne(id);

		if (trip == null) {
			throw new Exception("Trip can not be found in database.");
		}
		return trip;
	}

	public Iterable<Trip> findTrips(TripState state) {
		return tripRepository.findByTripState(state);
	}

	public Iterable<Trip> findTrips(Driver driver, TripState state) {
		return tripRepository.findByDriverAndTripState(driver, state);
	}

	public void save(Trip trip) {
		tripRepository.save(trip);
	}

	public Iterable<Trip> findTrips(Driver driver) {
		return tripRepository.findByDriver(driver);
	}

}

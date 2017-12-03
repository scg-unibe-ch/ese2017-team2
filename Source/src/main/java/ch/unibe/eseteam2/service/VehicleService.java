package ch.unibe.eseteam2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.model.dao.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private TripService tripService;

	public Iterable<Vehicle> findVehicles() {
		return vehicleRepository.findAll();
	}

	public Iterable<Vehicle> findAvailableVehicles() {
		return vehicleRepository.findAvailable();
	}

	public Iterable<Vehicle> findAvailableVehicles(Trip trip) {
		if (trip != null && trip.getVehicle() != null) {
			Vehicle vehicle = trip.getVehicle();
			return vehicleRepository.findAvailableIncluding(vehicle.getId());
		}
		return findAvailableVehicles();
	}

	public Vehicle findVehicle(Long vehicleId) {
		return vehicleRepository.findOne(vehicleId);
	}

	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

	public void deleteVehicle(Vehicle vehicle) throws Exception {
		if (tripService.findTrips(vehicle, TripState.active).iterator().hasNext()) {
			throw new Exception("This vehicle is assigned to an active trip and can not be fired.");
		}

		// TODO don't remove vehicle from trips in successful state
		for (Trip trip : tripService.findTrips(vehicle)) {
			trip.setVehicle(null);
			tripService.save(trip);
		}
		
		vehicleRepository.delete(vehicle);
	}
}

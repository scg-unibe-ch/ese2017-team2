package ch.unibe.eseteam2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.model.dao.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;

	public Iterable<Vehicle> findVehicles() {
		return vehicleRepository.findAll();
	}

	public Iterable<Vehicle> findAvailableVehicles() {
		return vehicleRepository.findAvailable();
	}

	public Iterable<Vehicle> findAvailableVehicles(Trip trip) {
		if (trip != null && trip.getVehicle() != null) {
			Vehicle vehicle = trip.getVehicle();
			return vehicleRepository.findAvailable(vehicle.getId());
		}
		return findAvailableVehicles();
	}

	public Vehicle findVehicle(Long vehicleId) {
		return vehicleRepository.findOne(vehicleId);
	}
}

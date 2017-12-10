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

	public Vehicle findBestVehicle(Trip trip, int animaLength, int animalWidth, int animalCount) {
		Vehicle best = null;
		int maxCount = 0;
		int count;
		for (Vehicle current : findAvailableVehicles(trip)) {
			count = current.getMaxAnimals(animaLength, animalWidth);
			if (count > maxCount) {
				best = current;
			}
		}

		return best;
	}

	public Vehicle findBestVehicle(int animaLength, int animalWidth, int animalCount) {
		Vehicle best = null;
		int bestCount = 0;
		int count;
		for (Vehicle current : findAvailableVehicles()) {
			count = current.getMaxAnimals(animaLength, animalWidth);

			if (count >= animalCount) {
				if (bestCount < animalCount) {
					// first vehicle which is big enough
					bestCount = count;
					best = current;
				} else if (count < bestCount) {
					// smaller vehicle which is big enough
					bestCount = count;
					best = current;
				}
			}

		}

		return best;
	}

	public Vehicle findBiggestVehicle(Trip trip, int animalLength, int animalWidth) {
		Vehicle best = null;
		int bestCount = 0;
		int count;
		for (Vehicle current : findAvailableVehicles(trip)) {
			count = current.getMaxAnimals(animalLength, animalWidth);
			if (count > bestCount) {
				bestCount = count;
				best = current;
			}
		}
		return best;
	}

	public Vehicle findBiggestVehicle(int animalLength, int animalWidth) {
		Vehicle best = null;
		int bestCount = 0;
		int count;
		for (Vehicle current : findAvailableVehicles()) {
			count = current.getMaxAnimals(animalLength, animalWidth);
			if (count > bestCount) {
				bestCount = count;
				best = current;
			}
		}
		return best;
	}

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

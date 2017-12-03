package ch.unibe.eseteam2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.model.dao.DriverRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private TripService tripService;

	@Autowired
	private UserDetailsManager userDetailsManager;

	public Driver findDriver(Long id) {
		return driverRepository.findOne(id);
	}

	public Iterable<Driver> findDrivers() {
		return driverRepository.findAll();
	}

	public void fireDriver(Driver driver) throws Exception {
		if (tripService.findTrips(driver, TripState.active).iterator().hasNext()) {
			throw new Exception("This driver is assigned to an active trip and can not be fired.");
		}
		//TODO don't remove driver from trips in successful state 
		for (Trip trip : tripService.findTrips(driver)) {
			trip.setDriver(null);
			tripService.save(trip);
		}
		userDetailsManager.deleteUser(driver.getEmail());
		driverRepository.delete(driver);
	}

	public void save(Driver driver) {
		driverRepository.save(driver);
	}

	public Driver findDriver(String email) {
		return driverRepository.findByEmail(email);
	}
}

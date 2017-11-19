package ch.unibe.eseteam2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.model.dao.VehicleRepository;

@Service
public class VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;

	public Iterable<Vehicle> findVehicles() {
		return vehicleRepository.findAll();
	}

	public Iterable<Vehicle> findAvailableVehicles(){
		//TODO only return available vehicles
		return vehicleRepository.findAll();
	}

	public Vehicle findVehicle(Long vehicleId) {
		return vehicleRepository.findOne(vehicleId);
	}
}

package ch.unibe.eseteam2.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.dao.DriverRepository;

@Service
public class DriverService {
	@Autowired
	private DriverRepository driverRepository;
	
	public Driver findDriver(Long id) {
		return driverRepository.findOne(id);
	}
	
	public Iterable<Driver> findDrivers(){
		return driverRepository.findAll();
	}
}

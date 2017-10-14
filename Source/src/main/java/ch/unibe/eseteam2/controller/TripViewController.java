package ch.unibe.eseteam2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.model.Address;
import ch.unibe.eseteam2.model.Trip;

@Controller
public class TripViewController {

	@RequestMapping("/planner/view")
	public String plannerView(Model model) {
		
		//TODO retrieve trip from database
		Trip trip = new Trip();
		trip.setCustomer("test customer");
		
		Address startAddress = new Address();
		startAddress.setName("test name");
		startAddress.setStreet("test street");
		startAddress.setPlz(1234);
		startAddress.setCity("test city");
		trip.setStartAddress(startAddress );
		Address endAddress = new Address();
		trip.setEndAddress(endAddress );
		trip.setAnimal("Horse");
		trip.setAnimalCount(2);
		
		
		
		model.addAttribute("trip", trip);
		
		return "planner/view";
	}

	@RequestMapping("/driver/view")
	public String driverView() {

		return "driver/view";
	}
}

package ch.unibe.eseteam2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.model.Trip;

@Controller
public class TripViewController {

	@RequestMapping("/planner/view")
	public String plannerView(Model model) {
		
		//TODO retrieve trip from database
		Trip trip = new Trip();
		trip.setCustomer("test customer");
		
		trip.setName_1("test name");
		trip.setStreet_1("test street");
		trip.setPlz_1(1234);
		trip.setCity_1("test city");
		
		
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

package ch.unibe.eseteam2.controller.planner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.unibe.eseteam2.model.Trip;

@Controller
public class TripEditController {

	@GetMapping("/planner/edit")
	public String tripForm(Model model) {
		// TODO retrieve trip from database
		Trip trip = new Trip();
		trip.setCustomer("test customer");

		trip.setName_1("test name");
		trip.setStreet_1("test street");
		trip.setPlz_1(1234);
		trip.setCity_1("test city");

		trip.setAnimal("Horse");
		trip.setAnimalCount(2);

		model.addAttribute("trip", trip);
		return "planner/edit";
	}

	@PostMapping("/planner/edit")
	public String tripSubmit(@ModelAttribute Trip trip) {
		//TODO save trip in database
		return "planner/edit";
	}
}

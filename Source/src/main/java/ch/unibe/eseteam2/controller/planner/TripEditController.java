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
		//TODO retrieve trip from database
		Trip trip = new Trip();
		
		model.addAttribute("trip", trip);
		return "planner/edit";
	}
	
	@PostMapping("/planner/edit")
	public String tripSubmit(@ModelAttribute Trip trip) {
		//TODO implements this
				
		return "planner/edit";
	}
}

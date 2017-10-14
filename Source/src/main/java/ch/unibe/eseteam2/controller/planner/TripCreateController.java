package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ch.unibe.eseteam2.model.Trip;

@Controller
public class TripCreateController {

	@GetMapping("/planner/create")
	public String tripForm(Model model) {
		// create empty Trip
		Trip trip = new Trip();
		// send Trip
		model.addAttribute("trip", trip);

		return "planner/create";
	}

	@PostMapping("/planner/create")
	public String tripSubmit(@Valid Trip trip, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			//There is some invalid input, try again.
			return "/planner/create";
		}

		
		// TODO add trip to database

		//Input is valid, go back.
		return "redirect:/planner/list";
	}
}

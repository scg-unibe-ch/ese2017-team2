package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
public class TripEditController {

	@Autowired
	private TripRepository tripRepository;

	@GetMapping("/planner/edit")
	public String getMapping(@RequestParam Long id, Model model) {

		Trip trip = tripRepository.findOne(id);
		// TODO test for null or exceptions

		model.addAttribute("trip", trip);
		return "/planner/edit";
	}

	@PostMapping("/planner/edit")
	public String postMapping(@Valid Trip trip, BindingResult bindingResult) {
		// TODO save trip in database
		if (bindingResult.hasErrors()) {
			// There is some invalid input, try again.
			return "/planner/edit";
		}
		
		
		return "redirect:/planner/list";
	}
}

package ch.unibe.eseteam2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.unibe.eseteam2.model.Trip;

@Controller
public class MainController {
	private List<Trip> tripList = new ArrayList<Trip>();

	@GetMapping("/trip")
	public String tourForm(Model model) {
		model.addAttribute("trip", new Trip());
		return "trip";
	}

	@PostMapping("/trip")
	public String tourSubmit(@ModelAttribute Trip trip) {
		tripList.add(trip);
		return "result";
	}
}

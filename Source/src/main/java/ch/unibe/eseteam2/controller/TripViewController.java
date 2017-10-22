package ch.unibe.eseteam2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
public class TripViewController {
	@Autowired
	private TripRepository tripRepository;

	@RequestMapping("/planner/view/{id}")
	public String plannerView(@PathVariable Long id, Model model) {
		Trip trip = tripRepository.findOne(id);
		if (trip == null) {
			// TODO error handling
		}

		model.addAttribute("trip", trip);

		return "planner/view";
	}

	@RequestMapping("/driver/view/{id}")
	public String driverView(@PathVariable Long id, Model model) {
		Trip trip = tripRepository.findOne(id);
		if (trip == null) {
			// TODO error handling
		}

		model.addAttribute("trip", trip);

		return "driver/view";
	}
}

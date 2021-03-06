package ch.unibe.eseteam2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.form.TripViewForm;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.service.TripService;

@Controller
public class TripViewController {
	@Autowired
	private TripService tripService;

	@RequestMapping("/planner/view/{id}")
	public String plannerView(@PathVariable Long id, Model model) {
		try {
			addAttributes(id, model);
		} catch (Exception e) {
			
			model.addAttribute("error", e.getMessage());
		}

		return "planner/trip/view";
	}

	@RequestMapping("/driver/view/{id}")
	public String driverView(@PathVariable Long id, Model model) {
		try {
			addAttributes(id, model);
		} catch (Exception e) {
			
			model.addAttribute("error", e.getMessage());
			
		}

		return "driver/trip/view";
	}

	private void addAttributes(Long id, Model model) throws Exception {
		Trip trip = tripService.findTrip(id);

		model.addAttribute("trip", new TripViewForm(trip));
	}
}

package ch.unibe.eseteam2.controller.driver;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.form.ConfirmationForm;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.service.TripService;

@Controller
@RequestMapping("/driver/confirm/")
public class ConfirmationController {

	@Autowired
	TripService tripService;

	@GetMapping("{id}")
	public String getMapping(@PathVariable Long id, Model model) {
		
		try {
			tripService.findTrip(id);
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "driver/trip/confirm";
		}
		
		model.addAttribute("form", new ConfirmationForm());
		return "driver/trip/confirm";
	}

	@PostMapping("{id}")
	public String postMapping(@PathVariable Long id, @Valid @ModelAttribute(name = "form") ConfirmationForm form, BindingResult bindingResult, Model model) {
		Trip trip;

		try {
			trip = tripService.findTrip(id);
			if (trip.getTripState() != TripState.active) {
				throw new Exception("Trip is not in active state.");
			}
			updateTrip(trip, form);
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "driver/trip/confirm";
		}

		if (bindingResult.hasErrors()) {
			return "driver/trip/confirm";
		}

		return "redirect:/driver/list";
	}

	private void updateTrip(Trip trip, ConfirmationForm form) {
		if (form.isSuccess()) {
			trip.setTripState(TripState.successful);
			trip.updateUsedTime();
		} else {
			trip.setTripState(TripState.unsuccessful);
		}

		trip.setFeedback(form.getFeedback());

		tripService.save(trip);

	}
}

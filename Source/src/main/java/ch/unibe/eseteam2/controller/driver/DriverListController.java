package ch.unibe.eseteam2.controller.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
public class DriverListController {
	@Autowired
	private TripRepository tripRepository;

	@GetMapping("/driver/list")
	public String getMapping(Model model) {
		updateTripStates();
		addTripLists(model);
		return "driver/list";
	}

	@PostMapping("/driver/list")
	public String postMapping(@RequestParam(value = "action", required = true) String action, @RequestParam(value = "select", required = true) Long id, Model model) {
		if (action.equals("view")) {
			if (!tripRepository.exists(id)) {
				// trip does not exist
				// TODO error handling
			}
			return "redirect:/planner/view/" + id;
		} else {

			// TODO handle invalid action
		}
		
		updateTripStates();
		addTripLists(model);
		
		return "driver/list";
	}

	private void updateTripStates() {
		for (Trip trip : tripRepository.findAll()) {
			trip.updateState();
			tripRepository.save(trip);
		}
	}

	private Model addTripLists(Model model) {
		//TODO change these to use "tripRepository.findByDriverAndTripState(driver, tripState)".
		model.addAttribute("tripsAssigned", tripRepository.findByTripState(TripState.assigned));
		model.addAttribute("tripsExpired", tripRepository.findByTripState(TripState.expired));
		model.addAttribute("tripsActive", tripRepository.findByTripState(TripState.active));
		model.addAttribute("tripsSuccessful", tripRepository.findByTripState(TripState.successful));
		model.addAttribute("tripsUnsuccessful", tripRepository.findByTripState(TripState.unsuccessful));

		return model;
	}
}

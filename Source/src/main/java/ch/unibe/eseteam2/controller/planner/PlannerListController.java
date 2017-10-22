package ch.unibe.eseteam2.controller.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
@RequestMapping("/planner/list")
public class PlannerListController {
	@Autowired
	private TripRepository tripRepository;

	@GetMapping()
	public String getList(Model model) {

		addTripLists(model);
		return "planner/list";
	}

	@PostMapping()
	public String postForm(@RequestParam(value = "action", required = true) String action, @RequestParam(value = "select", required = true) Long id, Model model) {

		if (action.equals("edit")) {
			Trip trip = this.tripRepository.findOne(id);
			if (trip == null) {
				// trip does not exist
				// TODO error handling
			} else if (!trip.canEdit()) {
				// TODO error handling
			} else {
				return "redirect:/planner/edit/" + id;
			}

		} else if (action.equals("delete")) {
			Trip trip = this.tripRepository.findOne(id);
			if (trip == null) {
				// TODO handle error
			} else if (!trip.canDelete()) {
				// TODO handle error
			} else {
				deleteTrip(id);
			}

		} else if (action.equals("view")) {
			if (!tripRepository.exists(id)) {
				// trip does not exist
				// TODO error handling
			}
			return "redirect:/planner/view/" + id;
		} else {

			// TODO handle invalid action
		}

		addTripLists(model);

		return "/planner/list";
	}

	private Model addTripLists(Model model) {
		model.addAttribute("tripsEditing", tripRepository.findByTripState(TripState.editing));
		model.addAttribute("tripsAssigned", tripRepository.findByTripState(TripState.assigned));
		model.addAttribute("tripsExpired", tripRepository.findByTripState(TripState.expired));
		model.addAttribute("tripsActive", tripRepository.findByTripState(TripState.active));
		model.addAttribute("tripsSuccessful", tripRepository.findByTripState(TripState.successful));
		model.addAttribute("tripsUnsuccessful", tripRepository.findByTripState(TripState.unsuccessful));

		return model;
	}

	private void deleteTrip(Long id) {

		if (!tripRepository.exists(id)) {
			// trip does not exist
			// TODO error handling
		}

		tripRepository.delete(id);
	}
}

package ch.unibe.eseteam2.controller.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.controller.service.TripService;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;

@Controller
@RequestMapping("/planner/list")
public class PlannerListController {
	@Autowired
	private TripService tripService;

	@GetMapping()
	public String getList(Model model) {
		tripService.updateTripStates();
		addTripLists(model);
		return "planner/list";
	}

	@PostMapping()
	public String postForm(@RequestParam(value = "action", required = false) String action, @RequestParam(value = "select", required = false) Long id, Model model) {

		try {
			if (action == null) {
				throw new Exception("No action specified.");
			}
			if (action.equals("edit")) {

				return redirectEdit(id, action);

			} else if (action.equals("delete")) {

				deleteTrip(id, action);

			} else if (action.equals("view")) {

				return redirectView(id, action);

			} else {
				throw new Exception("Invalid action.");
			}

		} catch (Exception e) {
			// TODO display error message.
			// bindingResult.reject(e.getMessage());
		}

		tripService.updateTripStates();
		addTripLists(model);
		return "/planner/list";
	}

	private String redirectEdit(Long id, String action) throws TripSelectException {
		Trip trip = findTrip(id, action);

		if (!trip.canEdit()) {
			throw new TripSelectException(action, "Selected trip can not be edited.");
		}

		return "redirect:/planner/edit/" + id;
	}

	private String redirectView(Long id, String action) throws TripSelectException {
		findTrip(id, action);

		return "redirect:/planner/edit/" + id;
	}

	private void deleteTrip(Long id, String action) throws TripSelectException {
		Trip trip = findTrip(id, action);

		if (!trip.canDelete()) {
			throw new TripSelectException(action, "Not allowed to delete selected trip.");
		}

		tripService.deleteTrip(trip);
	}

	private Trip findTrip(Long id, String action) throws TripSelectException {
		Trip trip;

		if (id == null) {
			throw new TripSelectException(action, "No trip selected.");
		}

		trip = this.tripService.findTrip(id);

		if (trip == null) {
			throw new TripSelectException(action, "Selected trip can not be found in database.");
		}
		return trip;
	}

	private Model addTripLists(Model model) {
		model.addAttribute("tripsEditing", tripService.findTrip(TripState.editing));
		model.addAttribute("tripsAssigned", tripService.findTrip(TripState.assigned));
		model.addAttribute("tripsExpired", tripService.findTrip(TripState.expired));
		model.addAttribute("tripsActive", tripService.findTrip(TripState.active));
		model.addAttribute("tripsSuccessful", tripService.findTrip(TripState.successful));
		model.addAttribute("tripsUnsuccessful", tripService.findTrip(TripState.unsuccessful));

		return model;
	}
}

class TripSelectException extends Exception {
	private static final long serialVersionUID = 8814065565116231943L;

	private String action;
	private String message;

	public TripSelectException(String action, String message) {
		this.action = action;
		this.message = message;
	}

	public String getAction() {
		return action;
	}

	public String getMessage() {
		return message;
	}
}

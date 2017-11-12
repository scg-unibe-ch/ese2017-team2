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
import ch.unibe.eseteam2.service.TripService;

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

				return redirectEdit(id);

			} else if (action.equals("delete")) {

				deleteTrip(id);

			} else if (action.equals("view")) {

				return redirectView(id);

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

	private String redirectEdit(Long id) throws Exception {
		Trip trip = tripService.findTrip(id);

		if (!trip.canEdit()) {
			throw new Exception("Selected trip can not be edited.");
		}

		return "redirect:/planner/edit/" + id;
	}

	private String redirectView(Long id) throws Exception {
		tripService.findTrip(id);

		return "redirect:/planner/view/" + id;
	}

	private void deleteTrip(Long id) throws Exception {
		Trip trip = tripService.findTrip(id);

		if (!trip.canDelete()) {
			throw new Exception("Not allowed to delete selected trip.");
		}

		tripService.deleteTrip(trip);
	}

	private Model addTripLists(Model model) {
		model.addAttribute("tripsEditing", tripService.findTrips(TripState.editing));
		model.addAttribute("tripsAssigned", tripService.findTrips(TripState.assigned));
		model.addAttribute("tripsExpired", tripService.findTrips(TripState.expired));
		model.addAttribute("tripsActive", tripService.findTrips(TripState.active));
		model.addAttribute("tripsSuccessful", tripService.findTrips(TripState.successful));
		model.addAttribute("tripsUnsuccessful", tripService.findTrips(TripState.unsuccessful));

		return model;
	}
}

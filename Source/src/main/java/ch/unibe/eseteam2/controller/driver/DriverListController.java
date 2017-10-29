package ch.unibe.eseteam2.controller.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.controller.service.DriverService;
import ch.unibe.eseteam2.controller.service.TripService;
import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.security.UserSecurityService;

@Controller
public class DriverListController {
	@Autowired
	private TripService tripService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping("/driver/list")
	public String getMapping(Model model) {
		tripService.updateTripStates();
		addTripLists(model);
		return "driver/list";
	}

	@PostMapping("/driver/list")
	public String postMapping(@RequestParam(value = "action", required = true) String action, @RequestParam(value = "select", required = true) Long id, Model model) {
		if (action == null) {
			// TODO error handling

		} else if (action.equals("view")) {

			// TODO error handling

			return "redirect:/driver/view/" + id;
		} else if (action.equals("confirm")) {
			// TODO error handling

			return "redirect:/driver/confirm/" + id;

		} else {

			// TODO handle invalid action
		}

		tripService.updateTripStates();
		addTripLists(model);

		return "driver/list";
	}

	private Model addTripLists(Model model) {

		Driver driver = driverService.findDriver(userSecurityService.getAuthenticatedUser().getUsername());

		model.addAttribute("tripsAssigned", tripService.findTrips(driver, TripState.assigned));
		model.addAttribute("tripsExpired", tripService.findTrips(driver, TripState.expired));
		model.addAttribute("tripsActive", tripService.findTrips(driver, TripState.active));
		model.addAttribute("tripsSuccessful", tripService.findTrips(driver, TripState.successful));
		model.addAttribute("tripsUnsuccessful", tripService.findTrips(driver, TripState.unsuccessful));

		return model;
	}
}

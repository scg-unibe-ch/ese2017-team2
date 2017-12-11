package ch.unibe.eseteam2.controller.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.security.UserSecurityService;
import ch.unibe.eseteam2.service.DriverService;
import ch.unibe.eseteam2.service.TripService;

@Controller
@RequestMapping("/driver")
public class DriverTripListController {
	@Autowired
	private TripService tripService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private UserSecurityService userSecurityService;

	@GetMapping("/list")
	public String getMapping(Model model) {
		tripService.updateTripStates();
		addTripLists(model);
		return "driver/trip/list";
	}

	@PostMapping("/list")
	public String postMapping(@RequestParam(value = "action", required = false) String action, @RequestParam(value = "select", required = false) Long id, Model model) {
		try {
			if (action == null) {
				throw new Exception("No action specified.");

			} else if (action.equals("view")) {

				return redirectView(id);

			} else if (action.equals("confirm")) {

				return redirectConfirm(id);

			} else {
				throw new Exception("Invalid action.");
			}
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		tripService.updateTripStates();
		addTripLists(model);

		return "driver/trip/list";
	}

	private String redirectView(Long id) throws Exception {
		tripService.findTrip(id);

		return "redirect:/driver/view/" + id;
	}

	private String redirectConfirm(Long id) throws Exception {
		tripService.findTrip(id);

		return "redirect:/driver/confirm/" + id;
	}

	private Model addTripLists(Model model) {

		String email = userSecurityService.getAuthenticatedUser().getUsername();
		Driver driver = driverService.findActiveDriver(email);

		model.addAttribute("tripsAssigned", tripService.findTrips(driver, TripState.assigned));
		model.addAttribute("tripsActive", tripService.findTrips(driver, TripState.active));
		model.addAttribute("tripsSuccessful", tripService.findTrips(driver, TripState.successful));
		model.addAttribute("tripsUnsuccessful", tripService.findTrips(driver, TripState.unsuccessful));

		return model;
	}
}

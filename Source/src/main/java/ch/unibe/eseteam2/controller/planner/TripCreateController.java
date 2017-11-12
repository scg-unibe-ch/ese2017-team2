package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.service.DriverService;
import ch.unibe.eseteam2.service.TripService;

@Controller
public class TripCreateController {
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private TripService tripService;


	@GetMapping("/planner/create")
	public String getMapping(Model model) {
		// create empty Trip
		Trip trip = new Trip();
		// send Trip
		model.addAttribute("trip", trip);
		model.addAttribute("driverList", driverService.findDrivers());

		return "/planner/create";
	}

	@PostMapping("/planner/create")
	public String postMapping(@RequestParam(name = "driverId", required = false) Long driverId, @Valid Trip trip, BindingResult bindingResult, Model model) {

		if (driverId != null) {
			Driver driver = driverService.findDriver(driverId);
			if (driver != null) {
				// TODO test state of trip (maybe in trip class and throw
				// exception)
				trip.setDriver(driver);
			} else {
				bindingResult.addError(new FieldError("trip", "driver", "Could not find selected driver in the database."));
			}
		}

		if (!trip.canEdit()) {
			bindingResult.addError(new ObjectError("trip", "Trip can not be edited."));
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", driverService.findDrivers());

			// There is some invalid input, try again.
			return "/planner/create";
		}

		tripService.save(trip);

		return "redirect:/planner/list";
	}
}

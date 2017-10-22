package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.dao.DriverRepository;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
public class TripCreateController {

	@Autowired
	private TripRepository tripRepository;

	@Autowired
	private DriverRepository driverRepository;

	@GetMapping("/planner/create")
	public String getMapping(Model model) {
		// create empty Trip
		Trip trip = new Trip();
		// send Trip
		model.addAttribute("trip", trip);
		model.addAttribute("driverList", getDriverList());

		return "/planner/create";
	}

	private Iterable<Driver> getDriverList() {
		return driverRepository.findAll();
	}

	@PostMapping("/planner/create")
	public String postMapping(@RequestParam(name = "driverId", required = false) Long driverId, @Valid Trip trip, BindingResult bindingResult, Model model) {

		if (driverId != null) {
			Driver driver = driverRepository.findOne(driverId);
			if (driver != null) {
				// TODO test state of trip (maybe in trip class and throw
				// exception)
				trip.setDriver(driver);
			} else {
				// TODO handle error
				bindingResult.addError(new FieldError("trip", "driver", "Could not find selected driver in the database."));
			}
		}
		
		if(!trip.canEdit()) {
			//TODO error handling
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", getDriverList());

			// There is some invalid input, try again.
			return "/planner/create";
		}

		tripRepository.save(trip);

		return "redirect:/planner/list";
	}
}

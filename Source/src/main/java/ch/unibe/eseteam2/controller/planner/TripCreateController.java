package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.form.TripCreateForm;
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

		model.addAttribute("trip", new TripCreateForm());
		model.addAttribute("driverList", driverService.findDrivers());

		return "/planner/create";
	}

	@PostMapping("/planner/create")
	public String postMapping(@RequestParam(name = "driverId", required = false) Long driverId, @Valid @ModelAttribute("trip") TripCreateForm tripForm, BindingResult bindingResult, Model model) {
		Trip trip = createTrip(tripForm, driverId, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", driverService.findDrivers());

			// There is some invalid input, try again.
			return "/planner/create";
		}

		tripService.save(trip);

		return "redirect:/planner/list";
	}

	private Trip createTrip(TripCreateForm form, Long driverId, BindingResult bindingResult) {
		//TODO maybe change this to use setters
		Trip trip = new Trip(form.getCustomer(), form.getAnimal(), form.getAnimalCount(), form.getDate(), form.getFirstname_1(), form.getStreet_1(), form.getPlz_1(), form.getCity_1(),
				form.getFirstname_2(), form.getStreet_2(), form.getPlz_2(), form.getCity_2());

		addDriver(trip, driverId, bindingResult);

		return trip;
	}

	private void addDriver(Trip trip, Long driverId, BindingResult bindingResult) {
		if (driverId == null) {
			return;
		}
		Driver driver = driverService.findDriver(driverId);
		if (driver == null) {
			bindingResult.addError(new FieldError("trip", "driver", "Could not find selected driver in the database."));
			return;
		}
		trip.setDriver(driver);

	}

}

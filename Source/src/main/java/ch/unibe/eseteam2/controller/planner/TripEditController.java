package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.form.TripEditForm;
import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;
import ch.unibe.eseteam2.service.DriverService;
import ch.unibe.eseteam2.service.TripService;

@Controller
public class TripEditController {

	@Autowired
	private TripService tripService;

	@Autowired
	private DriverService driverService;

	@GetMapping("/planner/edit/{id}")
	public String getMapping(@PathVariable Long id, Model model) {

		Trip trip;
		try {
			trip = tripService.findTrip(id);
		} catch (Exception e) {
			// TODO display error message, maybe error page?
			return "redirect:/planner/list";
		}

		model.addAttribute("trip", new TripEditForm(trip));
		model.addAttribute("driverList", driverService.findDrivers());

		return "/planner/edit";
	}

	
	@PostMapping("/planner/edit/{id}")
	public String postMapping(@PathVariable Long id, @RequestParam(name = "driverId", required = false) Long driverId, @Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult,
			Model model) {

		Trip trip;
		try {
			trip = tripService.findTrip(id);
		} catch (Exception e) {
			// TODO display error message, maybe error page?
			return "redirect:/planner/list";
		}

		if (!trip.canEdit()) {
			bindingResult.addError(new ObjectError("trip", "Trip can not be edited."));
		}

		updateTrip(trip, form, driverId, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", driverService.findDrivers());
			
			// There is some invalid input, try again.
			return "/planner/edit";
		}

		tripService.save(trip);

		return "redirect:/planner/list";
	}

	private void updateTrip(Trip trip, TripEditForm form, Long driverId, BindingResult bindingResult) {
		trip.setCustomer(form.getCustomer());
		trip.setAnimal(form.getAnimal());
		trip.setAnimalCount(form.getAnimalCount());

		trip.setFirstname_1(form.getFirstname_1());
		trip.setLastname_1(form.getLastname_1());
		trip.setStreet_1(form.getStreet_1());
		trip.setNumber_1(form.getNumber_1());
		trip.setCity_1(form.getCity_1());
		trip.setPlz_1(form.getPlz_1());

		trip.setFirstname_2(form.getFirstname_2());
		trip.setLastname_2(form.getLastname_2());
		trip.setStreet_2(form.getStreet_2());
		trip.setNumber_2(form.getNumber_2());
		trip.setCity_2(form.getCity_2());
		trip.setPlz_2(form.getPlz_2());

		if (form.getDate() != null) {
			trip.setDate(form.getDate());
		}

		addDriver(trip, driverId, bindingResult);
	}

	private void addDriver(Trip trip, Long driverId, BindingResult bindingResult) {
		if (trip.getTripState() == TripState.assigned) {
			bindingResult.addError(new FieldError("trip", "driver", "Can not change driver of an assigned trip."));
			return;
		}
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

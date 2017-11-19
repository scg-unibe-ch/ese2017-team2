package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.unibe.eseteam2.form.TripEditForm;
import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.service.DriverService;
import ch.unibe.eseteam2.service.TripService;
import ch.unibe.eseteam2.service.VehicleService;

@Controller
public class TripEditController {

	@Autowired
	private TripService tripService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/planner/edit/{id}")
	public String getMapping(@PathVariable Long id, Model model) {
		Trip trip = null;
		try {
			trip = tripService.findTrip(id);
			model.addAttribute("trip", new TripEditForm(trip));

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		model.addAttribute("vehicleList", vehicleService.findAvailableVehicles(trip));
		model.addAttribute("driverList", driverService.findDrivers());

		return "/planner/edit";
	}

	@PostMapping("/planner/edit/{id}")
	public String postMapping(@PathVariable Long id, @Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model) {

		Trip trip;
		try {
			trip = tripService.findTrip(id);
			if (!trip.canEdit()) {
				throw new Exception("Trip is in the state " + trip.getTripState() + " and can not be edited.");
			}
			updateTrip(trip, form, bindingResult);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

			return "/planner/edit";
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", driverService.findDrivers());
			model.addAttribute("vehicleList", vehicleService.findAvailableVehicles(trip));

			// There is some invalid input, try again.
			return "/planner/edit";
		}

		tripService.save(trip);

		return "redirect:/planner/list";
	}

	private void updateTrip(Trip trip, TripEditForm form, BindingResult bindingResult) {
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

		addDriver(form, trip, bindingResult);
		addVehicle(form, trip, bindingResult);
	}

	private void addVehicle(TripEditForm form, Trip trip, BindingResult bindingResult) {
		Long vehicleId = form.getVehicleId();
		if (vehicleId == null) {
			try {
				trip.setVehicle(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		Vehicle vehicle = vehicleService.findVehicle(vehicleId);
		if (vehicle == null) {
			bindingResult.addError(new FieldError("trip", "vehicle", "Could not find selected vehicle in the database."));
			return;
		}

		try {
			trip.setVehicle(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addDriver(TripEditForm form, Trip trip, BindingResult bindingResult) {
		Long driverId = form.getDriverId();
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

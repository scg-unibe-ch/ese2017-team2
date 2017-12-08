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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.eseteam2.form.TripEditForm;
import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.service.DriverService;
import ch.unibe.eseteam2.service.TripService;
import ch.unibe.eseteam2.service.VehicleService;

@Controller
public class TripCreateController {
	@Autowired
	private DriverService driverService;

	@Autowired
	private TripService tripService;

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/planner/create")
	public String getMapping(Model model) {

		model.addAttribute("trip", new TripEditForm(true));
		model.addAttribute("driverList", driverService.findDrivers());
		model.addAttribute("vehicleList", vehicleService.findAvailableVehicles());

		return "/planner/trip/edit";
	}

	@PostMapping("/planner/create")
	public String postMapping(@Valid @ModelAttribute("trip") TripEditForm tripForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		Trip trip;

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", driverService.findDrivers());
			model.addAttribute("vehicleList", vehicleService.findAvailableVehicles());

			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		trip = createTrip(tripForm, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", driverService.findDrivers());
			model.addAttribute("vehicleList", vehicleService.findAvailableVehicles());

			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		tripService.save(trip);
		redirectAttrs.addFlashAttribute("message", "Trip saved with state " + trip.getTripState() + ".");
		return "redirect:/planner/list";
	}

	private Trip createTrip(TripEditForm form, BindingResult bindingResult) {
		Trip trip;

		trip = new Trip(form.getCustomer(), form.getAnimal(), form.getAnimalCount(), form.getAddress1(), form.getAddress2(), form.getDate());

		addDriver(form, trip, bindingResult);
		addVehicle(form, trip, bindingResult);

		return trip;
	}

	private void addVehicle(TripEditForm form, Trip trip, BindingResult bindingResult) {
		// TODO
		Long vehicleId = form.getVehicleId();
		if (vehicleId == null) {
			return;
		}
		Vehicle vehicle = vehicleService.findVehicle(vehicleId);
		if (vehicle == null) {
			bindingResult.addError(new FieldError("trip", "vehicleId", "Could not find selected vehicle in the database."));
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
			bindingResult.addError(new FieldError("trip", "driverId", "Could not find selected driver in the database."));
			return;
		}
		trip.setDriver(driver);

	}

}

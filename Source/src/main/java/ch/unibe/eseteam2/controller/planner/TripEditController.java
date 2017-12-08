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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

		addModelAttributes(model, trip);

		return "/planner/trip/edit";
	}

	@PostMapping("/planner/edit/{id}")
	public String postMapping(@PathVariable Long id, @Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {

		Trip trip;
		try {
			trip = tripService.findTrip(id);
			if (!trip.canEdit()) {
				throw new Exception("Trip is in the state " + trip.getTripState() + " and can not be edited.");
			}
			updateTrip(trip, form, bindingResult);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

			return "/planner/trip/edit";
		}

		if (bindingResult.hasErrors()) {
			addModelAttributes(model, trip);

			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		tripService.save(trip);
		redirectAttrs.addFlashAttribute("message", "Trip saved in " + trip.getTripState() + " state.");

		return "redirect:/planner/list";
	}

	private void addModelAttributes(Model model, Trip trip) {
		model.addAttribute("create", false);
		model.addAttribute("vehicleList", vehicleService.findAvailableVehicles(trip));
		model.addAttribute("driverList", driverService.findDrivers());
	}

	private void updateTrip(Trip trip, TripEditForm form, BindingResult bindingResult) {

		trip.setCustomer(form.getCustomer());
		trip.setAnimal(form.getAnimal());
		trip.setAnimalCount(form.getAnimalCount());

		trip.setAddress1(form.getAddress1());
		trip.setAddress2(form.getAddress2());

		if (form.getDate() != null) {
			trip.setDate(form.getDate());
		}

		addEstimate(form, trip, bindingResult);
		addDriver(form, trip, bindingResult);
		addVehicle(form, trip, bindingResult);
	}

	private void addEstimate(TripEditForm form, Trip trip, BindingResult bindingResult) {
		trip.setEstimateHours(form.getEstimateHours());
		trip.setEstimateMinutes(form.getEstimateMinutes());
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

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

import ch.unibe.eseteam2.form.TripCreateForm;
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

		model.addAttribute("trip", new TripCreateForm());
		model.addAttribute("driverList", driverService.findDrivers());
		model.addAttribute("vehicleList", vehicleService.findAvailableVehicles());

		return "/planner/create";
	}

	@PostMapping("/planner/create")
	public String postMapping(@Valid @ModelAttribute("trip") TripCreateForm tripForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		Trip trip = createTrip(tripForm, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("driverList", driverService.findDrivers());
			model.addAttribute("vehicleList", vehicleService.findAvailableVehicles());

			// There is some invalid input, try again.
			return "/planner/create";
		}

		tripService.save(trip);
		redirectAttrs.addFlashAttribute("message", "Trip saved with state " + trip.getTripState() + ".");
		return "redirect:/planner/list";
	}

	private Trip createTrip(TripCreateForm form, BindingResult bindingResult) {
		// TODO maybe change this to use setters
		Trip trip = new Trip(form.getCustomer(), form.getAnimal(), form.getAnimalCount(), form.getDate(), form.getFirstname_1(), form.getLastname_1(), form.getStreet_1(), form.getNumber_1(),
				form.getPlz_1(), form.getCity_1(), form.getFirstname_2(), form.getFirstname_2(), form.getStreet_2(), form.getNumber_2(), form.getPlz_2(), form.getCity_2());

		addDriver(form, trip, bindingResult);
		addVehicle(form, trip, bindingResult);

		return trip;
	}

	private void addVehicle(TripCreateForm form, Trip trip, BindingResult bindingResult) {
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

	private void addDriver(TripCreateForm form, Trip trip, BindingResult bindingResult) {
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

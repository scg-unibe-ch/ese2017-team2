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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.eseteam2.exception.VehicleAssignException;
import ch.unibe.eseteam2.form.TripEditForm;
import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.service.AnimalService;
import ch.unibe.eseteam2.service.DriverService;
import ch.unibe.eseteam2.service.TripService;
import ch.unibe.eseteam2.service.VehicleService;

@Controller
@RequestMapping("/planner/trip")
public class TripCreateController {
	@Autowired
	private DriverService driverService;

	@Autowired
	private TripService tripService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private AnimalService animalService;

	@GetMapping("/create")
	public String getMapping(Model model) {

		if(!model.containsAttribute("trip")) {			
			model.addAttribute("trip", new TripEditForm());
		}

		addModelAttributes(model);

		return "/planner/trip/edit";
	}

	@PostMapping(path = "/create", params = "action=create")
	public String postMapping(@Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		Trip trip;

		if (bindingResult.hasErrors()) {
			addModelAttributes(model);

			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		trip = createTrip(form, bindingResult, model, true);

		if (bindingResult.hasErrors() || model.containsAttribute("ask")) {
			addModelAttributes(model);

			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		tripService.save(trip);
		animalService.save(form.getAnimalObject());

		redirectAttrs.addFlashAttribute("message", "Trip saved in " + trip.getTripState() + " state.");
		return "redirect:/planner/list";
	}

	@PostMapping(path = "/create", params = "action=copy")
	public String saveAndcopyTrip(@Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model) {
		Trip trip;

		if (bindingResult.hasErrors()) {
			addModelAttributes(model);

			model.addAttribute("message", "Could not create Trip.");
			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		trip = createTrip(form, bindingResult, model, false);

		if (bindingResult.hasErrors()) {
			addModelAttributes(model);

			model.addAttribute("message", "Could not create Trip.");
			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		int max = setMaxAnimalCount(trip, form);

		tripService.save(trip);
		animalService.save(form.getAnimalObject());

		model.addAttribute("message", "Saved trip with animal count " + max + ".");

		addModelAttributes(model);
		return "/planner/trip/edit";
	}

	private int setMaxAnimalCount(Trip trip, TripEditForm form) {
		Vehicle vehicle = trip.getVehicle();
		int max = vehicle.getMaxAnimals(form.getAnimalLength(), form.getAnimalWidth());
		trip.setAnimalCount(max);

		form.setAnimalCount(form.getAnimalCount() - max);

		return max;
	}

	@PostMapping(path = "/create", params = "action=suggest")
	public String suggestVehicle(@Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasFieldErrors("animalLength") || bindingResult.hasFieldErrors("animalLength") || bindingResult.hasFieldErrors("animalCount")) {
			addModelAttributes(model);

			return "/planner/trip/edit";
		}

		Vehicle vehicle = vehicleService.findBestVehicle(form.getAnimalLength(), form.getAnimalWidth(), form.getAnimalCount());
		if (vehicle == null) {
			vehicle = vehicleService.findBiggestVehicle(form.getAnimalLength(), form.getAnimalWidth());

			if (vehicle == null) {
				bindingResult.addError(new FieldError("trip", "vehicleId", "no vehicle found that is big enough to fit one animal."));

				addModelAttributes(model);
				return "/planner/trip/edit";
			}
		}
		form.setVehicleId(vehicle.getId());

		addModelAttributes(model);
		return "/planner/trip/edit";
	}

	private void addModelAttributes(Model model) {
		model.addAttribute("create", true);
		model.addAttribute("driverList", driverService.findDrivers());
		model.addAttribute("vehicleList", vehicleService.findAvailableVehicles());
	}

	private Trip createTrip(TripEditForm form, BindingResult bindingResult, Model model, boolean checkIfAllAnimalsFit) {
		Trip trip;

		trip = new Trip(form.getCustomer(), form.getAnimal(), form.getAnimalLength(), form.getAnimalWidth(), form.getAnimalCount(), form.getAddress1(), form.getAddress2(), form.getDate());

		addEstimate(form, trip, bindingResult);
		addDriver(form, trip, bindingResult);
		addVehicle(form, trip, bindingResult, model, checkIfAllAnimalsFit);

		return trip;
	}

	private void addEstimate(TripEditForm form, Trip trip, BindingResult bindingResult) {
		trip.setEstimateHours(form.getEstimateHours());
		trip.setEstimateMinutes(form.getEstimateMinutes());
	}

	private void addVehicle(TripEditForm form, Trip trip, BindingResult bindingResult, Model model, boolean checkIfAllAnimalsFit) {
		Long vehicleId = form.getVehicleId();
		if (vehicleId == null) {
			return;
		}
		Vehicle vehicle = vehicleService.findVehicle(vehicleId);
		if (vehicle == null) {
			bindingResult.addError(new FieldError("trip", "vehicleId", "Could not find selected vehicle in the database."));
			return;
		}

		int max = vehicle.getMaxAnimals(form.getAnimalLength(), form.getAnimalWidth());
		if (max <= 0) {
			bindingResult.addError(new FieldError("trip", "vehicleId", "not big enough."));
			return;
		}

		if (checkIfAllAnimalsFit && max < form.getAnimalCount()) {
			model.addAttribute("ask", true);
			return;
		}

		try {
			trip.setVehicle(vehicle);
		} catch (VehicleAssignException e) {
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

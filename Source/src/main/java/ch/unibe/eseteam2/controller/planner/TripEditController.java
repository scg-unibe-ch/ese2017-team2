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

import ch.unibe.eseteam2.exception.TripEditException;
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
public class TripEditController {

	@Autowired
	private TripService tripService;

	@Autowired
	private DriverService driverService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private AnimalService animalService;

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

	@PostMapping(path = "/planner/edit/{id}", params = "action=create")
	public String postMapping(@PathVariable Long id, @Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		Trip trip;

		try {
			trip = tripService.findTrip(id);
			if (!trip.canEdit()) {
				throw new TripEditException("Trip is in the state " + trip.getTripState() + " and can not be edited.");
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

			return "/planner/trip/edit";
		}

		if (bindingResult.hasErrors()) {
			addModelAttributes(model, trip);

			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		updateTrip(trip, form, bindingResult, model, true);

		if (bindingResult.hasErrors() || model.containsAttribute("ask")) {
			addModelAttributes(model, trip);

			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		tripService.save(trip);
		animalService.save(form.getAnimalObject());

		redirectAttrs.addFlashAttribute("message", "Trip saved in " + trip.getTripState() + " state.");
		return "redirect:/planner/list";
	}

	@PostMapping(path = "/planner/edit/{id}", params = "action=copy")
	public String saveAndcopyTrip(@PathVariable Long id, @Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		Trip trip;

		try {
			trip = tripService.findTrip(id);
			if (!trip.canEdit()) {
				throw new TripEditException("Trip is in the state " + trip.getTripState() + " and can not be edited.");
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

			return "/planner/trip/edit";
		}

		if (bindingResult.hasErrors()) {
			addModelAttributes(model, trip);

			model.addAttribute("message", "Could not save Trip.");
			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		updateTrip(trip, form, bindingResult, model, false);

		if (bindingResult.hasErrors()) {
			addModelAttributes(model, trip);

			model.addAttribute("message", "Could not save Trip.");
			// There is some invalid input, try again.
			return "/planner/trip/edit";
		}

		int max = setMaxAnimalCount(trip, form);

		tripService.save(trip);
		animalService.save(form.getAnimalObject());

		redirectAttrs.addFlashAttribute("message", "Saved trip with animal count " + max + ".");

		redirectAttrs.addFlashAttribute("trip", form);
		return "redirect:/planner/trip/create";
	}

	private int setMaxAnimalCount(Trip trip, TripEditForm form) {
		Vehicle vehicle = trip.getVehicle();
		int max = vehicle.getMaxAnimals(form.getAnimalLength(), form.getAnimalWidth());
		trip.setAnimalCount(max);

		form.setAnimalCount(form.getAnimalCount() - max);

		return max;
	}

	@PostMapping(path = "/planner/edit/{id}", params = "action=suggest")
	public String suggestVehicle(@PathVariable Long id, @Valid @ModelAttribute("trip") TripEditForm form, BindingResult bindingResult, Model model) {
		Trip trip;
		try {
			trip = tripService.findTrip(id);
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

			return "/planner/trip/edit";
		}

		if (bindingResult.hasFieldErrors("animalLength") || bindingResult.hasFieldErrors("animalLength") || bindingResult.hasFieldErrors("animalCount")) {
			addModelAttributes(model, trip);

			return "/planner/trip/edit";
		}

		Vehicle vehicle = vehicleService.findBestVehicle(trip, form.getAnimalLength(), form.getAnimalWidth(), form.getAnimalCount());
		if (vehicle == null) {
			vehicle = vehicleService.findBiggestVehicle(trip, form.getAnimalLength(), form.getAnimalWidth());

			if (vehicle == null) {
				bindingResult.addError(new FieldError("trip", "vehicleId", "no vehicle found that is big enough to fit one animal."));

				addModelAttributes(model, trip);
				return "/planner/trip/edit";
			}
		}
		form.setVehicleId(vehicle.getId());

		addModelAttributes(model, trip);
		return "/planner/trip/edit";
	}

	private void addModelAttributes(Model model, Trip trip) {
		model.addAttribute("create", false);
		model.addAttribute("vehicleList", vehicleService.findAvailableVehicles(trip));
		model.addAttribute("driverList", driverService.findActiveDrivers());
		model.addAttribute("animalList", animalService.findAnimals());
	}

	private void updateTrip(Trip trip, TripEditForm form, BindingResult bindingResult, Model model, boolean checkIfAllAnimalsFit) {

		trip.setCustomer(form.getCustomer());
		trip.setAnimal(form.getAnimal());
		trip.setAnimalLength(form.getAnimalLength());
		trip.setAnimalWidth(form.getAnimalWidth());
		trip.setAnimalCount(form.getAnimalCount());

		trip.setAddress1(form.getAddress1());
		trip.setAddress2(form.getAddress2());

		if (form.getDate() != null) {
			trip.setDate(form.getDate());
		}

		addEstimate(form, trip, bindingResult);
		addDriver(form, trip, bindingResult);
		addVehicle(form, trip, bindingResult, model, checkIfAllAnimalsFit);
	}

	private void addEstimate(TripEditForm form, Trip trip, BindingResult bindingResult) {
		trip.setEstimateHours(form.getEstimateHours());
		trip.setEstimateMinutes(form.getEstimateMinutes());
	}

	private void addVehicle(TripEditForm form, Trip trip, BindingResult bindingResult, Model model, boolean checkIfAllAnimalsFit) {
		Long vehicleId = form.getVehicleId();
		if (vehicleId == null) {
			try {
				trip.setVehicle(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		Vehicle vehicle = vehicleService.findActiveVehicle(vehicleId);
		if (vehicle == null) {
			bindingResult.addError(new FieldError("trip", "vehicle", "Could not find selected vehicle in the database."));
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
		Driver driver = driverService.findActiveDriver(driverId);
		if (driver == null) {
			bindingResult.addError(new FieldError("trip", "driver", "Could not find selected driver in the database."));
			return;
		}

		trip.setDriver(driver);

	}

}

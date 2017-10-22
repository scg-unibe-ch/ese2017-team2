package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.dao.DriverRepository;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
public class TripEditController {

	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private DriverRepository driverRepository;

	@GetMapping("/planner/edit/{id}")
	public String getMapping(@PathVariable Long id, Model model) {

		Trip trip = tripRepository.findOne(id);
		// TODO test for null or exceptions

		model.addAttribute("trip", trip);
		model.addAttribute("driverList", getDriverList());
		return "/planner/edit";
	}

	private Iterable<Driver> getDriverList() {
		return driverRepository.findAll();
	}

	@PostMapping("/planner/edit/{id}")
	public String postMapping(@PathVariable Long id, @Valid Trip trip, BindingResult bindingResult) {
		// bindingResult.addError(new FieldError(objectName, field,
		// defaultMessage));
		// bindingResult.addError(new ObjectError(objectName, defaultMessage));
		
		
		if (bindingResult.hasErrors()) {

			// There is some invalid input, try again.
			return "/planner/edit";
		}

		tripRepository.save(trip);

		return "redirect:/planner/list";
	}
}

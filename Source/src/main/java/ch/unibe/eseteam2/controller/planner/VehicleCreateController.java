package ch.unibe.eseteam2.controller.planner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.eseteam2.form.VehicleEditForm;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.service.VehicleService;

@Controller
@RequestMapping("/planner/vehicle")
public class VehicleCreateController {

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/create")
	public String getMappingCreate(Model model) {
		model.addAttribute("vehicle", new VehicleEditForm());
		return "/planner/vehicle/create";
	}

	@PostMapping("/create")
	public String createVehicle(@Valid @ModelAttribute("vehicle") VehicleEditForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
		Vehicle vehicle;

		form.checkErrors(bindingResult, "vehicle");

		if (bindingResult.hasErrors()) {
			// There is some invalid input, try again.
			return "/planner/vehicle/create";
		}

		vehicle = new Vehicle(form.getName(), form.getCount(), form.getLength(), form.getWidth());
		vehicleService.save(vehicle);

		redirectAttrs.addFlashAttribute("message", "Vehicle saved.");
		return "redirect:/planner/vehicle/list";
	}

}

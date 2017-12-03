package ch.unibe.eseteam2.controller.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.service.VehicleService;

@Controller
@RequestMapping("/planner/vehicle")
public class VehicleListController {

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/list")
	public String getMapping(Model model) {
		addVehicleLists(model);
		return "planner/vehicle/list";
	}

	@PostMapping("/list")
	public String postMapping(@RequestParam(value = "action", required = false) String action, @RequestParam(value = "select", required = false) Long id, Model model) {
		try {

			if (action == null) {
				throw new Exception("No action specified.");
			}
			if (action.equals("edit")) {

				return redirectEdit(id);

			} else if (action.equals("delete")) {

				deleteVehicle(id);
				model.addAttribute("message", "Trip deleted.");

			} else if (action.equals("create")) {

				return redirectCreate(id);

			} else {
				throw new Exception("Invalid action.");
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		addVehicleLists(model);
		return "planner/vehicle/list";
	}

	private String redirectCreate(Long id) {
		return "redirect:/planner/vehicle/create";
	}

	private void deleteVehicle(Long id) throws Exception {
		if (id == null) {
			throw new Exception("No vehicle selected.");
		}

		Vehicle vehicle = this.vehicleService.findVehicle(id);

		if (vehicle == null) {
			throw new Exception("Vehicle could not be found in database.");
		}
		
		this.vehicleService.deleteVehicle(vehicle);
	}

	private String redirectEdit(Long id) throws Exception {
		if (id == null) {
			throw new Exception("No vehicle selected.");
		}

		Vehicle vehicle = vehicleService.findVehicle(id);

		if (vehicle == null) {
			throw new Exception("Vehicle could not be found in database.");
		}

		return "redirect:/planner/vehicle/edit/" + id;
	}

	private void addVehicleLists(Model model) {
		model.addAttribute("vehicleList", this.vehicleService.findVehicles());
	}
}

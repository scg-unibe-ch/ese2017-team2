package ch.unibe.eseteam2.controller.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.service.VehicleService;

@Controller
@RequestMapping("/planner/vehicle")
public class VehicleListController {

	@Autowired
	private VehicleService vehicleService;

	@RequestMapping("/list")
	public String getMapping(Model model) {
		addVehicleLists(model);
		return "planner/vehicle";
	}

	private void addVehicleLists(Model model) {
		model.addAttribute("vehicleList", this.vehicleService.findVehicles());
	}
}

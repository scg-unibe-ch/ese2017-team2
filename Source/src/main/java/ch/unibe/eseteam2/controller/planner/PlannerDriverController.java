package ch.unibe.eseteam2.controller.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.form.DriverViewForm;
import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.service.DriverService;

@Controller
@RequestMapping("planner/driver")
public class PlannerDriverController {

	@Autowired
	private DriverService driverService;

	@GetMapping("/list")
	private String listDrivers(Model model) {
		model.addAttribute("driverList", driverService.findDrivers());
		return "/planner/driverlist";
	}

	@PostMapping("/list")
	private String selectDriver(@RequestParam(value = "select", required = false) Long id, Model model) {
		try {
			if (id == null) {
				throw new Exception("No driver selected.");
			}

			Driver driver = driverService.findDriver(id);

			if (driver == null) {
				throw new Exception("Driver can not be found in database.");
			}

			return "redirect:/planner/driver/" + id;

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());

		}

		model.addAttribute("driverList", driverService.findDrivers());
		return "/planner/driverlist";
	}

	@GetMapping("/{id}")
	private String getDriver(@PathVariable(name = "id") Long id, Model model) {
		Driver driver = driverService.findDriver(id);

		if (driver == null) {
			model.addAttribute("error", "Driver can not be found in database.");
			return "/planner/driver";
		}

		model.addAttribute("driver", new DriverViewForm(driver));
		return "/planner/driver";
	}

	@PostMapping("/{id}")
	private String fireDriver(@PathVariable(name = "id") Long id, Model model) {

		try {
			Driver driver = driverService.findDriver(id);
			if (driver == null) {
				throw new Exception("Driver can not be found in database.");
			}

			driverService.fireDriver(driver);

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "/planner/driver";
		}
		return "redirect:/planner/driver/list";
	}
}

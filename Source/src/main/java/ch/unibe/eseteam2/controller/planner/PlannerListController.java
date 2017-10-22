package ch.unibe.eseteam2.controller.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
@RequestMapping("/planner/list")
public class PlannerListController {
	@Autowired
	private TripRepository tripRepository;

	@GetMapping()
	public String getList(Model model) {

		addTripLists(model);
		return "planner/list";
	}

	@PostMapping()
	public String postForm(@RequestParam(value = "action", required = true) String action, @RequestParam(value = "select", required = false) Long id, Model model) {

		if (action.equals("edit")) {
			if (!tripRepository.exists(id)) {
				// trip does not exist
				// TODO error handling
			}

			return "redirect:/planner/edit/" + id;
		} else if (action.equals("delete")) {
			deleteTrip(id);

		} else {
			// TODO handle invalid action
		}

		addTripLists(model);

		return "/planner/list";
	}

	private Model addTripLists(Model model) {
		// TODO select trips by state

		model.addAttribute("tripsEditing", tripRepository.findAll());
		// model.addAttribute("tripsAssigned", tripRepository.findAll());
		// model.addAttribute("tripsActive", tripRepository.findAll());
		// model.addAttribute("tripsExpired", tripRepository.findAll());
		// model.addAttribute("tripsSuccessful", tripRepository.findAll());
		// model.addAttribute("tripsUnsuccessful", tripRepository.findAll());
		return model;
	}

	private void deleteTrip(Long id) {

		if (!tripRepository.exists(id)) {
			// trip does not exist
			// TODO error handling
		}

		tripRepository.delete(id);
	}
}

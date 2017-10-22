package ch.unibe.eseteam2.controller.planner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.dao.TripRepository;

@Controller
public class PlannerListController {
	@Autowired
	private TripRepository tripRepository;
	// @RequestMapping("/planner/list")
	// public String showList() {
	// return "planner/list";
	// }

	@GetMapping("/planner/list")
	public String getList(Model model) {
		// TODO select trips by state
		Iterable<Trip> tripList = tripRepository.findAll();
		model.addAttribute("tripList", tripList);
		return "planner/list";
	}

	@PostMapping("/planner/list")
	public ModelAndView postForm(@RequestParam(value = "action", required = true) String action, @RequestParam(value = "select", required = false) Long id, RedirectAttributes redirectAttributes) {

		if (action.equals("edit")) {
			if (!tripRepository.exists(id)) {
				// trip does not exist
				// TODO error handling
			}
			// redirectAttributes.addAttribute("id", id);

			return new ModelAndView("redirect:/planner/edit/" + id);
		} else if (action.equals("delete")) {
			deleteTrip(id);

		} else {
			// TODO handle invalid action
		}

		redirectAttributes.addAttribute("tripList", getEditing());
		return new ModelAndView("/planner/list", "tripList", getEditing());
	}

	private Iterable<Trip> getEditing() {
		return tripRepository.findAll();
	}

	private void deleteTrip(Long id) {

		if (!tripRepository.exists(id)) {
			// trip does not exist
			// TODO error handling
		}

		tripRepository.delete(id);
	}
}

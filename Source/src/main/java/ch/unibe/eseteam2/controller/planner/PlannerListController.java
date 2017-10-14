package ch.unibe.eseteam2.controller.planner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlannerListController {

	@RequestMapping("/planner/list")
	public String showList() {
		return "planner/list";
	}
}

package ch.unibe.eseteam2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TripViewController {
	@RequestMapping("/planner/view")
	public String plannerView() {
		return "planner/view";
	}

	@RequestMapping("/driver/view")
	public String driverView() {
		return "driver/view";
	}
}

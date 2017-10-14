package ch.unibe.eseteam2.controller.driver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DriverListController {

	@RequestMapping("/driver/list")
	public String showList() {
		return "driver/list";
	}
}

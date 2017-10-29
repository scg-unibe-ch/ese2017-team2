package ch.unibe.eseteam2.controller.driver;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.controller.service.TripService;

@Controller
@RequestMapping("/driver/confirm/")
public class ConfirmationController {

	@Autowired
	TripService tripService;
	
	@GetMapping("{id}")
	public String getMapping(@PathVariable Long id, Model model) {
		ConfirmationForm form = new ConfirmationForm();
		model.addAttribute("form", form);
		return "driver/confirm";
	}

	@PostMapping("{id}")
	public String postMapping(@PathVariable Long id, @Valid ConfirmationForm form) {
		
		
		
		return "redirect:driver/list";
	}
}

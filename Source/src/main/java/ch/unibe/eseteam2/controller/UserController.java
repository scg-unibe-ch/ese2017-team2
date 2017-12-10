package ch.unibe.eseteam2.controller;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.unibe.eseteam2.form.UserForm;
import ch.unibe.eseteam2.model.Driver;
import ch.unibe.eseteam2.service.DriverService;

@Controller
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserDetailsManager userDetailsManager;

	@Autowired
	private DriverService driverService;

	@PreAuthorize("@userSecurityService.canCreate()")
	@GetMapping(path = "/create")
	public String createForm(Model model) {
		model.addAttribute("user", new UserForm());
		return "login/registration";
	}

	@PreAuthorize("@userSecurityService.canCreate()")
	@PostMapping(path = "/create")
	public String create(@Valid @ModelAttribute("user") UserForm form, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "/login/registration";
		}

		if (userDetailsManager.userExists(form.getEmail())) {
			model.addAttribute("error", "A user already exists with the same email address.");
			form.setEmail("");
			return "/login/registration";

		} else {
			User user = new User(form.getEmail(), form.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_DRIVER")));
			userDetailsManager.createUser(user);

			Driver driver = form.createDriver();

			driverService.save(driver);

			Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			return "redirect:driver/list";
		}
	}

}

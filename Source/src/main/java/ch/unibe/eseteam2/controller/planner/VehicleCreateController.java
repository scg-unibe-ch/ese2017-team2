package ch.unibe.eseteam2.controller.planner;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.unibe.eseteam2.form.VehicleEditForm;
import ch.unibe.eseteam2.model.Vehicle;
import ch.unibe.eseteam2.service.VehicleService;

@Controller
@RequestMapping("/planner/vehicle")
public class VehicleCreateController {

	@Autowired
	private VehicleService vehicleService;

	@GetMapping("/create")
	public String getMappingCreate(Model model) {
		model.addAttribute("vehicle", new VehicleEditForm());
		model.addAttribute("create", true);
		return "/planner/vehicle/edit";
	}

	@PostMapping("/create")
	public String createVehicle(@RequestParam(name = "file", required = false) MultipartFile file, @Valid @ModelAttribute("vehicle") VehicleEditForm form, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttrs) {
		Vehicle vehicle;

		if(hasFile(file)) {			
			checkFileType(file, bindingResult, "vehicle");
		}

		if (bindingResult.hasErrors()) {
			// There is some invalid input, try again.
			model.addAttribute("create", true);
			return "/planner/vehicle/edit";
		}

		vehicle = new Vehicle(form.getName(), form.getCount(), form.getLength(), form.getWidth());
		
		if(hasFile(file)) {
			try {
				vehicle.setImageData(file.getBytes());
			} catch (IOException e) {
				bindingResult.addError(new FieldError("vehicle", "image", "could not upload image."));
				
				model.addAttribute("create", true);
				return "/planner/vehicle/edit";
			}
		}

		vehicleService.save(vehicle);

		redirectAttrs.addFlashAttribute("message", "Vehicle saved.");
		return "redirect:/planner/vehicle/list";
	}

	private boolean hasFile(MultipartFile file) {
		return file != null && !file.isEmpty();
	}

	private void checkFileType(MultipartFile file, BindingResult bindingResult, String objectString) {
		if (!file.getContentType().equals((MediaType.IMAGE_JPEG_VALUE)) && !file.getContentType().equals((MediaType.IMAGE_PNG_VALUE))) {
			bindingResult.addError(new FieldError(objectString, "image", "not an valid image file."));
		}
	}

}

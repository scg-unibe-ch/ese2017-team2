package ch.unibe.eseteam2.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import ch.unibe.eseteam2.model.Vehicle;

public class VehicleCreateForm {

	@NotNull
	private String name;

	@NotNull
	@Min(value = 0, message= "has to be at least 0.")
	private int count;

	@NotNull
	@Min(value = 1, message= "has to be at least 1.")
	private int width;

	@NotNull
	@Min(value = 1, message= "has to be at least 1.")
	private int length;

	public VehicleCreateForm() {
		this.name = "";
		this.count = 0;
		this.width = 1;
		this.length = 1;
	}

	public VehicleCreateForm(Vehicle vehicle) {
		this.name = vehicle.getName();
		this.count = vehicle.getCount();
		this.width = vehicle.getWidth();
		this.length = vehicle.getLength();
	}

	public void checkErrors(BindingResult bindingResult, String objectName) {
		if (this.name.length() < 2 || this.name.length() > 100) {
			bindingResult.addError(new FieldError(objectName, "name", "has to be between 2 and 100 characters long."));
		}
	}

	public void checkErrors(Vehicle vehicle, BindingResult bindingResult, String objectName) {
		if (vehicle.getUsed() > this.count) {
			bindingResult.addError(new FieldError(objectName, "count", "has to be greater than the number of assigned vehicles (" + vehicle.getUsed() + ")."));
		}

		this.checkErrors(bindingResult, objectName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}

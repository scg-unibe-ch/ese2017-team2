package ch.unibe.eseteam2.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import ch.unibe.eseteam2.model.Vehicle;

public class VehicleEditForm {

	@NotNull(message="can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String name;

	@NotNull(message="can not be empty.")
	@Min(value = 1, message = "has to be at least 1.")
	private Integer count;

	@NotNull(message="can not be empty.")
	@Min(value = 1, message = "has to be at least 1.")
	private Integer width;

	@NotNull(message="can not be empty.")
	@Min(value = 1, message = "has to be at least 1.")
	private Integer length;

	/**
	 * Used to display error messages for the selected image.
	 */
	private String image;

	public VehicleEditForm() {
	}

	public VehicleEditForm(Vehicle vehicle) {
		this.name = vehicle.getName();
		this.count = vehicle.getCount();
		this.width = vehicle.getWidth();
		this.length = vehicle.getLength();
	}

	public void checkErrors(Vehicle vehicle, BindingResult bindingResult, String objectName) {
		if (vehicle.getUsed() > this.count) {
			bindingResult.addError(new FieldError(objectName, "count", "has to be greater than the number of assigned vehicles (" + vehicle.getUsed() + ")."));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}

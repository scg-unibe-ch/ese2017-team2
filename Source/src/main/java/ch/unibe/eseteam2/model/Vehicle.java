package ch.unibe.eseteam2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import ch.unibe.eseteam2.exception.VehicleAssignException;

@Entity
@Table(name = "vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "count")
	private int count;

	@NotNull
	@Column(name = "used")
	private int used;

	/**
	 * The width of the load area in centimeters. Used to calculate how many
	 * animals fit in the vehicle.
	 */
	@NotNull
	@Column(name = "width")
	private int width;

	/**
	 * The length of the load area in centimeters. Used to calculate how many
	 * animals fit in the vehicle.
	 */
	@NotNull
	@Column(name = "length")
	private int length;

	@Lob
	@Column(name = "image_data")
	private byte[] imageData;

	@Column(name = "active")
	private Boolean active;

	public Vehicle() {

	}

	public Vehicle(String name, int count, int width, int length) {
		this.setName(name);
		this.setCount(count);
		this.setUsed(0);

		this.setWidth(width);
		this.setLength(length);
		this.setActive(true);
	}

	public int getMaxAnimals(int animalLength, int animalWidth) {
		if (animalLength < 1) {
			throw new IllegalArgumentException("animalLength has to be at least 1.");
		}
		if (animalWidth < 1) {
			throw new IllegalArgumentException("animalWidth has to be at least 1.");
		}
		return (this.length / animalLength) * (this.width / animalWidth);
	}

	public void assign(Trip trip) throws VehicleAssignException {
		if (this.used >= this.count) {
			throw new VehicleAssignException("Can not assign vehicle to trip: All vehicles of type " + name + " are in use");
		}
		this.used++;
	}

	public void unassign(Trip trip) throws VehicleAssignException {
		if (this.used <= 0) {
			throw new VehicleAssignException("Tried to unassign vehicle from trip when there was no vehicle assigned of type " + name);
		}
		this.used--;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this vehicle. The name can not be null or empty.
	 * 
	 * @param name
	 *            the name to display for this vehicle
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Vehicle name can not be null.");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("Vehicle name can not be empty.");
		}
		if (name.length() < 2 || name.length() > 100) {
			throw new IllegalArgumentException("Vehicle name has to be between 2 and 100 characters long.");
		}

		this.name = name;
	}

	public int getCount() {
		return count;
	}

	/**
	 * Sets how many vehicles of this type there are in total. The count has to
	 * be positive (not zero) and greater than or equal to the number of used
	 * vehicles.
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		if (count <= 0) {
			throw new IllegalArgumentException("Vehicle count can not be less than one.");
		}
		if (count < used) {
			throw new IllegalArgumentException("Vehicle count can not be smaller than the number of used vehicles.");
		}
		this.count = count;
	}

	public int getUsed() {
		return used;
	}

	public void setUsed(int available) {
		if (this.used < 0) {
			throw new IllegalArgumentException("Used vehicle count can not be negative.");
		}
		if (this.used > this.count) {
			throw new IllegalArgumentException("Used vehicle count can not be greater than the number of vehicles.");
		}
		this.used = available;
	}

	public int getWidth() {
		return width;
	}

	/**
	 * Set the width of the load area. The width has to be greater than zero.
	 * 
	 * @param width
	 *            the width of the load area in meter
	 */
	public void setWidth(int width) {
		if (width <= 0) {
			throw new IllegalArgumentException("Vehicle width has to be greater than zero.");
		}
		this.width = width;
	}

	public int getLength() {
		return length;
	}

	/**
	 * Set the length of the load area. The length has to be greater than zero.
	 * 
	 * @param length
	 *            the length of the load area in meter
	 */
	public void setLength(int length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Vehicle length has to be greater than zero.");
		}
		this.length = length;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}

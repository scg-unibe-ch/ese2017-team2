package ch.unibe.eseteam2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Length(min = 2, max = 100)
	private String name;
	@NotNull
	@Min(value = 0)
	private int count;

	@NotNull
	@Min(value = 0)
	private int used;

	/**
	 * The width of the load area in meters.
	 * Used to calculate how many animals fit in the vehicle.
	 */
	@NotNull
	@Min(value = 0)
	@Column(name = "width")
	private double width;

	/**
	 * The length of the load area in meters.
	 * Used to calculate how many animals fit in the vehicle.
	 */
	@NotNull
	@Min(value = 0)
	@Column(name = "length")
	private double length;

	@NotNull
	private String image;

	public Vehicle() {

	}

	public Vehicle(String name, int count, double width, double length) {
		this.setName(name);
		this.setCount(count);

		this.setWidth(width);
		this.setLength(length);
	}

	public void assign(Trip trip) throws Exception {
		if (this.used >= this.count) {
			throw new Exception("Can not assign vehicle to trip: All vehicles of type " + name + " are in use");
		}
		this.used++;
	}

	public void unassign(Trip trip) throws Exception {
		if (this.used <= 0) {
			throw new Exception("Tried to unassign vehicle from trip when there was no vehicle assigned of type " + name);
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

		this.name = name;
	}

	public int getCount() {
		return count;
	}

	/**
	 * Sets how many vehicles of this type there are in total. The count has to
	 * be positive and greater than or equal to the number of used vehicles.
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		if (count < 0) {
			throw new IllegalArgumentException("Vehicle count can not be negative.");
		}
		if (count < used) {
			throw new IllegalArgumentException("Vehicle count can not be smaller than the number of used vehicles.");
		}
		this.count = count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public double getWidth() {
		return width;
	}

	/**
	 * Set the width of the load area.
	 * The width has to be greater than zero.
	 * @param width the width of the load area in meter
	 */
	public void setWidth(double width) {
		if (width <= 0) {
			throw new IllegalArgumentException("Vehicle width has to be greater than zero.");
		}
		this.width = width;
	}

	public double getLength() {
		return length;
	}
	
	/**
	 * Set the length of the load area.
	 * The length has to be greater than zero.
	 * @param length the length of the load area in meter
	 */
	public void setLength(double length) {
		if (length <= 0) {
			throw new IllegalArgumentException("Vehicle length has to be greater than zero.");
		}
		this.length = length;
	}

}

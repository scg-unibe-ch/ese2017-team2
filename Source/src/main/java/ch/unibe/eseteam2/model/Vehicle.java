package ch.unibe.eseteam2.model;

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
	private int available;

	@NotNull
	private String image;

	public void assign(Trip trip) throws Exception {
		if (this.available <= 0) {
			throw new Exception("There are no vehicles available of type " + name);
		}
		this.available--;
	}

	public void unassign(Trip trip) throws Exception {
		if (this.available >= this.count) {
			throw new Exception("Number of available vehicles is greater than the number of vehicles.");
		}
		this.available++;

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

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

}

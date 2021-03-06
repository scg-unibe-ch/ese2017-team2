package ch.unibe.eseteam2.form;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import ch.unibe.eseteam2.model.Address;
import ch.unibe.eseteam2.model.Animal;
import ch.unibe.eseteam2.model.Trip;

public class TripEditForm {
	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String customer;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String animal;

	@NotNull(message = "can not be empty.")
	@Min(value = 1, message = "has to be at least 1.")
	private Integer animalLength;

	@NotNull(message = "can not be empty.")
	@Min(value = 1, message = "has to be at least 1.")
	private Integer animalWidth;

	@NotNull(message = "can not be empty.")
	@Min(value = 1, message = "has to be at least 1.")
	private Integer animalCount;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Future(message = "has to be in the future.")
	private Date date;

	@Min(value = 0, message = "has to be positive.")
	private Integer estimateHours;

	@Range(min = 0, max = 59, message = "has to be between 0 and 59.")
	private Integer estimateMinutes;

	private Long driverId;

	private Long vehicleId;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String firstname_1;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String lastname_1;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String street_1;

	@NotNull(message = "can not be empty.")
	@Length(max = 6, message = "has to be at most 6 characters long.")
	@Pattern(regexp = "\\d+[A-Za-z]?", message = "has to be a number followed by at most one character.")
	private String number_1;

	@NotNull(message = "can not be empty.")
	@Min(value = 1000, message = "has to be greater than or equal to 1000.")
	@Max(value = 9999, message = "has to be less than or equal to 9999.")
	private Integer plz_1;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String city_1;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String firstname_2;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String lastname_2;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String street_2;

	@NotNull(message = "can not be empty.")
	@Length(max = 6, message = "has to be at most 6 characters long.")
	@Pattern(regexp = "\\d+[A-Za-z]?", message = "has to be a number followed by at most one character.")
	private String number_2;

	@NotNull(message = "can not be empty.")
	@Min(value = 1000, message = "has to be greater than or equal to 1000.")
	@Max(value = 9999, message = "has to be less than or equal to 9999.")
	private Integer plz_2;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String city_2;

	public TripEditForm() {
	}

	public TripEditForm(Trip trip) {
		this.customer = trip.getCustomer();
		this.animal = trip.getAnimal();
		this.animalLength = trip.getAnimalLength();
		this.animalWidth = trip.getAnimalWidht();
		this.animalCount = trip.getAnimalCount();

		this.date = trip.getDate();

		if (trip.getEstimateHours() != null) {
			this.estimateHours = trip.getEstimateHours();
		}

		if (trip.getEstimateMinutes() != null) {
			this.estimateMinutes = trip.getEstimateMinutes();
		}

		if (trip.getDriver() != null) {
			this.driverId = trip.getDriver().getId();
		} else {
			this.driverId = null;
		}

		if (trip.getVehicle() != null) {
			this.vehicleId = trip.getVehicle().getId();
		} else {
			this.vehicleId = null;
		}

		setAddress1(trip.getAddress1());
		setAddress2(trip.getAddress2());
	}

	private void setAddress1(Address address) {
		if (address != null) {
			this.firstname_1 = address.getFirstname();
			this.lastname_1 = address.getLastname();
			this.street_1 = address.getStreet();
			this.number_1 = address.getNumber();
			this.plz_1 = address.getPlz();
			this.city_1 = address.getCity();
		}
	}

	private void setAddress2(Address address) {
		if (address != null) {
			this.firstname_2 = address.getFirstname();
			this.lastname_2 = address.getLastname();
			this.street_2 = address.getStreet();
			this.number_2 = address.getNumber();
			this.plz_2 = address.getPlz();
			this.city_2 = address.getCity();
		}
	}

	public Animal getAnimalObject() {
		return new Animal(this.animal, this.animalLength, this.animalWidth);
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public Integer getAnimalLength() {
		return animalLength;
	}

	public void setAnimalLength(Integer animalLength) {
		this.animalLength = animalLength;
	}

	public Integer getAnimalWidth() {
		return animalWidth;
	}

	public void setAnimalWidth(Integer animalWidth) {
		this.animalWidth = animalWidth;
	}

	public Integer getAnimalCount() {
		return animalCount;
	}

	public void setAnimalCount(Integer animalCount) {
		this.animalCount = animalCount;
	}

	public String getFirstname_1() {
		return firstname_1;
	}

	public void setFirstname_1(String firstname_1) {
		this.firstname_1 = firstname_1;
	}

	public String getLastname_1() {
		return lastname_1;
	}

	public void setLastname_1(String lastname_1) {
		this.lastname_1 = lastname_1;
	}

	public String getStreet_1() {
		return street_1;
	}

	public void setStreet_1(String street_1) {
		this.street_1 = street_1;
	}

	public String getNumber_1() {
		return number_1;
	}

	public void setNumber_1(String number_1) {
		this.number_1 = number_1;
	}

	public Integer getPlz_1() {
		return plz_1;
	}

	public void setPlz_1(Integer plz_1) {
		this.plz_1 = plz_1;
	}

	public String getCity_1() {
		return city_1;
	}

	public void setCity_1(String city_1) {
		this.city_1 = city_1;
	}

	public String getFirstname_2() {
		return firstname_2;
	}

	public void setFirstname_2(String firstname_2) {
		this.firstname_2 = firstname_2;
	}

	public String getLastname_2() {
		return lastname_2;
	}

	public void setLastname_2(String lastname_2) {
		this.lastname_2 = lastname_2;
	}

	public String getStreet_2() {
		return street_2;
	}

	public void setStreet_2(String street_2) {
		this.street_2 = street_2;
	}

	public String getNumber_2() {
		return number_2;
	}

	public void setNumber_2(String number_2) {
		this.number_2 = number_2;
	}

	public Integer getPlz_2() {
		return plz_2;
	}

	public void setPlz_2(Integer plz_2) {
		this.plz_2 = plz_2;
	}

	public String getCity_2() {
		return city_2;
	}

	public void setCity_2(String city_2) {
		this.city_2 = city_2;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getEstimateHours() {
		return estimateHours;
	}

	public void setEstimateHours(Integer estimateHours) {
		this.estimateHours = estimateHours;
	}

	public Integer getEstimateMinutes() {
		return estimateMinutes;
	}

	public void setEstimateMinutes(Integer estimateMinutes) {
		this.estimateMinutes = estimateMinutes;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * Creates a new Address object from the input data and returns it.
	 * 
	 * @return the first address
	 */
	public Address getAddress1() {
		return new Address(this.firstname_1, this.lastname_1, this.street_1, this.number_1, this.plz_1, this.city_1);
	}

	/**
	 * Creates a new Address object from the input data and returns it.
	 * 
	 * @return the second address
	 */
	public Address getAddress2() {
		return new Address(this.firstname_2, this.lastname_2, this.street_2, this.number_2, this.plz_2, this.city_2);
	}
}

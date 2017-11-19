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
import org.springframework.format.annotation.DateTimeFormat;

public class TripCreateForm {
	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String customer;

	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String animal;

	@NotNull
	@Min(value = 1, message = "has to be at least 1.")
	private int animalCount;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Future(message = "has to be in the future.")
	private Date date;

	private Long driverId;

	private Long vehicleId;

	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String firstname_1;
	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String lastname_1;
	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String street_1;
	@NotNull
	@Pattern(regexp = "\\d+[A-Za-z]?", message = "has to be a number followed by at most one character.")
	private String number_1;
	@NotNull
	@Min(value = 1000, message = "has to be greater than or equal to 1000.")
	@Max(value = 9999, message = "has to be less than or equal to 9999.")
	private int plz_1;
	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String city_1;

	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String firstname_2;
	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String lastname_2;
	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String street_2;
	@NotNull
	@Pattern(regexp = "\\d+[A-Za-z]?", message = "has to be a number followed by at most one character.")
	private String number_2;
	@NotNull
	@Min(value = 1000, message = "has to be greater than or equal to 1000.")
	@Max(value = 9999, message = "has to be less than or equal to 9999.")
	private int plz_2;
	@NotNull
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String city_2;

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

	public int getAnimalCount() {
		return animalCount;
	}

	public void setAnimalCount(int animalCount) {
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

	public int getPlz_1() {
		return plz_1;
	}

	public void setPlz_1(int plz_1) {
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

	public int getPlz_2() {
		return plz_2;
	}

	public void setPlz_2(int plz_2) {
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

}

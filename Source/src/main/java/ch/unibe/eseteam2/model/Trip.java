package ch.unibe.eseteam2.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Trip {

	@NotNull
	@Length(min = 2, max = 100)
	private String customer;

	@NotNull
	@Length(min = 2, max = 100)
	private String name_1;
	@NotNull
	@Length(min = 2, max = 100)
	private String street_1;
	@NotNull
	@Digits(integer = 4, fraction = 0)
	private int plz_1;

	@NotNull
	@Length(min = 2, max = 100)
	private String city_1;
	@NotNull
	@Length(min = 2, max = 100)
	private String name_2;
	@NotNull
	@Length(min = 2, max = 100)
	private String street_2;
	@NotNull
	@Digits(integer = 4, fraction = 0)
	private int plz_2;
	@NotNull
	@Length(min = 2, max = 100)
	private String city_2;

	// TODO improve date handling
	@NotNull
	private String startTime;

	@NotNull
	@Length(min = 2, max = 100)
	private String animal;

	@NotNull
	@Min(1)
	private int animalCount;

	private Driver driver;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getName_1() {
		return name_1;
	}

	public void setName_1(String name_1) {
		this.name_1 = name_1;
	}

	public String getStreet_1() {
		return street_1;
	}

	public void setStreet_1(String street_1) {
		this.street_1 = street_1;
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

	public String getName_2() {
		return name_2;
	}

	public void setName_2(String name_2) {
		this.name_2 = name_2;
	}

	public String getStreet_2() {
		return street_2;
	}

	public void setStreet_2(String street_2) {
		this.street_2 = street_2;
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "Trip [customer=" + customer + ", name_1=" + name_1 + ", street_1=" + street_1 + ", plz_1=" + plz_1 + ", city_1=" + city_1 + ", name_2=" + name_2 + ", street_2=" + street_2 + ", plz_2="
				+ plz_2 + ", city_2=" + city_2 + ", startTime=" + startTime + ", animal=" + animal + ", animalCount=" + animalCount + ", driver=" + driver + "]";
	}

}

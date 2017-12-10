package ch.unibe.eseteam2.form;

import java.util.Date;

import ch.unibe.eseteam2.model.Address;
import ch.unibe.eseteam2.model.Trip;

public class TripViewForm {

	private String customer;

	private String firstname_1;
	private String lastname_1;
	private String street_1;
	private String number_1;
	private Integer plz_1;
	private String city_1;

	private String firstname_2;
	private String lastname_2;
	private String street_2;
	private String number_2;
	private Integer plz_2;
	private String city_2;

	private String animal;

	private Integer animalLength;

	private Integer animalWidth;

	private Integer animalCount;

	private String driver;

	private String vehicle;

	private Date date;

	private String tripState;

	private String feedback;

	private String estimate;
	private String used;

	public TripViewForm(Trip trip) {
		this.customer = trip.getCustomer();

		this.animal = trip.getAnimal();
		this.animalLength = trip.getAnimalLength();
		this.animalWidth = trip.getAnimalWidht();
		this.animalCount = trip.getAnimalCount();

		if (trip.getDriver() != null) {
			this.driver = trip.getDriver().getName();
		}

		if (trip.getVehicle() != null) {
			this.vehicle = trip.getVehicle().getName();
		}

		this.date = trip.getDate();

		setEstimate(trip);
		setUsed(trip);

		this.tripState = trip.getTripState().toString();

		this.feedback = trip.getFeedback();

		setAddress1(trip.getAddress1());
		setAddress2(trip.getAddress2());
	}

	private void setEstimate(Trip trip) {
		Integer hours = trip.getEstimateHours();
		Integer minutes = trip.getEstimateMinutes();

		if (hours != null && minutes != null) {
			this.estimate = hours + ":" + minutes;
		}
	}

	private void setUsed(Trip trip) {
		Long hours = trip.getUsedHours();
		Long minutes = trip.getUsedMinutes();

		if (hours != null && minutes != null) {
			this.used = hours + ":" + minutes;
		}
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
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

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public String getTripState() {
		return tripState;
	}

	public void setTripState(String tripState) {
		this.tripState = tripState;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "TripViewForm [customer=" + customer + ", firstname_1=" + firstname_1 + ", lastname_1=" + lastname_1 + ", street_1=" + street_1 + ", number_1=" + number_1 + ", plz_1=" + plz_1
				+ ", city_1=" + city_1 + ", firstname_2=" + firstname_2 + ", lastname_2=" + lastname_2 + ", street_2=" + street_2 + ", number_2=" + number_2 + ", plz_2=" + plz_2 + ", city_2=" + city_2
				+ ", animal=" + animal + ", animalCount=" + animalCount + ", driver=" + driver + ", date=" + date + ", tripState=" + tripState + ", feedback=" + feedback + "]";
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

}

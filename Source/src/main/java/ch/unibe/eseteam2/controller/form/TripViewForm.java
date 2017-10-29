package ch.unibe.eseteam2.controller.form;

import java.util.Date;

import ch.unibe.eseteam2.model.Trip;

public class TripViewForm {

	private String customer;

	private String name_1;
	private String street_1;
	private int plz_1;
	private String city_1;

	private String name_2;
	private String street_2;
	private int plz_2;
	private String city_2;

	private String animal;

	private int animalCount;

	private String driver;

	private Date date;

	private String tripState;

	private String feedback;

	public TripViewForm(Trip trip) {
		this.customer = trip.getCustomer();

		this.name_1 = trip.getName_1();
		this.street_1 = trip.getStreet_1();
		this.plz_1 = trip.getPlz_1();
		this.city_1 = trip.getCity_1();

		this.name_2 = trip.getName_2();
		this.street_2 = trip.getStreet_2();
		this.plz_2 = trip.getPlz_2();
		this.city_2 = trip.getCity_2();

		this.animal = trip.getAnimal();
		this.driver = trip.getDriver().getName();
		this.date = trip.getDate();
		this.tripState = trip.getTripState().toString();
		this.feedback = trip.getFeedback();
	}

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

}

package ch.unibe.eseteam2.form;

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

		this.animal = trip.getAnimal();
		this.animalCount = trip.getAnimalCount();
		
		this.driver = trip.getDriver().getName();
		this.date = trip.getDate();
		this.tripState = trip.getTripState().toString();
		
		this.feedback = trip.getFeedback();
		
		this.name_1 = trip.getName_1();
		this.street_1 = trip.getStreet_1();
		this.plz_1 = trip.getPlz_1();
		this.city_1 = trip.getCity_1();

		this.name_2 = trip.getName_2();
		this.street_2 = trip.getStreet_2();
		this.plz_2 = trip.getPlz_2();
		this.city_2 = trip.getCity_2();
	}

	public String getCustomer() {
		return customer;
	}

	public String getName_1() {
		return name_1;
	}

	public String getStreet_1() {
		return street_1;
	}

	public int getPlz_1() {
		return plz_1;
	}

	public String getCity_1() {
		return city_1;
	}

	public String getName_2() {
		return name_2;
	}

	public String getStreet_2() {
		return street_2;
	}

	public int getPlz_2() {
		return plz_2;
	}

	public String getCity_2() {
		return city_2;
	}

	public String getAnimal() {
		return animal;
	}

	public int getAnimalCount() {
		return animalCount;
	}

	public String getDriver() {
		return driver;
	}

	public Date getDate() {
		return date;
	}

	public String getTripState() {
		return tripState;
	}

	public String getFeedback() {
		return feedback;
	}

}

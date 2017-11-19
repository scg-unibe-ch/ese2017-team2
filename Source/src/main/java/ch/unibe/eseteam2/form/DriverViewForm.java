package ch.unibe.eseteam2.form;

import ch.unibe.eseteam2.model.Driver;

public class DriverViewForm {

	private String email;

	private String firstname;
	private String lastname;
	private String street;
	private String number;
	private int plz;
	private String city;

	public DriverViewForm(Driver driver) {
		this.email = driver.getEmail();
		this.firstname = driver.getFirstname();
		this.lastname = driver.getLastname();
		this.street = driver.getStreet();
		this.number = driver.getNumber();
		this.plz = driver.getPlz();
		this.city = driver.getCity();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}

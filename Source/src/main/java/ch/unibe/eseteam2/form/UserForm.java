package ch.unibe.eseteam2.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import ch.unibe.eseteam2.model.Driver;

public class UserForm {

	@NotNull(message = "can not be empty.")
	@NotEmpty(message = "can not be empty.")
	@Email(message = "has to be a valid email address.")
	private String email;

	@NotNull(message = "can not be empty.")
	@Length(min = 8, max = 100, message = "has to be between 8 and 100 characters long.")
	private String password;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String firstname;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String lastname;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String street;

	@NotNull(message = "can not be empty.")
	@Pattern(regexp = "\\d+[A-Za-z]?", message = "has to be a number followed by at most one character.")
	private String number;

	@NotNull(message = "can not be empty.")
	@Min(value = 1000, message = "has to be greater than or equal to 1000.")
	@Max(value = 9999, message = "has to be less than or equal to 9999.")
	private Integer plz;

	@NotNull(message = "can not be empty.")
	@Length(min = 2, max = 100, message = "has to be between 2 and 100 characters long.")
	private String city;

	public Driver createDriver() {
		Driver driver = new Driver(email, firstname, lastname, street, number, plz, city);

		return driver;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getPlz() {
		return plz;
	}

	public void setPlz(Integer plz) {
		this.plz = plz;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}

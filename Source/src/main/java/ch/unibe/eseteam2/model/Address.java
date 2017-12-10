package ch.unibe.eseteam2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "firstname")
	private String firstname;

	@NotNull
	@Column(name = "lastname")
	private String lastname;

	@NotNull
	@Column(name = "street")
	private String street;

	@NotNull
	@Column(name = "number")
	private String number;

	@NotNull
	@Column(name = "plz")
	private int plz;

	@NotNull
	@Column(name = "city")
	private String city;

	public Address() {

	}

	public Address(String firstname, String lastname, String street, String number, int plz, String city) {
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setStreet(street);
		this.setNumber(number);
		this.setPlz(plz);
		this.setCity(city);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		if (firstname == null) {
			throw new IllegalArgumentException("Firstname can not be null.");
		}
		if (firstname.length() < 2 || firstname.length() > 100) {
			throw new IllegalArgumentException("Firstname has to be between 2 and 100 characters long.");
		}

		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		if (lastname == null) {
			throw new IllegalArgumentException("Lastname can not be null.");
		}
		if (lastname.length() < 2 || lastname.length() > 100) {
			throw new IllegalArgumentException("Lastname has to be between 2 and 100 characters long.");
		}

		this.lastname = lastname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if (street == null) {
			throw new IllegalArgumentException("Street can not be null.");
		}
		if (street.length() < 2 || street.length() > 100) {
			throw new IllegalArgumentException("Street has to be between 2 and 100 characters long.");
		}

		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		if (number == null) {
			throw new IllegalArgumentException("Number can not be null.");
		}
		if (number.length() > 6) {
			throw new IllegalArgumentException("Number can be at most 6 characters long.");
		}
		if (!number.matches("\\d+[A-Za-z]?")) {
			throw new IllegalArgumentException("Number has to be a number followed by at most one character.");
		}
		this.number = number;
	}

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		if (plz < 1000 || plz > 9999) {
			throw new IllegalArgumentException("PLZ has to be between 1000 and 9999");
		}
		this.plz = plz;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if (city == null) {
			throw new IllegalArgumentException("City can not be null.");
		}
		if (city.length() < 2 || city.length() > 100) {
			throw new IllegalArgumentException("City has to be between 2 and 100 characters long.");
		}
		this.city = city;
	}

}

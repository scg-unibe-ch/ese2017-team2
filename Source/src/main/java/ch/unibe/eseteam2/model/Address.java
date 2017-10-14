package ch.unibe.eseteam2.model;

public class Address {

	private String name;
	private String street;
	private int plz;
	private String city;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	@Override
	public String toString() {
		return "Address [name=" + name + ", street=" + street + ", plz=" + plz + ", city=" + city + "]";
	}

}

package ch.unibe.eseteam2.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Length(min = 2, max = 100)
	private String customer;

	@NotNull
	@Length(min = 2, max = 100)
	private String firstname_1;

	@NotNull
	@Length(min = 2, max = 100)
	private String lastname_1;

	@NotNull
	@Length(min = 2, max = 100)
	private String street_1;

	@NotNull
	private String number_1;

	@NotNull
	@Min(value = 1000)
	@Max(value = 9999)
	private int plz_1;

	@NotNull
	@Length(min = 2, max = 100)
	private String city_1;
	@NotNull
	@Length(min = 2, max = 100)
	private String firstname_2;
	@NotNull
	@Length(min = 2, max = 100)
	private String lastname_2;
	@NotNull
	@Length(min = 2, max = 100)
	private String street_2;
	@NotNull
	private String number_2;
	@NotNull
	@Min(value = 1000)
	@Max(value = 9999)
	private int plz_2;
	@NotNull
	@Length(min = 2, max = 100)
	private String city_2;

	@NotNull
	@Length(min = 2, max = 100)
	private String animal;

	@NotNull
	@Min(value = 1)
	private int animalCount;

	private TripState tripState;

	@OneToOne
	@NotNull
	private Address address1;

	@OneToOne
	@NotNull
	private Address address2;

	@OneToOne
	private Driver driver;

	@ManyToOne
	private Vehicle vehicle;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date date;

	private String feedback;

	public Trip(String customer, String animal, int animalCount, Date date, String firstname_1, String lastname_1, String street_1, String number_1, int plz_1, String city_1, String firstname_2,
			String lastname_2, String street_2, String number_2, int plz_2, String city_2) {
		this.customer = customer;

		this.animal = animal;
		this.animalCount = animalCount;

		this.date = date;

		this.firstname_1 = firstname_1;
		this.lastname_1 = lastname_1;
		this.street_1 = street_1;
		this.number_1 = number_1;
		this.plz_1 = plz_1;
		this.city_1 = city_1;

		this.firstname_2 = firstname_2;
		this.lastname_2 = lastname_2;
		this.street_2 = street_2;
		this.number_2 = number_2;
		this.plz_2 = plz_2;
		this.city_2 = city_2;

		setTripState(TripState.editing);

		this.updateState();
	}

	public Trip() {
		setTripState(TripState.editing);
	}

	public boolean canDelete() {
		return this.tripState == TripState.editing || this.tripState == TripState.expired || this.tripState == TripState.unsuccessful;
	}

	public void onDelete() {
		if (this.vehicle != null) {
			try {
				this.vehicle.unassign(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean canEdit() {
		return !(this.tripState == TripState.successful || this.tripState == TripState.active);
	}

	public void updateState() {
		switch (this.tripState) {
		case active:
			break;
		case assigned:
			if (this.driver != null && this.vehicle != null) {
				if (this.hasStarted()) {
					this.setTripState(TripState.active);
					this.updateState();
				}
			} else {
				this.setTripState(TripState.editing);
				this.updateState();
			}
			break;
		case editing:
			if (this.driver != null && this.vehicle != null) {
				this.setTripState(TripState.assigned);
			}
			if (this.hasStarted()) {
				this.setTripState(TripState.expired);
			}
			break;
		case expired:
			if (this.date != null && !this.hasStarted()) {
				this.setTripState(TripState.editing);
				this.updateState();
			}
			break;
		case successful:
			break;
		case unsuccessful:
			// TODO if time != null -> editing, update state
			if (this.driver != null && this.vehicle != null) {
				if (this.hasStarted()) {
					this.setFeedback(null);
					this.setTripState(TripState.active);
					this.updateState();
				}
			}
			break;
		}

	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
		this.updateState();
	}

	public void setDate(Date date) {
		this.date = date;

		updateState();
	}

	public Date getDate() {
		return this.date;
	}

	public void setTripState(TripState tripState) {
		this.tripState = tripState;

		switch (this.tripState) {
		case successful:
		case unsuccessful:
			// unassign vehicle
			if (this.vehicle != null) {
				try {
					this.vehicle.unassign(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.vehicle = null;
			}

		default:
			break;
		}
	}

	public TripState getTripState() {
		return tripState;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	private boolean hasStarted() {
		if (this.date == null) {
			return false;
		}
		if (this.date.before(Calendar.getInstance().getTime())) {
			return true;
		}

		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Address getAddress1() {
		return address1;
	}

	public void setAddress1(Address address1) {
		this.address1 = address1;
	}

	public Address getAddress2() {
		return address2;
	}

	public void setAddress2(Address address2) {
		this.address2 = address2;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) throws Exception {
		if (this.vehicle != null) {
			this.vehicle.unassign(this);
		}
		if (vehicle != null) {
			vehicle.assign(this);
		}
		this.vehicle = vehicle;

		this.updateState();
	}

}

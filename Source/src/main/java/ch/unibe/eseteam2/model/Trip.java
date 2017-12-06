package ch.unibe.eseteam2.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.engine.internal.Cascade;
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
	private String animal;

	@NotNull
	@Min(value = 1)
	private int animalCount;

	private TripState tripState;

	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private Address address1;

	@OneToOne(cascade = CascadeType.ALL)
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

	public Trip(String customer, String animal, int animalCount, Address address1, Address address2, Date date) {
		setTripState(TripState.editing);

		setCustomer(customer);
		setAnimal(animal);
		setAnimalCount(animalCount);

		setAddress1(address1);
		setAddress2(address2);

		setDate(date);

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
		if (address1 == null) {
			throw new IllegalArgumentException("Address1 can not be null.");
		}
		this.address1 = address1;
	}

	public Address getAddress2() {
		return address2;
	}

	public void setAddress2(Address address2) {
		if (address2 == null) {
			throw new IllegalArgumentException("Address2 can not be null.");
		}
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

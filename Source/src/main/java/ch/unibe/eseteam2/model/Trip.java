package ch.unibe.eseteam2.model;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import ch.unibe.eseteam2.InputUtils;

@Entity
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String customer;

	@NotNull
	private String animal;

	@NotNull
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

	private Integer estimateHours;
	private Integer estimateMinutes;

	private Long usedHours;
	private Long usedMinutes;

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

	private boolean hasStarted() {
		if (this.date == null) {
			return false;
		}
		if (this.date.before(Calendar.getInstance().getTime())) {
			return true;
		}

		return false;
	}

	public void updateState() {
		switch (this.tripState) {
		case active:
			break;
		case assigned:
			if (this.driver == null || this.vehicle == null) {
				this.setTripState(TripState.editing);
				this.updateState();
			} else {
				if (this.hasStarted()) {
					this.setTripState(TripState.active);
					this.updateState();
				}
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

	public boolean isEstimateSet() {
		if (this.estimateHours == null || this.estimateMinutes == null) {
			return false;
		}
		return this.estimateHours != 0 && this.estimateMinutes != 0;
	}

	public void updateUsedTime() {
		if (isEstimateSet() && this.date != null) {
			Instant start = this.date.toInstant();
			Instant now = Instant.now();

			if (start.isBefore(now)) {

				this.usedHours = ChronoUnit.HOURS.between(start, now);
				now = now.minus(this.usedHours, ChronoUnit.HOURS);
				this.usedMinutes = ChronoUnit.MINUTES.between(start, now);
			}

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

	public Integer getEstimateHours() {
		return estimateHours;
	}

	public void setEstimateHours(Integer estimateHours) {
		this.estimateHours = estimateHours;
	}

	public Integer getEstimateMinutes() {
		return estimateMinutes;
	}

	public void setEstimateMinutes(Integer estimateMinutes) {
		this.estimateMinutes = estimateMinutes;
	}

	public Long getUsedHours() {
		return usedHours;
	}

	public void setUsedHours(Long usedHours) {
		this.usedHours = usedHours;
	}

	public Long getUsedMinutes() {
		return usedMinutes;
	}

	public void setUsedMinutes(Long usedMinutes) {
		this.usedMinutes = usedMinutes;
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
		InputUtils.checkNull(customer, "customer");
		InputUtils.checkStringRange(customer, "customer", 2, 100);
		this.customer = customer;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		InputUtils.checkNull(animal, "animal");
		InputUtils.checkStringRange(animal, "animal", 2, 100);
		this.animal = animal;
	}

	public int getAnimalCount() {
		return animalCount;
	}

	public void setAnimalCount(int animalCount) {
		if (animalCount < 1) {
			throw new IllegalArgumentException("animalCount has to be at least 1.");
		}
		this.animalCount = animalCount;
	}

	public Address getAddress1() {
		return address1;
	}

	public void setAddress1(Address address1) {
		InputUtils.checkNull(address1, "address1");
		this.address1 = address1;
	}

	public Address getAddress2() {
		return address2;
	}

	public void setAddress2(Address address2) {
		InputUtils.checkNull(address2, "address2");
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

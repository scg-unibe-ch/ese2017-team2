package ch.unibe.eseteam2.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Trip {

	@NotNull
	@Length(min=2, max=100)
	private String customer;

	private Address startAddress;
	private Address endAddress;

	@NotNull
	@Future
	private Date startTime;

	@NotNull
	@Length(min=2, max=100)
	private String animal;

	@NotNull
	@Min(1)
	private int animalCount;

	private Driver driver;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public Address getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(Address startAddress) {
		this.startAddress = startAddress;
	}

	public Address getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(Address endAddress) {
		this.endAddress = endAddress;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "Trip [customer=" + customer + ", startAddress=" + startAddress + ", endAddress=" + endAddress + ", startTime=" + startTime + ", animal=" + animal + ", animalCount=" + animalCount
				+ ", driver=" + driver + "]";
	}

}

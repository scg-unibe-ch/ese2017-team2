package ch.unibe.eseteam2;

import static org.mockito.Mockito.mock;

import static org.junit.Assert.*;
import org.junit.Test;

import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.Vehicle;

public class VehicleTest {

	@Test
	public void constructor() {
		new Vehicle("bigTruck", 10, 1.5, 2);
		new Vehicle("smallTruck", 0, 2, 3.2);
		new Vehicle("tinyTruck", 2, 0.01, 0.1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nameNull() {
		new Vehicle(null, 10, 1.5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nameEmpty() {
		new Vehicle("", 10, 1.5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeCount() {
		new Vehicle("bigTruck", -1, 1.5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void widthZero() {
		new Vehicle("zeroTruck", 5, 0, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void lengthZero() {
		new Vehicle("zeroTruck", 5, 1.7, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void widthNegative() {
		new Vehicle("negativeTruck", 5, -2.5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void lengthNegative() {
		new Vehicle("negativeTruck", 5, 3.12, -0.4);
	}

	@Test
	public void assignAndUnassgin() throws Exception {
		Vehicle vehicle = new Vehicle("smallTruck", 3, 1.5, 2);
		Trip trip1 = mock(Trip.class);
		Trip trip2 = mock(Trip.class);
		Trip trip3 = mock(Trip.class);

		assertEquals(vehicle.getUsed(), 0);

		vehicle.assign(trip1);
		assertEquals(vehicle.getUsed(), 1);

		vehicle.assign(trip2);
		assertEquals(vehicle.getUsed(), 2);

		vehicle.assign(trip3);
		assertEquals(vehicle.getUsed(), 3);

		vehicle.unassign(trip1);
		assertEquals(vehicle.getUsed(), 2);

		vehicle.unassign(trip2);
		assertEquals(vehicle.getUsed(), 1);

		vehicle.unassign(trip3);
		assertEquals(vehicle.getUsed(), 0);
	}

}

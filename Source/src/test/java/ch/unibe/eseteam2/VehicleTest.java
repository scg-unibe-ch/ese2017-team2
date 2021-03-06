package ch.unibe.eseteam2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.unibe.eseteam2.exception.VehicleAssignException;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.Vehicle;

public class VehicleTest {

	@Test
	public void constructor() {
		new Vehicle("bigTruck", 10, 150, 200);
		new Vehicle("smallTruck", 1, 200, 320);
		new Vehicle("tinyTruck", 2, 1, 1);
		new Vehicle("ab", 100, 470, 360);
		new Vehicle("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l", 1, 101, 1300);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void nameNull() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle(null, 10, 150, 200);
	}

	@Test
	public void nameEmpty() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("", 10, 150, 20);
	}

	@Test
	public void nameTooShort() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("a", 4, 70, 200);
	}

	@Test
	public void nameTooLong() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l-", 7, 2, 3);
	}

	@Test
	public void countNegative() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("bigTruck", -1, 150, 20);
	}
	
	@Test
	public void countZero() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("bigTruck", 0, 150, 20);
	}


	@Test
	public void widthZero() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("zeroTruck", 5, 0, 200);
	}

	@Test
	public void lengthZero() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("zeroTruck", 5, 170, 0);
	}

	@Test
	public void widthNegative() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("negativeTruck", 5, -250, 200);
	}

	@Test
	public void lengthNegative() {
		thrown.expect(IllegalArgumentException.class);
		new Vehicle("negativeTruck", 5, 312, -40);
	}

	@Test
	public void assignAndUnassgin() throws Exception {
		Vehicle vehicle = new Vehicle("smallTruck", 3, 150, 200);
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

	@Test
	public void assignTooMany() throws VehicleAssignException {
		Vehicle vehicle = new Vehicle("smallTruck", 1, 150, 200);
		Trip trip1 = mock(Trip.class);
		Trip trip2 = mock(Trip.class);
		
		assertEquals(vehicle.getUsed(), 0);
		
		vehicle.assign(trip1);
		assertEquals(vehicle.getUsed(), 1);

		thrown.expect(VehicleAssignException.class);
		vehicle.assign(trip2);
		
	}
	
	@Test
	public void unassignTooMany() throws VehicleAssignException {
		Vehicle vehicle = new Vehicle("smallTruck", 5, 150, 200);
		Trip trip1 = mock(Trip.class);
		Trip trip2 = mock(Trip.class);
		
		assertEquals(vehicle.getUsed(), 0);
		
		vehicle.assign(trip1);
		assertEquals(vehicle.getUsed(), 1);
		
		vehicle.unassign(trip1);
		assertEquals(vehicle.getUsed(), 0);
		
		thrown.expect(VehicleAssignException.class);
		vehicle.unassign(trip2);
		
	}

}

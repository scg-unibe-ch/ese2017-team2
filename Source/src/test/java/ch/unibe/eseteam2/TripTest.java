package ch.unibe.eseteam2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.unibe.eseteam2.model.Address;
import ch.unibe.eseteam2.model.Trip;
import ch.unibe.eseteam2.model.TripState;

public class TripTest {
	private Address address1;
	private Address address2;

	private Date futureDate;
	private Date pastDate;

	@Before
	public void setup() {
		this.address1 = mock(Address.class);
		this.address2 = mock(Address.class);

		this.futureDate = mock(Date.class);
		when(futureDate.before(any())).thenReturn(false);

		this.pastDate = mock(Date.class);
		when(pastDate.before(any())).thenReturn(true);

	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void constructor() {
		Trip trip;

		trip = new Trip("Peter Braun", "cow", 5, address1, address2, null);
		assertEquals(trip.getTripState(), TripState.editing);

		trip = new Trip("Peter Braun", "horse", 1, address1, address2, futureDate);
		assertEquals(trip.getTripState(), TripState.editing);

		trip = new Trip("Peter Braun", "chicken", 100, address1, address2, pastDate);
		assertEquals(trip.getTripState(), TripState.expired);

		trip = new Trip("Pe", "co", 1, address1, address2, futureDate);
		assertEquals(trip.getTripState(), TripState.editing);

		trip = new Trip("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l",
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l", 5, address1, address2, futureDate);
		assertEquals(trip.getTripState(), TripState.editing);

	}

	@Test
	public void customerNull() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("customer can not be null.");
		new Trip(null, "cow", 5, address1, address2, futureDate);
	}

	@Test
	public void customerTooShort() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("customer has to be between 2 and 100 characters long.");
		new Trip("c", "cow", 5, address1, address2, futureDate);
	}

	@Test
	public void customerTooLong() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("customer has to be between 2 and 100 characters long.");
		new Trip("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", "cow", 5, address1, address2, futureDate);
	}

	@Test
	public void animalNull() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("animal can not be null.");
		new Trip("Paul Schärer", null, 5, address1, address2, futureDate);
	}

	@Test
	public void animalTooShort() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("animal has to be between 2 and 100 characters long.");
		new Trip("Bob Bauer", "c", 5, address1, address2, futureDate);
	}

	@Test
	public void animalTooLong() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("animal has to be between 2 and 100 characters long.");
		new Trip("Anna Arnold", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", 5, address1, address2, futureDate);
	}
	
	@Test
	public void animalCountNegative() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("animalCount has to be at least 1.");
		new Trip("Bob Bauer", "cow", -1, address1, address2, futureDate);
	}
	
	@Test
	public void animalCountZero() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("animalCount has to be at least 1.");
		new Trip("Bob Bauer", "cow", 0, address1, address2, futureDate);
	}
	
	@Test
	public void address1Null() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("address1 can not be null.");
		new Trip("Paul Schärer", "sheep", 5, null, address2, futureDate);
	}
	
	@Test
	public void address2Null() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("address2 can not be null.");
		new Trip("Paul Schärer", "sheep", 5, address1, null, futureDate);
	}

	// TODO implement more test cases
}

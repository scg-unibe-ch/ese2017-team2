package ch.unibe.eseteam2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void constructor() {
		Trip trip;
		
		trip = new Trip("Peter Braun", "cow", 5, address1, address2, null);
		assertEquals(trip.getTripState(), TripState.editing);

		trip = new Trip("Peter Braun", "cow", 5, address1, address2, futureDate);
		assertEquals(trip.getTripState(), TripState.editing);
		
		trip = new Trip("Peter Braun", "cow", 5, address1, address2, pastDate);
		assertEquals(trip.getTripState(), TripState.expired);
	}
	
	//TODO implement more test cases
}

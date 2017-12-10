package ch.unibe.eseteam2;

import org.junit.Test;

import ch.unibe.eseteam2.model.Driver;

public class DriverTest {
	@Test
	public void constructor() {
		new Driver("test@test.com", "Hans", "Hofer", "Bachweg", "15a", 1234, "Aarau");
	}
	
	//TODO add more test cases
}

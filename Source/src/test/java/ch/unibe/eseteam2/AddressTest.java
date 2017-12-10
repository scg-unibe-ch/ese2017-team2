package ch.unibe.eseteam2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.unibe.eseteam2.model.Address;

public class AddressTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void constructor() {
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, "Solothurn");
		new Address("Pe", "He", "Br", "123456", 1000, "Be");
		new Address("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l",
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l",
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l", "12345a", 9999,
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l");
	}

	@Test
	public void firstNameNull() {
		thrown.expect(IllegalArgumentException.class);
		new Address(null, "Herrmann", "Brühlstrasse", "5A", 4500, "Solothurn");

	}

	@Test
	public void firstNameTooShort() {
		thrown.expect(IllegalArgumentException.class);
		new Address("a", "Hofer", "Bernstrasse", "12c", 1234, "Bern");
	}

	@Test
	public void firstNameTooLong() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", "Müller", "Bachweg", "1", 4563, "Basel");
	}

	@Test
	public void lastNameNull() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Lucas", null, "Brühlstrasse", "5A", 4500, "Solothurn");

	}

	@Test
	public void lastNameTooShort() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Hans", "H", "Bernstrasse", "12c", 1234, "Bern");
	}

	@Test
	public void lastNameTooLong() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Ueli", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", "Bachweg", "1", 4563, "Basel");
	}

	@Test
	public void streetNull() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Lucas", "Herrmann", null, "5A", 4500, "Solothurn");

	}

	@Test
	public void streetTooShort() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Hans", "Hofer", "B", "12c", 1234, "Bern");
	}

	@Test
	public void streetTooLong() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Ueli", "Müller", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", "1", 4563, "Basel");
	}

	@Test
	public void numberNull() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Lucas", "Herrmann", "Brühlstrasse", null, 4500, "Solothurn");

	}

	@Test
	public void numberTooLong() {
		thrown.expect(IllegalArgumentException.class);
		new Address("David", "Hofer", "Bernstrasse", "1234567", 1234, "Bern");
	}

	@Test
	public void numberTooLongWithCharacter() {
		thrown.expect(IllegalArgumentException.class);
		new Address("David", "Hofer", "Bernstrasse", "123456c", 1234, "Bern");
	}

	@Test
	public void numberTwoCharacters() {
		thrown.expect(IllegalArgumentException.class);
		new Address("David", "Hofer", "Bernstrasse", "1cd", 1234, "Bern");
	}

	@Test
	public void numberOnlyCharacter() {
		thrown.expect(IllegalArgumentException.class);
		new Address("David", "Hofer", "Bernstrasse", "d", 1234, "Bern");
	}

	@Test
	public void numberEmpty() {
		thrown.expect(IllegalArgumentException.class);
		new Address("David", "Hofer", "Bernstrasse", "", 1234, "Bern");
	}

	@Test
	public void plzTooSmall() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Paul", "Bär", "Bahnhofstrasse", "17", 999, "Langenthal");
	}

	@Test
	public void plzTooBig() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Tom", "Bär", "Rue de Lyon", "5", 10000, "Genf");
	}

	@Test
	public void plzNegative() {
		thrown.expect(IllegalArgumentException.class);
		new Address("David", "Hofer", "Stationsweg", "1", -1, "Zürich");
	}

	@Test
	public void cityNull() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, null);
	}

	@Test
	public void cityTooShort() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, "a");
	}

	@Test
	public void cityTooLong() {
		thrown.expect(IllegalArgumentException.class);
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1");
	}

}

package ch.unibe.eseteam2;

import org.junit.Test;

import ch.unibe.eseteam2.model.Address;

public class AddressTest {
	@Test
	public void constructor() {
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, "Solothurn");
		new Address("Pe", "He", "Br", "123456", 1000, "Be");
		new Address("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l",
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l",
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l", "12345a", 9999,
				"Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l");
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstNameNull() {
		new Address(null, "Herrmann", "Brühlstrasse", "5A", 4500, "Solothurn");

	}

	@Test(expected = IllegalArgumentException.class)
	public void firstNameTooShort() {
		new Address("a", "Hofer", "Bernstrasse", "12c", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void firstNameTooLong() {
		new Address("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", "Müller", "Bachweg", "1", 4563, "Basel");
	}

	@Test(expected = IllegalArgumentException.class)
	public void lastNameNull() {
		new Address("Lucas", null, "Brühlstrasse", "5A", 4500, "Solothurn");

	}

	@Test(expected = IllegalArgumentException.class)
	public void lastNameTooShort() {
		new Address("Hans", "H", "Bernstrasse", "12c", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void lastNameTooLong() {
		new Address("Ueli", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", "Bachweg", "1", 4563, "Basel");
	}

	@Test(expected = IllegalArgumentException.class)
	public void streetNull() {
		new Address("Lucas", "Herrmann", null, "5A", 4500, "Solothurn");

	}

	@Test(expected = IllegalArgumentException.class)
	public void streetTooShort() {
		new Address("Hans", "Hofer", "B", "12c", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void streetTooLong() {
		new Address("Ueli", "Müller", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", "1", 4563, "Basel");
	}

	@Test(expected = IllegalArgumentException.class)
	public void numberNull() {
		new Address("Lucas", "Herrmann", "Brühlstrasse", null, 4500, "Solothurn");

	}

	@Test(expected = IllegalArgumentException.class)
	public void numberTooLong() {
		new Address("David", "Hofer", "Bernstrasse", "1234567", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void numberTooLongWithCharacter() {
		new Address("David", "Hofer", "Bernstrasse", "123456c", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void numberTwoCharacters() {
		new Address("David", "Hofer", "Bernstrasse", "1cd", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void numberOnlyCharacter() {
		new Address("David", "Hofer", "Bernstrasse", "d", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void numberEmpty() {
		new Address("David", "Hofer", "Bernstrasse", "", 1234, "Bern");
	}

	@Test(expected = IllegalArgumentException.class)
	public void plzTooSmall() {
		new Address("Paul", "Bär", "Bahnhofstrasse", "17", 999, "Langenthal");
	}

	@Test(expected = IllegalArgumentException.class)
	public void plzTooBig() {
		new Address("Tom", "Bär", "Rue de Lyon", "5", 10000, "Genf");
	}

	@Test(expected = IllegalArgumentException.class)
	public void plzNegative() {
		new Address("David", "Hofer", "Stationsweg", "1", -1, "Zürich");
	}

	@Test(expected = IllegalArgumentException.class)
	public void cityNull() {
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cityTooShort() {
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, "a");
	}

	@Test(expected = IllegalArgumentException.class)
	public void cityTooLong() {
		new Address("Lucas", "Herrmann", "Brühlstrasse", "5A", 4500, "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1");
	}

}

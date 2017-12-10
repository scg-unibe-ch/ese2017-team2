package ch.unibe.eseteam2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ch.unibe.eseteam2.model.Animal;

public class AnimalTest {
	@Test
	public void constructor() {
		new Animal("cow", 200, 75);
		new Animal("co", 1, 1);
		new Animal("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l", 110, 50);
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void nameNull() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("name can not be null.");
		new Animal(null, 200, 75);
	}

	@Test
	public void nameTooShort() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("name has to be between 2 and 100 characters long.");
		new Animal("c", 200, 75);
	}

	@Test
	public void nameTooLong() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("name has to be between 2 and 100 characters long.");
		new Animal("Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut l1", 200, 75);
	}

	@Test
	public void lengthNegative() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("length has to be at least 1.");
		new Animal("cow", -1, 75);
	}

	@Test
	public void lengthZero() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("length has to be at least 1.");
		new Animal("cow", 0, 75);
	}

	@Test
	public void widthNegative() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("width has to be at least 1.");
		new Animal("cow", 150, -1);
	}

	@Test
	public void widthZero() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("width has to be at least 1.");
		new Animal("cow", 100, 0);
	}
}

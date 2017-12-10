package ch.unibe.eseteam2;

public class InputUtils {
	/**
	 * Checks if the string is inside the given range. Throws an
	 * IllegalArgumentException if the check fails
	 * 
	 * @param input
	 *            the String to check, can not be null
	 * @param name
	 *            the name to use in the exception message
	 * @param min
	 *            the minimum length of the String
	 * @param max
	 *            the maximum length of the String
	 */
	public static void checkStringRange(String input, String name, int min, int max) {
		if (input.length() < min || input.length() > max) {
			throw new IllegalArgumentException(name + " has to be between " + min + " and " + max + " characters long.");
		}
	}

	/**
	 * Checks if the object is null. Throws an IllegalArgumentException if the
	 * check fails.
	 * 
	 * @param input
	 *            the object to check
	 * @param name
	 *            the name to use in the exception message
	 */
	public static void checkNull(Object input, String name) {
		if (input == null) {
			throw new IllegalArgumentException(name + " can not be null.");
		}
	}
}

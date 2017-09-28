package fr.umlv.urm.utilities;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class Commands {
	/**
	 * Check if array has number of arguments
	 * 
	 * @param tokens
	 * @param number
	 */
	public static void hasNumberOfArguments(String[] tokens, int number) {
		if (tokens == null || tokens.length < number) throw new IllegalArgumentException("Command does not have the right amount of arguments");
	}
	
	/**
	 * Check if string is an integer
	 * 
	 * @param str
	 * @return integer representation of string
	 */
	public static int isInteger(String str) {
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Check if integer is positive
	 * 
	 * @param i integer to check
	 * @return integer
	 */
	public static int isPositiveInteger(int i) {
		if (i < 0) {
			throw new IllegalArgumentException();
		}
		
		return i;
	}
	
	/**
	 * Join an array from begin to end
	 * 
	 * @param tokens
	 * @param begin
	 * @param end
	 * @return string join
	 */
	public static String join(String[] tokens, int begin, int end) {
		StringBuilder sb = new StringBuilder();
		
		for (int i =  begin; i < end; i++) {
			sb.append(tokens[i]).append(" ");
		}
		
		return sb.toString();
	}
}

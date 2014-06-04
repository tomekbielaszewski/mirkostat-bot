package org.grizz.utils;

public class Emergency {
	public static void checkPrecondition(boolean condition, String message) {
		if(!condition) {
			throw new IllegalArgumentException(message);
		}
	}
}

package org.grizz.output;

import org.springframework.stereotype.Service;

//@Service
public class SystemOutput implements Output {
	private StringBuilder stringBuilder = new StringBuilder();

	@Override
	public void append(String output) {
		stringBuilder.append(output);
	}

	@Override
	public void flush() {
		System.out.println(stringBuilder.toString());
		stringBuilder = new StringBuilder();
	}

}

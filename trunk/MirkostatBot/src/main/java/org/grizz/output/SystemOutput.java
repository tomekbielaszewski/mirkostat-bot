package org.grizz.output;

import org.apache.log4j.Logger;

//@Service
public class SystemOutput implements Output {
    private final Logger logger = Logger.getLogger(this.getClass());
	private StringBuilder stringBuilder = new StringBuilder();

	@Override
	public void append(String output) {
		stringBuilder.append(output);
	}

	@Override
	public void flush() {
		logger.info(stringBuilder.toString());
		stringBuilder = new StringBuilder();
	}

}

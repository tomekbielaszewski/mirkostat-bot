package org.grizz.output;

import org.grizz.utils.Emergency;
import org.springframework.stereotype.Service;

@Service
public class StringOutput implements Output {
	private StringBuilder stringBuilder;

    public StringOutput() {
        stringBuilder = new StringBuilder();
    }

    @Override
	public void append(String output) {
		Emergency.checkPrecondition(stringBuilder != null, "stringBuilder == null");
		stringBuilder.append(output);
	}

	@Override
	public void flush() {
		stringBuilder.setLength(0);
	}

	public String getOutput() {
		return stringBuilder.toString();
	}
}

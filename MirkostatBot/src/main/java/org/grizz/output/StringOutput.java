package org.grizz.output;

import org.grizz.utils.Emergency;

//@Service
public class StringOutput implements Output {
	private StringBuilder stringBuilder;
	
	public void setStringBuilder(StringBuilder stringBuilder) {
		this.stringBuilder = stringBuilder;
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

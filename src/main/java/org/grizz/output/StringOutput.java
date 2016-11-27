package org.grizz.output;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class StringOutput implements Output {
	private StringBuilder stringBuilder;

    public StringOutput() {
        stringBuilder = new StringBuilder();
    }

    @Override
	public void append(@NotNull String output) {
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

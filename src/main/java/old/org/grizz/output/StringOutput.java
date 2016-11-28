package old.org.grizz.output;

import org.springframework.stereotype.Service;


@Service
public class StringOutput implements Output {
	private StringBuilder stringBuilder;

    public StringOutput() {
        stringBuilder = new StringBuilder();
    }

    @Override
	public void append( String output) {
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

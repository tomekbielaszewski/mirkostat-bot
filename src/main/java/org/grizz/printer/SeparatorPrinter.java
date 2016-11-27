package org.grizz.printer;

import org.grizz.output.Output;
import org.springframework.stereotype.Service;

@Service
public class SeparatorPrinter implements StatPrinter {

	@Override
	public void print(Output output) {
		output.append("------------------------------------\n\n");
	}

}

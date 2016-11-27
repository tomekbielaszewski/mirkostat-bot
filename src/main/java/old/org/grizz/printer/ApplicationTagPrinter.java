package old.org.grizz.printer;

import old.org.grizz.output.Output;
import org.springframework.stereotype.Service;

@Service
public class ApplicationTagPrinter implements StatPrinter {

	@Override
	public void print(Output output) {
        output.append("**[ #mirkostatbot ]**\n" +
                "Czyli co się działo przez ostatnie 24 godziny na Mirko?\n\n");
	}

}

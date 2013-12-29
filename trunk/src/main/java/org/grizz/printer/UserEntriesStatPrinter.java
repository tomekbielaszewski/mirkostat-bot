package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.UserEntryCounter;
import org.grizz.utils.StringPlural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntriesStatPrinter implements StatPrinter {
	@Autowired
	private UserEntryCounter userEntryCounter;

	@Override
	public void print(Output output) {
		String user = userEntryCounter.getUserWithHighestEntryRate();
		int entries = userEntryCounter.getHighestEntryRate();
		String plural = StringPlural.choose(new String[]{"wpis","wpisy","wpisów"}, entries);
		
		output.append("Najczęściej piszący użytkownik ostatniej godziny:\n");
		output.append("@"+user+": "+entries+" "+plural+"!");
		
		output.append("\n\n");
	}

}

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
		int amountOfStats = 3;
		String[] entriesPlurals = new String[]{"wpis","wpisy","wpisów"};
		
		String template = "Najczęściej piszący użytkownicy ostatnich 24 godzin:\n";

		for (int i = 1; i <= amountOfStats; i++) {
			template += i+". @%s: %d %s";
			if(i < amountOfStats) {
				template += "\n";
			}
		}
		
		Object[] statistics = new Object[amountOfStats * 3];

		for (int i = 0; i < amountOfStats*3; i += 3) {
			statistics[i] = userEntryCounter.getUserEntryRate(i);
			int amountOfEntries = userEntryCounter.getEntryRate(i);
			
			statistics[i+1] = amountOfEntries;
			statistics[i+2] = StringPlural.choose(entriesPlurals, amountOfEntries);
		}
		
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	
	}

}

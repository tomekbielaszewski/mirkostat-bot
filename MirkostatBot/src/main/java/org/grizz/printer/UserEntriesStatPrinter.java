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
		int amountOfStats = 5;
		String[] entriesPlurals = new String[]{"wpis","wpisy","wpisow"};
		
		String template = "Najczesciej piszacy uzytkownicy:\n";

		for (int i = 1; i <= amountOfStats; i++) {
			template += (i<=9?"0":"")+i+". @%s: %d %s";
			if(i < amountOfStats) {
				template += "\n";
			}
		}
		
		Object[] statistics = new Object[amountOfStats * 3];

		for (int i = 0; i < amountOfStats*3; i += 3) {
            int index = i/3;
			statistics[i] = userEntryCounter.getUserEntryRate(index);
			int amountOfEntries = userEntryCounter.getEntryRate(index);
			
			statistics[i+1] = amountOfEntries;
			statistics[i+2] = StringPlural.choose(entriesPlurals, amountOfEntries);
		}
		
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	
	}

}

package old.org.grizz.printer;

import old.org.grizz.output.Output;
import old.org.grizz.utils.StringPlural;
import old.org.grizz.statistics.collector.UserEntryCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserEntriesStatPrinter implements StatPrinter {
	@Autowired
	private UserEntryCounter userEntryCounter;

	@Override
	public void print(Output output) {
		int amountOfStats = 5;
		String[] entriesPlurals = new String[]{"wpis","wpisy","wpisów"};
		
		String template = "Najczęsciej piszący użytkownicy:\n";

		for (int i = 1; i <= amountOfStats; i++) {
			template += (i<=9?"0":"")+i+". @%s: %d %s";
			if(i < amountOfStats) {
				template += "\n";
			}
		}

        int params = 3;
		Object[] statistics = new Object[amountOfStats * params];

		for (int i = 0; i < amountOfStats*params; i += params) {
            int index = i/params;
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

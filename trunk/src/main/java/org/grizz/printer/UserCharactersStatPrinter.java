package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.UserTypedCharactersCounter;
import org.grizz.utils.StringPlural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCharactersStatPrinter implements StatPrinter {
	@Autowired
	private UserTypedCharactersCounter charactersCounter;

	@Override
	public void print(Output output) {
		int amountOfStats = 3;
		String[] charactersPlurals = new String[]{"znak","znaki","znaków"};
		
		String template = "Najwięcej piszący użytkownicy ostatnich 24 godzin:\n";

		for (int i = 1; i <= amountOfStats; i++) {
			template += i+". @%s: %d %s";
			if(i < amountOfStats) {
				template += "\n";
			}
		}
		
		Object[] statistics = new Object[amountOfStats * 3];

		for (int i = 0; i < amountOfStats*3; i += 3) {
			statistics[i] = charactersCounter.getUserTypedCharactersRate(i);
			int amountOfCharacters = charactersCounter.getTypedCharactersRate(i);
			
			statistics[i+1] = amountOfCharacters;
			statistics[i+2] = StringPlural.choose(charactersPlurals, amountOfCharacters);
		}
		
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	}

}

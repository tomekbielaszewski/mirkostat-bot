package old.org.grizz.printer;

import old.org.grizz.output.Output;
import old.org.grizz.utils.StringPlural;
import old.org.grizz.statistics.collector.UserTypedCharactersCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCharactersStatPrinter implements StatPrinter {
	@Autowired
	private UserTypedCharactersCounter charactersCounter;

	@Override
	public void print(Output output) {
		int amountOfStats = 5;
		String[] charactersPlurals = new String[]{"znak","znaki","znaków"};
		
		String template = "Najwięcej piszący użytkownicy:\n";

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
			statistics[i] = charactersCounter.getUserTypedCharactersRate(index);
			int amountOfCharacters = charactersCounter.getTypedCharactersRate(index);
			
			statistics[i+1] = amountOfCharacters;
			statistics[i+2] = StringPlural.choose(charactersPlurals, amountOfCharacters);
		}
		
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	}

}

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
		String user = charactersCounter.getUserWithHighestTypedCharactersRate();
		int characters = charactersCounter.getHighestTypedCharactersRate();
		String plural = StringPlural.choose(new String[]{"znak","znaki","znaków"}, characters);
		
		output.append("Najwięcej piszący użytkownik ostatniech 24 godzin:\n");
		output.append("@"+user+": "+characters+" "+plural+"!");
		
		output.append("\n\n");
	}

}

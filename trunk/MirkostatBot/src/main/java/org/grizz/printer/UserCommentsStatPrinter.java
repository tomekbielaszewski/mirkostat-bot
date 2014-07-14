package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.UserCommentsCounter;
import org.grizz.utils.StringPlural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCommentsStatPrinter implements StatPrinter {
	@Autowired
	private UserCommentsCounter userCommentsCounter;

	@Override
	public void print(Output output) {
		int amountOfStats = 5;
		String[] commentPlurals = new String[]{"komentarz","komentarze","komentarzy"};
		
		String template = "Najwiecej komentujacy uzytkownicy:\n";

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
			statistics[i] = userCommentsCounter.getUserOnPosition(index);
			int amountOfComments = userCommentsCounter.getUserCountOnPosition(index);
			
			statistics[i+1] = amountOfComments;
			statistics[i+2] = StringPlural.choose(commentPlurals, amountOfComments);
		}
		
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	
	}

}

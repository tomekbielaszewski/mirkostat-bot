package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.TagRateCounter;
import org.springframework.beans.factory.annotation.Autowired;

public class TagStatsPrinter implements StatPrinter {
	@Autowired
	private TagRateCounter tagRateCounter;
	private int amountOfStats;
	
	public TagStatsPrinter(int amountOfStats) {
		this.amountOfStats = amountOfStats;
	}

	@Override
	public void print(Output output) {
		String template = "Najpopularniejsze tagi ostatnich 24 godzin:\n";

		for (int i = 1; i <= amountOfStats; i++) {
			template += i+". %s x%d";
			if(i < amountOfStats) {
				template += "\n";
			}
		}
		
		Object[] statistics = new Object[amountOfStats * 2];

		for (int i = 0; i < amountOfStats*2; i += 2) {
			statistics[i] = tagRateCounter.getTag(i);
			statistics[i+1] = tagRateCounter.getTagRate(i);
		}
		
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	}

}

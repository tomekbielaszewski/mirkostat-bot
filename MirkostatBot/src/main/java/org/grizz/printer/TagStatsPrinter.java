package org.grizz.printer;

import java.util.Arrays;

import org.grizz.output.Output;
import org.grizz.statistics.collector.TagRateCounter;
import org.springframework.beans.factory.annotation.Autowired;

public class TagStatsPrinter implements StatPrinter {
	@Autowired
	private TagRateCounter tagRateCounter;
	private final int amountOfStats;
	
	public TagStatsPrinter(int amountOfStats) {
		this.amountOfStats = amountOfStats;
	}

	@Override
	public void print(Output output) {
		String template = "Najpopularniejsze tagi ostatnich 24 godzin:\n";

		for (int i = 1; i <= amountOfStats; i++) {
			template += (i < 10 ? "0" : "") + i + ". [%s](www.wykop.pl/tag/%s) x%d"; //[wykop](www.wykop.pl)
			if(i < amountOfStats) {
				template += "\n";
			}
		}
		
		Object[] statistics = new Object[amountOfStats * 3];

		for (int i = 0; i < amountOfStats*3; i += 3) {
			String tag = tagRateCounter.getTag(i);
			tag = tag.substring(1);
			
			statistics[i] = tag;
			statistics[i+1] = tag;
			statistics[i+2] = tagRateCounter.getTagRate(i);
		}
		System.out.println("template:" + template);
		System.out.println("stats: " + Arrays.toString(statistics));
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	}

}

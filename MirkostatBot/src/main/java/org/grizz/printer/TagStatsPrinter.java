package org.grizz.printer;

import java.util.Arrays;

import org.grizz.output.Output;
import org.grizz.statistics.collector.EntryCounter;
import org.grizz.statistics.collector.TagRateCounter;
import org.springframework.beans.factory.annotation.Autowired;

public class TagStatsPrinter implements StatPrinter {
	@Autowired
	private TagRateCounter tagRateCounter;
    @Autowired
    private EntryCounter entryCounter;

	private final int amountOfStats;
	
	public TagStatsPrinter(int amountOfStats) {
		this.amountOfStats = amountOfStats;
	}

	@Override
	public void print(Output output) {
		String template = "Najpopularniejsze tagi ostatnich 24 godzin:\n";
        int amountOfEntries = entryCounter.getNumberOfEntries();

		for (int i = 1; i <= amountOfStats; i++) {
			template += (i < 10 ? "0" : "") + i + ". [%s](www.wykop.pl/tag/%s) x%d (%s wszystkich)"; //[wykop](www.wykop.pl)
			if(i < amountOfStats) {
				template += "\n";
			}
		}
		
		Object[] statistics = new Object[amountOfStats * 4];

		for (int i = 0; i < amountOfStats*4; i += 4) {
			String tag = tagRateCounter.getTag(i);
			tag = tag.substring(1);
            int percent = (int)(((float)tagRateCounter.getTagRate(i) / (float)amountOfEntries) * 100);
			
			statistics[i] = tag;
			statistics[i+1] = tag;
			statistics[i+2] = tagRateCounter.getTagRate(i);
			statistics[i+3] = percent > 0 ? percent+"%" : "<1%";
		}
		System.out.println("template:" + template);
		System.out.println("stats: " + Arrays.toString(statistics));
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	}

}

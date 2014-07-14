package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.EntryCounter;
import org.grizz.statistics.collector.TagRateCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagRankingPrinter implements StatPrinter {
	@Autowired
	private TagRateCounter tagRateCounter;
    @Autowired
    private EntryCounter entryCounter;

	private final int amountOfStats = 10;

	@Override
	public void print(Output output) {
		String template = "Ranking najpopularniejszych tagow:\n";
        int amountOfEntries = entryCounter.getNumberOfEntries();

		for (int i = 1; i <= amountOfStats; i++) {
			template += (i < 10 ? "0" : "") + i + ". [%s](www.wykop.pl/tag/%s) x%d (%s wszystkich)"; //[wykop](www.wykop.pl)
			if(i < amountOfStats) {
				template += "\n";
			}
		}

        int params = 4;
		Object[] statistics = new Object[amountOfStats * params];

		for (int i = 0; i < amountOfStats*params; i += params) {
            int index = i/params;
			String tag = tagRateCounter.getTag(index);
			tag = tag.substring(1);
            int percent = (int)(((float)tagRateCounter.getTagRate(index) / (float)amountOfEntries) * 100);
			
			statistics[i] = tag;
			statistics[i+1] = tag;
			statistics[i+2] = tagRateCounter.getTagRate(index);
			statistics[i+3] = percent > 0 ? percent+"%" : "<1%";
		}
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	}

}

package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.EntryCounter;
import org.grizz.statistics.collector.EntryLengthCollector;
import org.grizz.statistics.collector.TagCounter;
import org.grizz.statistics.collector.UserCounter;
import org.grizz.utils.StringPlural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatSummaryPrinter implements StatPrinter {

	@Autowired
	private UserCounter userCounter;
	@Autowired
	private EntryCounter entryCounter;
	@Autowired
	private EntryLengthCollector entryLengthCollector;
	@Autowired
	private TagCounter tagCounter;
	
	@Override
	public void print(Output output) {
		int amountOfUsers = userCounter.getNumberOfUsers();
		int amountOfEntries = entryCounter.getNumberOfEntries();
		int overallCharactersTyped = entryLengthCollector.getOverallLength();
		int overallTagsUsed = tagCounter.getNumberOfTags();
		
		String mirekPlural = StringPlural.choose(new String[]{"Mirek napisał","Mirki napisały","Mirków napisało"}, amountOfUsers);
		String entryPlural = StringPlural.choose(new String[]{"wpis","wpisy","wpisów"}, amountOfEntries);
		String characterPlural = StringPlural.choose(new String[]{"znak","znaki","znaków"}, overallCharactersTyped);
		String tagPlural = StringPlural.choose(new String[]{"unikatowy tag","unikatowe tagi","unikatowych tagów"}, overallTagsUsed);
		
		String summary = 
			String.format("Przez ostatnie 24 godziny %d %s %d %s "
					+ "o łącznej długości %d %s. Użyto przy tym %d %s.",
			amountOfUsers, mirekPlural, 
			amountOfEntries, entryPlural,
			overallCharactersTyped, characterPlural,
			overallTagsUsed, tagPlural);
		
		output.append(summary);
		output.append("\n\n");
	}

}

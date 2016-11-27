package old.org.grizz.statistics;

import old.org.grizz.model.Entry;
import old.org.grizz.output.Output;
import old.org.grizz.printer.StatPrinter;
import old.org.grizz.service.DuplicateFilter;
import old.org.grizz.service.provider.EntryProvider;
import old.org.grizz.statistics.collector.StatCollector;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class StatCollectorPool {
	private List<StatCollector> statCollectors = new ArrayList<>();
	private List<StatPrinter> statPrinters = new ArrayList<>();
	
	@Autowired
	private EntryProvider entryProvider;
	@Autowired
	private Output output;
	@Autowired
	private DuplicateFilter duplicateFilter;
	
	public void addStatCollector(StatCollector collector) {
		statCollectors.add(collector);
	}
	
	public void addStatPrinter(StatPrinter printer) {
		statPrinters.add(printer);
	}

	public void collect() {
		List<Entry> entries = entryProvider.getEntries();
		
		for (Entry entry : entries) {
			if(!duplicateFilter.isDuplicated(entry)) {
				for (StatCollector collector : statCollectors) {
					collector.collect(entry);
				}
			}
		}
	}

	public void printStats() {
		for (StatPrinter printer : statPrinters) {
			printer.print(output);
		}
	}

	public void reset() {
		for (StatCollector collector : statCollectors) {
			collector.reset();
		}
		
		duplicateFilter.reset();
	}

}

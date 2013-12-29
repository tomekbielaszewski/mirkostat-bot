package org.grizz.statistics;

import java.util.ArrayList;
import java.util.List;

import org.grizz.model.Entry;
import org.grizz.output.Output;
import org.grizz.printer.StatPrinter;
import org.grizz.service.DuplicateFilter;
import org.grizz.service.EntryProvider;
import org.grizz.statistics.collector.StatCollector;
import org.springframework.beans.factory.annotation.Autowired;

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
			} else {
				System.out.println("Duplicate found! ID: " + entry.getId());
			}
		}
	}

	public void printStats() {
		for (StatPrinter printer : statPrinters) {
			printer.print(output);
		}
		
		output.flush();
	}

	public void reset() {
		for (StatCollector collector : statCollectors) {
			collector.reset();
		}
	}

}

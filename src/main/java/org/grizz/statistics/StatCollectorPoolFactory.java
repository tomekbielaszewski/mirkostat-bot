package org.grizz.statistics;

import org.grizz.printer.StatPrinter;
import org.grizz.statistics.collector.StatCollector;

public class StatCollectorPoolFactory {
	
	private static StatCollectorPoolFactory INSTANCE;
	
	public static StatCollectorPoolFactory getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new StatCollectorPoolFactory();
		}
		
		return INSTANCE;
	}
	
	private StatCollectorPoolFactory() { }
	
	public StatCollectorPool create(StatCollector[] collectors, StatPrinter[] printers) {
		StatCollectorPool collectorPool = new StatCollectorPool();
		
		for (int i = 0; i < collectors.length; i++) {
			StatCollector collector = collectors[i];
			
			if(collector == null) {
				throw new IllegalArgumentException("Collector no. "+i+" is null!");
			}
			collectorPool.addStatCollector(collector);
		}
		
		for (int i = 0; i < printers.length; i++) {
			StatPrinter printer = printers[i];
			
			if(printer == null) {
				throw new IllegalArgumentException("Printer no. "+i+" is null!");
			}
			collectorPool.addStatPrinter(printer);
		}
		
		return collectorPool;
	}
}

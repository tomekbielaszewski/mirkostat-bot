package org.grizz;

import org.grizz.printer.StatPrinter;
import org.grizz.printer.UserCharactersStatPrinter;
import org.grizz.printer.UserEntriesStatPrinter;
import org.grizz.service.Timer;
import org.grizz.service.URLBuilder;
import org.grizz.service.URLEncoder;
import org.grizz.statistics.StatCollectorPool;
import org.grizz.statistics.StatCollectorPoolFactory;
import org.grizz.statistics.collector.EntryCounter;
import org.grizz.statistics.collector.EntryLengthCollector;
import org.grizz.statistics.collector.StatCollector;
import org.grizz.statistics.collector.TagCounter;
import org.grizz.statistics.collector.TagRateCounter;
import org.grizz.statistics.collector.UserCounter;
import org.grizz.statistics.collector.UserEntryCounter;
import org.grizz.statistics.collector.UserTypedCharactersCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
	private static final String APP_KEY = "QRyIcO0ZUu";
	private static final String SECRET_KEY = "E3NWFSkUqN";
//	private static final long LONG_TICK_TIME = 3600000l; //1 godzina
	private static final long LONG_TICK_TIME = 20000l; //20 sekund
	
	@Autowired
	private EntryCounter entryCounter;
	@Autowired
	private EntryLengthCollector entryLengthCollector;
	@Autowired
	private TagCounter tagCounter;
	@Autowired
	private TagRateCounter tagRateCounter;
	@Autowired
	private UserCounter userCounter;
	@Autowired
	private UserEntryCounter userEntryCounter;
	@Autowired
	private UserTypedCharactersCounter userTypedCharactersCounter;
	
	@Autowired
	private UserEntriesStatPrinter mostActiveUserStatPrinter;
	@Autowired
	private UserCharactersStatPrinter userCharactersStatPrinter;
	
	@Bean
	public URLBuilder urlBuilder() {
		return new URLBuilder(APP_KEY);
	}
	
	@Bean
	public URLEncoder urlEncoder() {
		return new URLEncoder(SECRET_KEY);
	}
	
	@Bean
	public Timer timer() {
		return new Timer(LONG_TICK_TIME);
	}
	
	@Bean
	public StatCollectorPoolFactory statCollectorPoolFactory() {
		return StatCollectorPoolFactory.getInstance();
	}
	
	@Bean
	public StatCollectorPool statCollectorPool() {
		StatCollectorPoolFactory factory = statCollectorPoolFactory();
		StatCollector[] collectors = new StatCollector[]{
				entryCounter, entryLengthCollector, tagCounter,
				tagRateCounter, userCounter, userEntryCounter,
				userTypedCharactersCounter
		};
		StatPrinter[] printers = new StatPrinter[]{
				mostActiveUserStatPrinter, userCharactersStatPrinter
		};
		
		return factory.create(collectors, printers);
	}
}

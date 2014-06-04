package org.grizz;

import org.grizz.printer.ApplicationTagPrinter;
import org.grizz.printer.BestVotedUsersPrinter;
import org.grizz.printer.MostActiveUserPrinter;
import org.grizz.printer.SeparatorPrinter;
import org.grizz.printer.StatPrinter;
import org.grizz.printer.StatSummaryPrinter;
import org.grizz.printer.TagStatsPrinter;
import org.grizz.printer.UserCharactersStatPrinter;
import org.grizz.printer.UserEntriesStatPrinter;
import org.grizz.service.EntryProvider;
import org.grizz.service.URLBuilder;
import org.grizz.service.URLEncoder;
import org.grizz.statistics.StatCollectorPool;
import org.grizz.statistics.StatCollectorPoolFactory;
import org.grizz.statistics.collector.EntryCounter;
import org.grizz.statistics.collector.EntryLengthCollector;
import org.grizz.statistics.collector.MostActiveUser;
import org.grizz.statistics.collector.StatCollector;
import org.grizz.statistics.collector.TagCounter;
import org.grizz.statistics.collector.TagRateCounter;
import org.grizz.statistics.collector.TotalVoteCollector;
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
	private static final long COLLECTION_PERIOD = 86400000; //doba
//	private static final long COLLECTION_PERIOD = 3600000l; //1 godzina
	private static final int AMOUNT_OF_TAG_STATS = 10;
	
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
	private TotalVoteCollector totalVoteCollector;
	@Autowired
	private MostActiveUser mostActiveUser;
	
	@Autowired
	private SeparatorPrinter separatorPrinter;
	@Autowired
	private UserEntriesStatPrinter countedUserEntriesStatPrinter;
	@Autowired
	private UserCharactersStatPrinter userCharactersStatPrinter;
	@Autowired
	private StatSummaryPrinter statSummaryPrinter;
	@Autowired
	private ApplicationTagPrinter applicationTagPrinter;
	@Autowired
	private BestVotedUsersPrinter bestVotedUsersPrinter;
	@Autowired
	private MostActiveUserPrinter mostActiveUserPrinter;

	@Bean
	public TagStatsPrinter tagStatsPrinter() {
		return new TagStatsPrinter(AMOUNT_OF_TAG_STATS);
	}
	
	@Bean
	public URLBuilder urlBuilder() {
		return new URLBuilder(APP_KEY);
	}
	
	@Bean
	public URLEncoder urlEncoder() {
		return new URLEncoder(SECRET_KEY);
	}
	
	@Bean
	public EntryProvider entryProvider() {
		return new EntryProvider(COLLECTION_PERIOD);
	}
	
	@Bean
	public StatCollectorPoolFactory statCollectorPoolFactory() {
		return StatCollectorPoolFactory.getInstance();
	}
	
	@Bean
	public StatCollectorPool statCollectorPool() {
		StatCollectorPoolFactory factory = statCollectorPoolFactory();
		StatCollector[] collectors = new StatCollector[]{
				entryCounter,
				entryLengthCollector,
				tagCounter,
				tagRateCounter,
				userCounter,
				userEntryCounter,
				userTypedCharactersCounter, 
				totalVoteCollector,
				mostActiveUser
		};
		
		//printers order is important
		StatPrinter[] printers = new StatPrinter[]{
				tagStatsPrinter(),
				separatorPrinter,
				bestVotedUsersPrinter,
				separatorPrinter,
				mostActiveUserPrinter,
				separatorPrinter,
				countedUserEntriesStatPrinter,
				separatorPrinter,
				userCharactersStatPrinter,
				separatorPrinter,
				statSummaryPrinter, 
				separatorPrinter,
				applicationTagPrinter
		};
		
		return factory.create(collectors, printers);
	}
}

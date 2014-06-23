package org.grizz;

import com.google.gson.Gson;
import org.apache.commons.lang3.Validate;
import org.grizz.model.UserKeyModel;
import org.grizz.printer.*;
import org.grizz.service.EntryProvider;
import org.grizz.service.HTTPRequestService;
import org.grizz.service.URLBuilder;
import org.grizz.service.URLEncoder;
import org.grizz.service.post.WykopUrlSigner;
import org.grizz.statistics.StatCollectorPool;
import org.grizz.statistics.StatCollectorPoolFactory;
import org.grizz.statistics.collector.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfiguration {
	private static final String APP_KEY = "TPJl24Jc5J";
	private static final String SECRET_KEY = "mo4JG9Ed4E";
	private static final String ACCOUNT_KEY = "Ypbaxw0FUUtS4swAvxpI";
//	private static final long COLLECTION_PERIOD = 400000; //test
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

    @Autowired
    private HTTPRequestService requestService;
    @Autowired
    private WykopUrlSigner signer;

	@Bean
	public TagStatsPrinter tagStatsPrinter() {
		return new TagStatsPrinter(AMOUNT_OF_TAG_STATS);
	}

    @Bean(name = "userKey")
    public String userKey() {
        URLBuilder urlBuilder = urlBuilder();
        Map<String, String> postContent = new HashMap<>();
        String url = urlBuilder.getWykopLoginURL();

        postContent.put("accountkey", ACCOUNT_KEY);

        String json = requestService.sendPost(url, postContent, Collections.EMPTY_MAP);

        Gson gson = new Gson();
        UserKeyModel userKeyModel = gson.fromJson(json, UserKeyModel.class);
        Validate.notNull(userKeyModel.getUserkey());

        return userKeyModel.getUserkey();
    }

    @Bean
    public WykopUrlSigner wykopUrlSigner() {
        return new WykopUrlSigner(SECRET_KEY);
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

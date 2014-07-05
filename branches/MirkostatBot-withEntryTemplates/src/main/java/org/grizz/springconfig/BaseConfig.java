package org.grizz.springconfig;

import org.grizz.printer.*;
import org.grizz.service.MicroblogService;
import org.grizz.service.URLBuilder;
import org.grizz.service.post.WykopUrlSigner;
import org.grizz.statistics.StatCollectorPool;
import org.grizz.statistics.StatCollectorPoolFactory;
import org.grizz.statistics.collector.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Grizz on 2014-07-03.
 */
@Configuration
@ComponentScan("org.grizz")
@Import({AppConfig.class, DevConfig.class})
public class BaseConfig {
    private static final String APP_KEY = "TPJl24Jc5J";
    private static final String SECRET_KEY = "mo4JG9Ed4E";
    private static final String ACCOUNT_KEY = "Ypbaxw0FUUtS4swAvxpI";
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
    private UserAppCounter userAppCounter;
    @Autowired
    private EntryCommentCounter entryCommentCounter;
    @Autowired
    private VoteCounter voteCounter;
    @Autowired
    private UntaggedEntriesCounter untaggedEntriesCounter;
    @Autowired
    private GenderActivityCollector genderActivityCollector;
    @Autowired
    private UsersNotTaggingCounter usersNotTaggingCounter;
    @Autowired
    private UserGroupActivityCollector userGroupActivityCollector;

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
    private UserAppsPrinter userAppsPrinter;
    @Autowired
    private GenderStatPrinter genderStatPrinter;
    @Autowired
    private UserGroupStatPrinter userGroupStatPrinter;

    @Bean
    public MicroblogService microblogService() {
        return new MicroblogService(ACCOUNT_KEY);
    }

    @Bean
    public TagStatsPrinter tagStatsPrinter() {
        return new TagStatsPrinter(AMOUNT_OF_TAG_STATS);
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
                mostActiveUser,
                userAppCounter,
                entryCommentCounter,
                voteCounter,
                untaggedEntriesCounter,
                usersNotTaggingCounter,
                genderActivityCollector,
                userGroupActivityCollector
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
                genderStatPrinter,
                separatorPrinter,
                userGroupStatPrinter,
                separatorPrinter,
                userAppsPrinter,
                separatorPrinter,
                statSummaryPrinter,
                separatorPrinter,
                applicationTagPrinter,
        };

        return factory.create(collectors, printers);
    }
}

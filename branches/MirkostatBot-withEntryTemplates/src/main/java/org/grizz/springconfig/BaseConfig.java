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
    private EmbedCounter embedCounter;
    @Autowired
    private UserCommentsCounter userCommentsCounter;
    @Autowired
    private CommentRankingCounter commentRankingCounter;
    @Autowired
    private ImageRankingCounter imageRankingCounter;

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
    private SignPrinter signPrinter;
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
    @Autowired
    private EmbedCountStatPrinter embedCountStatPrinter;
    @Autowired
    private UserCommentsStatPrinter userCommentsStatPrinter;
    @Autowired
    private TagRankingPrinter tagRankingPrinter;
    @Autowired
    private CommentRankingPrinter commentRankingPrinter;
    @Autowired
    private ImageRankingPrinter imageRankingPrinter;

    @Bean
    public MicroblogService microblogService() {
        return new MicroblogService(ACCOUNT_KEY);
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
                userGroupActivityCollector,
                embedCounter,
                userCommentsCounter,
                commentRankingCounter,
                imageRankingCounter
        };

        //printers order is important
        StatPrinter[] printers = new StatPrinter[]{
                applicationTagPrinter,
                separatorPrinter,
                tagRankingPrinter,
                separatorPrinter,
                commentRankingPrinter,
                separatorPrinter,
                imageRankingPrinter,
                separatorPrinter,
                bestVotedUsersPrinter,
                separatorPrinter,
                mostActiveUserPrinter,
                separatorPrinter,
                countedUserEntriesStatPrinter,
                separatorPrinter,
                userCharactersStatPrinter,
                separatorPrinter,
                userCommentsStatPrinter,
                separatorPrinter,
                genderStatPrinter,
                separatorPrinter,
                userGroupStatPrinter,
                separatorPrinter,
                userAppsPrinter,
                separatorPrinter,
                embedCountStatPrinter,
                separatorPrinter,
                statSummaryPrinter,
                separatorPrinter,
                signPrinter
        };

        return factory.create(collectors, printers);
    }
}

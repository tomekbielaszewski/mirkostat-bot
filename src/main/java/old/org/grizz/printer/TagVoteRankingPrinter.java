package old.org.grizz.printer;

import old.org.grizz.output.Output;
import old.org.grizz.statistics.collector.TagVoteCounter;
import old.org.grizz.utils.StringPlural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagVoteRankingPrinter implements StatPrinter {
    @Autowired
    private TagVoteCounter tagVoteCounter;
//    @Autowired
//    private VoteCounter voteCounter;

    private final int amountOfStats = 10;

    @Override
    public void print(Output output) {
        String template = "Ranking najbardziej zaplusowanych tagów:\n";
//        int amountOfVotes = voteCounter.getTotalNumberOfVotes();

        for (int i = 1; i <= amountOfStats; i++) {
            template += (i < 10 ? "0" : "") + i + ". #[%s](www.wykop.pl/tag/%s) - w tagu rozdano %d %s"; //#[heheszki](www.wykop.pl/tag/heheszki) - w tagu rozdano 100 plusow
            if(i < amountOfStats) {
                template += "\n";
            }
        }

        int params = 4;
        Object[] statistics = new Object[amountOfStats * params];

        for (int i = 0; i < amountOfStats*params; i += params) {
            int index = i/params;
            String tag = tagVoteCounter.getTagVote(index);
            tag = tag.substring(1);
            int tagVoteRate = tagVoteCounter.getTagVoteRate(index);

            statistics[i] = tag;
            statistics[i+1] = tag;
            statistics[i+2] = tagVoteRate;
            statistics[i+3] = StringPlural.choose(new String[]{"plus", "plusy", "plusów"}, tagVoteRate);
        }
        String formattedStatistics = String.format(template, statistics);

        output.append(formattedStatistics);
        output.append("\n\n");
    }

}

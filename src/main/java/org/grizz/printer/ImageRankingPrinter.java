package org.grizz.printer;

import org.apache.commons.lang3.StringUtils;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.output.Output;
import org.grizz.statistics.collector.ImageRankingCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-07.
 */
@Service
public class ImageRankingPrinter implements StatPrinter {
    @Autowired
    private ImageRankingCounter imageRankingCounter;

    private final int amountOfStats = 10;

    @Override
    public void print(Output output) {
        String template = "Ranking najlepszych obrazków:\n";

        for (int i = 1; i <= amountOfStats; i++) {
            template += (i < 10 ? "0" : "") + i + ". [(#)](%s) | [(IMG)](%s) **+%d** %s: @%s";
            if(i < amountOfStats) {
                template += "\n";
            }
        }

        int params = 5;
        Object[] statistics = new Object[amountOfStats * params];

        for (int i = 0; i < amountOfStats*params; i += params) {
            int index = i/params;
            Entry entry = imageRankingCounter.getImageOnPosition(index);

            statistics[i] = getEntryUrl(entry);
            statistics[i+1] = getImageUrl(entry);
            statistics[i+2] = entry.getVotes();
            statistics[i+3] = getWordBySex(entry.getAuthorSex(), "Wstawił", "Wstawiła");
            statistics[i+4] = entry.getAuthor();
        }
        String formattedStatistics = String.format(template, statistics);

        output.append(formattedStatistics);
        output.append("\n\n");
    }

    private String getImageUrl(Entry entry) {
        return entry.getEmbed().getUrl();
    }

    private String getEntryUrl(Entry entry) {
        if(entry instanceof EntryComment) {
            EntryComment comment = (EntryComment) entry;
            return getEntryCommentUrl(comment.getEntryId(), comment.getId());
        } else {
            return getEntryUrl(entry.getId());
        }
    }

    private String getEntryUrl(Long id) {
        String template = "http://www.wykop.pl/wpis/%d/";
        return String.format(template, id);
    }

    private String getEntryCommentUrl(int entryId, Long commentId) {
        String template = "http://www.wykop.pl/wpis/%d/#comment-%d";
        return String.format(template, entryId, commentId);
    }

    private String getWordBySex(String sex, String male, String female) {
        return StringUtils.isEmpty(sex) || sex.equals("male") ? male : female;
    }
}

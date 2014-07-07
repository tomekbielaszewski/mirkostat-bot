package org.grizz.printer;

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
        String template = "Ranking najlepszych obrazkow:\n";

        for (int i = 1; i <= amountOfStats; i++) {
            template += (i < 10 ? "0" : "") + i + ". [(#)](%s) | [(IMG)](%s) **+%d**";
            if(i < amountOfStats) {
                template += "\n";
            }
        }

        Object[] statistics = new Object[amountOfStats * 3];

        for (int i = 0; i < amountOfStats*3; i += 3) {
            int index = i/3;
            Entry entry = imageRankingCounter.getImageOnPosition(index);

            statistics[i] = getEntryUrl(entry);
            statistics[i+1] = getImageUrl(entry);
            statistics[i+2] = entry.getVotes();
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
}

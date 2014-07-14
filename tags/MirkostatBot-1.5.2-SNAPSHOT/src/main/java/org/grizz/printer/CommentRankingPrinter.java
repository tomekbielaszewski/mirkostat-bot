package org.grizz.printer;

import org.grizz.model.EntryComment;
import org.grizz.output.Output;
import org.grizz.statistics.collector.CommentRankingCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentRankingPrinter implements StatPrinter {
	@Autowired
	private CommentRankingCounter commentRankingCounter;

	private final int amountOfStats = 10;

	@Override
	public void print(Output output) {
		String template = "Ranking najlepszych komentarzy:\n";

		for (int i = 1; i <= amountOfStats; i++) {
			template += (i < 10 ? "0" : "") + i + ". [#](%s) **+%d** Autor: @%s";
			if(i < amountOfStats) {
				template += "\n";
			}
		}

        int params = 3;
		Object[] statistics = new Object[amountOfStats * params];

		for (int i = 0; i < amountOfStats*params; i += params) {
            int index = i/params;
			EntryComment comment = commentRankingCounter.getCommentOnPosition(index);
			
			statistics[i] = getUrl(comment);
			statistics[i+1] = comment.getVotes();
			statistics[i+2] = comment.getAuthor();
		}
		String formattedStatistics = String.format(template, statistics);
		
		output.append(formattedStatistics);
		output.append("\n\n");
	}

    private String getUrl(EntryComment comment) {
        String template = "http://www.wykop.pl/wpis/%d/#comment-%d";
        return String.format(template, comment.getEntryId(), comment.getId());
    }
}

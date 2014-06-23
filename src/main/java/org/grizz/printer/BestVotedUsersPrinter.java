package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.TotalVoteCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BestVotedUsersPrinter implements StatPrinter {
	@Autowired
	private TotalVoteCollector totalVoteCollector;

	@Override
	public void print(Output output) {
		output.append(
				String.format("Najbardziej zaplusowany Mirek @%s dostal lacznie **%d plusow** zdobytych we wpisach i komentarzach. Brawo! Atencja musi sie zgadzac!",
				totalVoteCollector.getBestVotedUser(),
				totalVoteCollector.getBestVotes()));
		output.append("\n\n");
	}

}

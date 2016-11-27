package old.org.grizz.printer;

import old.org.grizz.output.Output;
import old.org.grizz.statistics.collector.TotalVoteCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BestVotedUsersPrinter implements StatPrinter {
	@Autowired
	private TotalVoteCollector totalVoteCollector;

	@Override
	public void print(Output output) {
		output.append(
				String.format("Najbardziej zaplusowany Mirek @%s dostał łącznie **%d plusów** zdobytych we wpisach i komentarzach. Brawo! Atencja musi się zgadzać!",
				totalVoteCollector.getBestVotedUser(),
				totalVoteCollector.getBestVotes()));
		output.append("\n\n");
	}

}

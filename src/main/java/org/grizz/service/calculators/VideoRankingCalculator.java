package org.grizz.service.calculators;

import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SimpleRanking;
import org.springframework.stereotype.Component;
import pl.grizwold.microblog.model.Embed;
import pl.grizwold.microblog.model.EmbedType;
import pl.grizwold.microblog.model.Entry;

import java.util.Optional;
import java.util.Set;

@Component
public class VideoRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "videoRanking";
    private Ranking ranking = new SimpleRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            if (isVideoPresent(e.getEmbed())) {
                ranking.add(e, e.getVoteCount());
            }
            e.getComments().forEach(c -> {
                if (isVideoPresent(c.getEmbed())) {
                    ranking.add(c, c.getVoteCount());
                }
            });
        });
    }

    private boolean isVideoPresent(Embed embed) {
        return Optional.ofNullable(embed)
                .map(e -> EmbedType.VIDEO.equals(embed.getType()))
                .orElse(false);
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Object getValue() {
        return ranking.asList();
    }
}

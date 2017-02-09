package org.grizz.service.calculators;

import org.grizz.model.Embed;
import org.grizz.model.EmbedType;
import org.grizz.model.Entry;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SimpleRanking;
import org.springframework.stereotype.Component;

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
                ranking.add(e, e.getVotes());
            }
            e.getComments().forEach(c -> {
                if (isVideoPresent(c.getEmbed())) {
                    ranking.add(c, c.getVotes());
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

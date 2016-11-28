package org.grizz.service.calculators;

import org.grizz.model.Embed;
import org.grizz.model.EmbedType;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.service.calculators.structures.Ranking;
import org.grizz.service.calculators.structures.SimpleRanking;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ImageRankingCalculator implements StatisticsCalculator {
    public static final String NAME = "imageRanking";
    private Ranking ranking = new SimpleRanking();

    @Override
    public void consume(Set<Entry> entries) {
        entries.forEach(e -> {
            if(isImagePresent(e.getEmbed())) {
                ranking.add(e, e.getVotes());
            }
            e.getComments().forEach(c -> {
                if(isImagePresent(c.getEmbed())) {
                    ranking.add(c, c.getVotes());
                }
            });
        });
    }

    private boolean isImagePresent(Embed embed) {
        return EmbedType.IMAGE.equals(embed.getType());
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

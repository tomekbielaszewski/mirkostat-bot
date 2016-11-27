package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.EmbedCounter;
import org.grizz.utils.StringPlural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class EmbedCountStatPrinter implements StatPrinter {
    @Autowired
    private EmbedCounter embedCounter;

    @Override
    public void print(Output output) {
        int numberOfEmbedTypes = embedCounter.getNumberOfEmbedTypes();
        ArrayList<String> parameters = new ArrayList<>();

        for (int i = 0; i < numberOfEmbedTypes; i++) {
            int embedTypeCount = embedCounter.getEmbedTypeCountOnPosition(i);

            parameters.add(String.valueOf(embedTypeCount));

            switch(embedCounter.getEmbedTypeOnPosition(i)) {
                case IMAGE : parameters.add(StringPlural.choose(new String[]{"Obrazek", "Obrazki", "Obrazków"}, embedTypeCount)); break;
                case VIDEO : parameters.add(StringPlural.choose(new String[]{"Filmik", "Filmiki", "Filmików"}, embedTypeCount)); break;
            }
        }

        output.append(String.format(template(numberOfEmbedTypes), parameters.toArray()));
    }

    private String template(int numberOfGenders) {
        StringBuilder builder = new StringBuilder();

        builder.append("Mirki dodały:\n");

        for (int i = 0; i < numberOfGenders; i++) {
            builder.append((i>=9?"":"0")+(i+1)+". %s %s\n");
        }

        builder.append("\n");

        return builder.toString();
    }
}

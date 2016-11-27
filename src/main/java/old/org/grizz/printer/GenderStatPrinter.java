package old.org.grizz.printer;

import old.org.grizz.output.Output;
import old.org.grizz.statistics.collector.GenderActivityCollector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class GenderStatPrinter implements StatPrinter {
    @Autowired
    private GenderActivityCollector genderActivityCollector;

    @Override
    public void print(Output output) {
        int numberOfGenders = genderActivityCollector.getNumberOfGenders();
        ArrayList<String> parameters = new ArrayList<>();

        for (int i = 0; i < numberOfGenders; i++) {
            if(StringUtils.isNotEmpty(genderActivityCollector.getGenderOnPosition(i))) {
                int genderCount = genderActivityCollector.getGenderCountOnPosition(i);

                switch(genderActivityCollector.getGenderOnPosition(i)) {
                    case "male" : parameters.add("Niebieskiepaski"); break;
                    case "female" : parameters.add("Różowepaski"); break;
                }
                parameters.add(String.valueOf(genderCount));
            }
        }

        output.append(String.format(template(numberOfGenders), parameters.toArray()));
    }

    private String template(int numberOfGenders) {
        StringBuilder builder = new StringBuilder();

        builder.append("Aktywność niebieskich i różowych pasków:\n");

        for (int i = 0; i < numberOfGenders; i++) {
            if(StringUtils.isNotEmpty(genderActivityCollector.getGenderOnPosition(i))) {
                builder.append((i>=9?"":"0")+(i+1)+". %s x%s\n");
            }
        }

        builder.append("\n");

        return builder.toString();
    }
}

package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.UserGroupActivityCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class UserGroupStatPrinter implements StatPrinter {
    @Autowired
    private UserGroupActivityCollector userGroupActivityCollector;

    @Override
    public void print(Output output) {
        int numberOfGenders = userGroupActivityCollector.getNumberOfUserGroups();
        ArrayList<String> parameters = new ArrayList<>();

        for (int i = 0; i < numberOfGenders; i++) {
            switch(userGroupActivityCollector.getUserGroupOnPosition(i)) {
                case GREEN: parameters.add("Zielonki"); break;
                case ORANGE: parameters.add("Pomarańczki"); break;
                case RED: parameters.add("Bordo"); break;
                case BLACK: parameters.add("Admini"); break;
                case BLUE: parameters.add("Sponsorowani"); break;
                case SILVER: parameters.add("Srebro"); break;
                case DELETED: parameters.add("Emoquit"); break;
            }
            parameters.add(String.valueOf(userGroupActivityCollector.getUserGroupCountOnPosition(i)));
        }

        output.append(String.format(template(numberOfGenders), parameters.toArray()));
    }

    private String template(int numberOfGenders) {
        StringBuilder builder = new StringBuilder();

        builder.append("Aktywność grup użytkowników:\n");

        for (int i = 0; i < numberOfGenders; i++) {
            builder.append((i>=9?"":"0")+(i+1)+". %s x%s\n");
        }

        builder.append("\n");

        return builder.toString();
    }
}

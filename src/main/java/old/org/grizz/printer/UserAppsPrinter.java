package old.org.grizz.printer;

import old.org.grizz.output.Output;
import old.org.grizz.statistics.collector.UserAppCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-04.
 */
@Service
public class UserAppsPrinter implements StatPrinter {
    @Autowired
    private UserAppCounter userAppCounter;

    @Override
    public void print(Output output) {
        int numberOfApps = userAppCounter.getNumberOfApps();
        String[] userApps = getUserApps(numberOfApps);

        output.append(String.format(template(numberOfApps), userApps));
    }

    private String[] getUserApps(int numberOfApps) {
        int params = 2;
        String[] userApps = new String[numberOfApps * params];

        for (int i = 0; i < numberOfApps * params; i += params) {
            int index = i/params;
            userApps[i] = userAppCounter.getAppOnPosition(index);
            userApps[i+1] = String.valueOf(userAppCounter.getAppCountOnPosition(index));
        }
        return userApps;
    }

    private String template(int numberOfApps) {
        StringBuilder builder = new StringBuilder();

        builder.append("Mirki ostatnio pisały z takich oto aplikacji:\n");
        for (int i = 0; i < numberOfApps; i++) {
            builder.append((i>=9?"":"0")+(i+1)+". %s x%s\n");
        }

        builder.append("\n");

        return builder.toString();
    }
}

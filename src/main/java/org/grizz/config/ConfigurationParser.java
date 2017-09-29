package org.grizz.config;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConfigurationParser {

    public Configuration parse(String[] args) {
        Configuration configuration = new Configuration();

        try {
            JCommander.newBuilder()
                    .addObject(configuration)
                    .build()
                    .parse(args);
        } catch (ParameterException e) {
            JCommander jCommander = e.getJCommander();
            jCommander.setProgramName("Mirkoonline bot");
            jCommander.usage();

            System.exit(1);
        }

        log.info("Loaded configuration: " + configuration);

        return configuration;
    }
}

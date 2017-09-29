package org.grizz;

import org.grizz.config.Configuration;
import org.grizz.config.ConfigurationParser;
import org.grizz.config.MainConfig;
import org.grizz.service.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Starter {
    public static void main(String... args) {
        Configuration configuration = new ConfigurationParser().parse(args);

        ConfigurableApplicationContext context = SpringApplication.run(MainConfig.class);
        Task task = context.getBean(Task.class);

        task.run(configuration.getHoursOfHistory());
    }
}

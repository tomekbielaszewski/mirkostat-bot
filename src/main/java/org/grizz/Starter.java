package org.grizz;

import org.grizz.config.Configuration;
import org.grizz.config.ConfigurationParser;
import org.grizz.model.WykopKeys;
import org.grizz.service.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableConfigurationProperties(WykopKeys.class)
public class Starter {
    public static void main(String... args) {
        Configuration configuration = new ConfigurationParser().parse(args);

        ConfigurableApplicationContext context = SpringApplication.run(Starter.class);
        Task task = context.getBean(Task.class);

        task.run(configuration.getHoursOfHistory());
    }
}

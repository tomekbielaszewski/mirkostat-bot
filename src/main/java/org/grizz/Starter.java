package org.grizz;

import org.grizz.config.Configuration;
import org.grizz.config.ConfigurationParser;
import org.grizz.service.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        Configuration configuration = new ConfigurationParser().parse(args);

        ConfigurableApplicationContext context = SpringApplication.run(Starter.class);
        Task bot = context.getBean(Task.class);

        bot.run(configuration);
    }
}

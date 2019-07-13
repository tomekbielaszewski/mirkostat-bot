package org.grizz.config;

import org.grizz.service.Task;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("org.grizz")
public class MainConfig {
    private final Task task;

    public MainConfig(Task task) {
        this.task = task;
    }

    @PostConstruct
    public void runTask() {
        task.run();
    }
}

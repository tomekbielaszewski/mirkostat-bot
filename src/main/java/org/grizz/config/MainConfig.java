package org.grizz.config;

import org.grizz.service.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan("org.grizz")
public class MainConfig {
    @Autowired
    private Task task;

    @PostConstruct
    public void runTask() {
        task.run();
    }
}

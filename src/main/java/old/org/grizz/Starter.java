package old.org.grizz;

import old.org.grizz.output.Output;
import old.org.grizz.output.StringOutput;
import old.org.grizz.service.MicroblogService;
import old.org.grizz.service.post.EntryPoster;
import org.apache.log4j.Logger;
import old.org.grizz.springconfig.BaseConfig;
import old.org.grizz.statistics.StatCollectorPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class Starter {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private StatCollectorPool statCollectorPool;
    @Autowired
    private Output output;
    @Autowired
    private EntryPoster entryPoster;
    @Autowired
    private MicroblogService microblogService;

    public static void main_old(String[] args) {
        Logger logger = Logger.getLogger(Starter.class);

        logger.info("Application context is loading...");
        @SuppressWarnings("resource")
        ApplicationContext context = new AnnotationConfigApplicationContext(BaseConfig.class);
        logger.info("Application context loading is done!");

        context.getBean(Starter.class).run();
    }

//    @Scheduled(cron = "00 00 17 * * *")
    public void run() {
        String mirkoStatBotEntryBody = getMirkoStatBotEntryBody();
        printMirkoStatBot(mirkoStatBotEntryBody);
    }

    public String getMirkoStatBotEntryBody() {

        statCollectorPool.collect();
        statCollectorPool.printStats();
        statCollectorPool.reset();

        try {
            return ((StringOutput) output).getOutput();
        } finally {
            output.flush();
        }
    }

    private void printMirkoStatBot(String mirkoStatBotEntryBody) {
        entryPoster.post(mirkoStatBotEntryBody);
    }
}

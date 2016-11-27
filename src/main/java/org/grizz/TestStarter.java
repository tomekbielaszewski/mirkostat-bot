package org.grizz;

import com.crozin.wykop.sdk.Application;
import com.crozin.wykop.sdk.AuthenticatedSession;
import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.domain.Entry;
import org.apache.log4j.Logger;
import org.grizz.service.MicroblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Grizz on 2014-06-19.
 */
@Service
public class TestStarter {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private MicroblogService microblogService;

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TestStarter.class);

        logger.info("Application context is loading...");
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext**.xml");
        logger.info("Application context loading is done!");

        context.getBean(TestStarter.class).run();
    }

    private void run() {
//        microblogService.login();
//        logger.warn(microblogService.add("( ͡° ͜ʖ ͡°)"));
        Application app = new Application("TPJl24Jc5J", "mo4JG9Ed4E");
        AuthenticatedSession session = app.openSession("Ypbaxw0FUUtS4swAvxpI");

//        session.getMicroblogService().addEntry("( ͡° ͜ʖ ͡°)");

        Command command = new Command("stream", "index", new String[]{"page", "10"});
//        System.out.println(session.execute(command));
        List<Entry> resultList = session.getResultList(command, Entry.class);

        for (Entry entry : resultList) {
            System.out.println(entry.getId() + ": " + entry.getAuthor());
        }
    }
}

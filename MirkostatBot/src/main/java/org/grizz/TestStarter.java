package org.grizz;

import org.apache.log4j.Logger;
import org.grizz.service.HTTPRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-06-19.
 */
@Service
public class TestStarter {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private HTTPRequestService requestService;

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(TestStarter.class);

        logger.info("Application context is loading...");
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext**.xml");
        logger.info("Application context loading is done!");

        context.getBean(TestStarter.class).run();
    }

    private void run() {
        logger.info(requestService.sendGet("http://a.wykop.pl/tag/index/gfy/appkey/TPJl24Jc5J"));
    }
}

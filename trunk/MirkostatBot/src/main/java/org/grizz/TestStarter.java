package org.grizz;

import org.grizz.service.HTTPRequestService;
import org.grizz.service.MicroblogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-06-19.
 */
@Service
public class TestStarter {

    @Autowired
    private HTTPRequestService requestService;

    public static void main(String[] args) {
        System.out.println("Application context is loading...");
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext**.xml");
        System.out.println("Application context loading is done!");

        context.getBean(TestStarter.class).run();
    }

    private void run() {
        System.out.println(requestService.sendGet("http://a.wykop.pl/tag/index/gfy/appkey/TPJl24Jc5J"));
    }
}

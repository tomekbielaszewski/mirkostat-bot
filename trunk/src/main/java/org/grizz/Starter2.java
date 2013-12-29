package org.grizz;

import org.grizz.statistics.StatCollectorPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Starter2 {
	@Autowired
	private StatCollectorPool statCollectorPool;
	
	public static void main(String[] args) {
		System.out.println("Application context is loading...");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext**.xml");
		System.out.println("Application context loading is done!");
		
		context.getBean(Starter2.class).run();
	}

	private void run() {
		statCollectorPool.collect();
		statCollectorPool.printStats();
	}
}

package org.grizz;

import java.util.Arrays;
import java.util.List;

import org.grizz.model.Entry;
import org.grizz.service.MassiveHibernateOperationsService;
import org.grizz.service.MicroblogService;
import org.grizz.service.TagExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Starter {
	@Autowired
	MicroblogService microblog;
	@Autowired
	MassiveHibernateOperationsService massiveHibernateOperationsService;
	@Autowired
	TagExtractor tagExtractor;
	
	public static void main(String[] args) {
		System.out.println("Application context is loading...");
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:META-INF/spring/applicationContext**.xml");
		System.out.println("Application context loading is done!");
		
		context.getBean(Starter.class).run();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() {
		long delay = 10000;
		boolean isFirstTick = true;
		
		while(true) {
			long start = System.currentTimeMillis();
			Entry[] entries = microblog.index();
			
			List entriesList = Arrays.asList(entries);
			
			for (Object obj : entriesList) {
				Entry entry = (Entry) obj;
				
//				entry.setTags(tagExtractor.extract(entry.getBody()));
				
				if(entry.getBody().length() > 2048) {
					entry.setBody(entry.getBody().substring(0, 2047));
				}
			}
			
			entriesList = massiveHibernateOperationsService.persistOrUpdateAll(entriesList, Entry.class);
			
			System.out.printf("################################## TICK ##################################"
							+ "\n# Pobrano %d wpisow w czasie %d ms\n"
							+ "##########################################################################\n",
					entries.length, (System.currentTimeMillis() - start));
			
			for (Object obj : entriesList) {
				Entry entry = (Entry) obj;
				
				System.out.println("@"+entry.getAuthor());
				System.out.println(entry.getBody());
				System.out.println("################# Zapisano: #################");
			}
			
			if(!isFirstTick) {
				delay = calculateDelay(delay, entriesList.size(), 48);
			}
			
			isFirstTick = false;
			
			System.out.printf("################# Waiting %d ms... #################\n", delay);
			
			try { Thread.sleep(delay); } catch (InterruptedException e) { }
		}
	}

	private long calculateDelay(long currentDelay, int downloaded, int maxToDownload) {
		if(downloaded == 0) {
			downloaded = 1;
		}
		maxToDownload /= 2;
		float factor = (float)maxToDownload/(float)downloaded;
		return (long) (currentDelay * factor) > 300000 ? 300000 : (long) (currentDelay * factor);
	}
}

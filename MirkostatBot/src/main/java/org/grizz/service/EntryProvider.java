package org.grizz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.grizz.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;

public class EntryProvider {
	private final long COLLECTION_PERIOD;
	
	@Autowired
	private MicroblogService microblog;
	private boolean oldEntryFound;
	
	public EntryProvider(long collectionPeriod) {
		COLLECTION_PERIOD = collectionPeriod;
	}

	public List<Entry> getEntries() {
		int page = 0;
		Date currentDate = new Date();
		List<Entry> entriesDownloaded = new ArrayList<>();
		
		while(!(oldEntryFound || page >= 200)) {
			System.out.println("Downloading microblog page "+page+"...");
			Entry[] entries = microblog.index(page);
			System.out.println("Downloaded! Now validating entries date...");
			
			for (Entry entry : entries) {
				if(validateEntryDate(entry.getDateAdded(), currentDate)) {
					entriesDownloaded.add(entry);
				} else {
					oldEntryFound = true;
					break;
				}
			}
			
			if(oldEntryFound || page > 200) {
				System.out.println("On the page "+page+" found entry which is more than 24h old!\n"
						+ "Download is finished!");
			} else {
				System.out.println("All entries are young!");
			}
			
			page++;
		}
		
		return entriesDownloaded;
	}

	private boolean validateEntryDate(Date dateAdded, Date currentDate) {
		long timeAdded = dateAdded.getTime();
		long currentTime = currentDate.getTime();
		
		return (currentTime - timeAdded) < COLLECTION_PERIOD;
	}
}

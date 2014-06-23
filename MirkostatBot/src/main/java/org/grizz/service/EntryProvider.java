package org.grizz.service;

import org.grizz.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryProvider {
	private final long COLLECTION_PERIOD;
	
	@Autowired
	private MicroblogService microblog;

	public EntryProvider(long collectionPeriod) {
		COLLECTION_PERIOD = collectionPeriod;
	}

	public List<Entry> getEntries() {
		Date currentDate = new Date();
		List<Entry> entriesDownloaded = new ArrayList<>();

		boolean isOldEntryFound = iterateWithStreamIndex(entriesDownloaded, currentDate, 100);
        if(!isOldEntryFound) {
            Entry lastEntry = entriesDownloaded.get(entriesDownloaded.size() - 1);
            iterateWithStreamfirstId(lastEntry.getId(), entriesDownloaded, currentDate);
        }
		
		return entriesDownloaded;
	}

    private boolean iterateWithStreamIndex(List<Entry> downloadedEntries, Date currentDate, int maxPage) {
        boolean oldEntryFound = false;
        int page = 1;

        while(!(oldEntryFound || page >= maxPage)) {
            Entry[] entries = microblog.index(page);

            for (Entry entry : entries) {
                if(validateEntryDate(entry.getDateAdded(), currentDate)) {
                    downloadedEntries.add(entry);
                } else {
                    oldEntryFound = true;
                    break;
                }
            }
            page++;
        }

        return oldEntryFound;
    }

    private void iterateWithStreamfirstId(Long lastId, List<Entry> downloadedEntries, Date currentDate) {
        boolean oldEntryFound = false;

        while(!oldEntryFound) {
            Entry[] entries = microblog.next(lastId);

            for (Entry entry : entries) {
                if(validateEntryDate(entry.getDateAdded(), currentDate)) {
                    downloadedEntries.add(entry);
                } else {
                    oldEntryFound = true;
                    break;
                }
            }

            lastId = entries[entries.length - 1].getId();
        }
    }

	private boolean validateEntryDate(Date dateAdded, Date currentDate) {
		long timeAdded = dateAdded.getTime();
		long currentTime = currentDate.getTime();
		
		return (currentTime - timeAdded) < COLLECTION_PERIOD;
	}
}
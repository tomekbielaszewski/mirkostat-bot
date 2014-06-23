package org.grizz.service;

import org.grizz.model.Entry;
import org.grizz.service.post.WykopUrlSigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class MicroblogService {
	@Autowired
	private URLBuilder builder;
	@Autowired
	private HTTPRequestService requestService;
    @Autowired
    private EntryFactory entryFactory;
    @Autowired
    private WykopUrlSigner signer;

    public Entry[] index() {
		return index(1);
	}
	
	public Entry[] index(int page) {
        System.out.print("Page: " + page);

		String json = requestService.sendGet(builder.getLatestMicroblogEntriesURL(page));
		Entry[] entries = entryFactory.getEntries(json);

        System.out.println(" | Last entry date: " + new SimpleDateFormat("d k:m:s").format(entries[entries.length - 1].getDateAdded()));

        return entries;
	}

    public Entry[] next(Long entryId) {
        System.out.print("First id: " + entryId);
        String json = requestService.sendGet(builder.getMicroblogEntriesAfterURL(entryId));
        Entry[] entries = entryFactory.getEntries(json);
        System.out.print(" | Last id: " + entries[entries.length - 1].getId());
        System.out.println(" | Last entry date: " + new SimpleDateFormat("dd kk:mm:ss").format(entries[entries.length - 1].getDateAdded()));
        return entries;
    }

    public String add(String entryBody) {
        Map<String, String> postContent = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        String url = builder.getMicroblogAddEntryURL();

        postContent.put("body", entryBody);

        return requestService.sendPost(url, postContent, headers);
    }

    public Entry[] next(int entryId) {
        return next(Long.valueOf(entryId));
    }
}

package org.grizz.service;

import com.google.gson.Gson;
import org.apache.commons.lang3.Validate;
import org.grizz.model.Entry;
import org.grizz.model.UserKeyModel;
import org.grizz.service.post.WykopUrlSigner;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MicroblogService {
    private final String accountKey;

    @Autowired
	private URLBuilder builder;
	@Autowired
	private HTTPRequestService requestService;
    @Autowired
    private EntryFactory entryFactory;
    @Autowired
    private WykopUrlSigner signer;

    private String userkey;

    public MicroblogService(String accountKey) {
        this.accountKey = accountKey;
    }

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
        Validate.notNull(userkey);

        Map<String, String> postContent = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        String url = builder.getMicroblogAddEntryURL(userkey);

        postContent.put("body", entryBody);

        return requestService.sendPost(url, postContent, headers);
    }

    public Entry[] next(int entryId) {
        return next(Long.valueOf(entryId));
    }

    public void login() {
        Map<String, String> postContent = new HashMap<>();
        String url = builder.getWykopLoginURL();

        postContent.put("accountkey", accountKey);

        String json = requestService.sendPost(url, postContent, Collections.EMPTY_MAP);

        Gson gson = new Gson();
        UserKeyModel userKeyModel = gson.fromJson(json, UserKeyModel.class);
        Validate.notNull(userKeyModel.getUserkey());

        this.userkey = userKeyModel.getUserkey();
    }

    public void logout() {
        userkey = null;
    }
}

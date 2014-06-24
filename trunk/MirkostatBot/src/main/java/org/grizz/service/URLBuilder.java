package org.grizz.service;

public class URLBuilder {
	private static final String BASE_API_URL = "http://a.wykop.pl/";
    private static final String LATEST_MICROBLOG_ENTRIES_URL = "stream/index/";
    private static final String MICROBLOG_ENTRIES_AFTER_ENTRY_ID = "stream/firstid/";
    private static final String MICROBLOG_ADD_ENTRY = "entries/add/";
    private static final String WYKOP_LOGIN = "user/login/";

    private String appKey;

	public URLBuilder(final String appKey) {
		this.appKey = appKey;
	}
	
	public String getLatestMicroblogEntriesURL(int page) {
		return BASE_API_URL + LATEST_MICROBLOG_ENTRIES_URL + appKey() + ",page,"+page+"," + clearOutput();
	}

    public String getMicroblogEntriesAfterURL(Long entryId) {
        return BASE_API_URL + MICROBLOG_ENTRIES_AFTER_ENTRY_ID + entryId + "," + appKey()  + "," + clearOutput();
    }

    public String getMicroblogAddEntryURL(String userKey) {
        return BASE_API_URL + MICROBLOG_ADD_ENTRY + appKey() + ",userkey," + userKey  + "," + clearOutput();
    }

    public String getWykopLoginURL() {
        return BASE_API_URL + WYKOP_LOGIN + appKey() + "," + clearOutput();
    }

    private String appKey() {
        return "appkey," + appKey;
    }

	private String clearOutput() {
		return "output,clear";
	}

}

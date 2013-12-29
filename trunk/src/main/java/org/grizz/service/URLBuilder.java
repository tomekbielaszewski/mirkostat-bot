package org.grizz.service;

public class URLBuilder {
	private static final String BASE_URL = "http://a.wykop.pl/";
	private static final String LATEST_MICROBLOG_ENTRIES_URL = "stream/index/";
	
	private String appKey;
	
	public URLBuilder(final String appKey) {
		this.appKey = appKey;
	}
	
	public String getLatestMicroblogEntriesURL(int page) {
		return BASE_URL + LATEST_MICROBLOG_ENTRIES_URL + appKey() + ",page,"+page+"," + clearOutput(); 
	}

	private String appKey() {
		return "appkey," + appKey;
	}
	
	private String clearOutput() {
		return "output,clear";
	}
}

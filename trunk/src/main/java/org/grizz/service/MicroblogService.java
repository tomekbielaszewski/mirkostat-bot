package org.grizz.service;

import org.grizz.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class MicroblogService {
//	private URLEncoder encoder;
	
	@Autowired
	private URLBuilder builder;
	
	@Autowired
	private HTTPRequestService requestService;
	
	public Entry[] index() {
		return index(0);
	}
	
	public Entry[] index(int page) {
		String json = requestService.sendGet(builder.getLatestMicroblogEntriesURL(page));
		Gson gson = new Gson();
		
		Entry[] entries = gson.fromJson(json, Entry[].class);
		
		return entries;
	}
}

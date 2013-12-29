package org.grizz.service;

import java.util.Arrays;
import java.util.List;

import org.grizz.model.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryProvider {
	@Autowired
	private MicroblogService microblog;

	public List<Entry> getLatestEntries() {
		return Arrays.asList(microblog.index());
	}

}

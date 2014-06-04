package org.grizz.model;

import java.util.Collections;
import java.util.List;

public class EntryComment extends Entry {

	@Override
	public void setComments(List<EntryComment> comments) { }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntryComment> getComments() {
		return Collections.EMPTY_LIST;
	}
}

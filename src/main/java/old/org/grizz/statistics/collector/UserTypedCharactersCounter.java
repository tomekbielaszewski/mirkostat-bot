package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import org.springframework.stereotype.Service;

@Service
public class UserTypedCharactersCounter extends AbstractStatCounter {

	@Override
	public void collect(Entry entry) {
		addToCounter(entry.getAuthor(), entry.getBody().length());

        for (EntryComment entryComment : entry.getComments()) {
            addToCounter(entryComment.getAuthor(), entryComment.getBody().length());
        }
    }
	
	public int getTypedCharactersRate(int position) {
		return getEntryOnPosition(position).getValue();
	}
	
	public String getUserTypedCharactersRate(int position) {
		return (String) getEntryOnPosition(position).getKey();
	}
}

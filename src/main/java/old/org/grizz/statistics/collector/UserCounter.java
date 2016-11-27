package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import old.org.grizz.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserCounter implements StatCollector {
	private Set<String> users = new HashSet<>();

	@Override
	public void collect(Entry entry) {
		users.add(entry.getAuthor());

        for (User user : entry.getVoters()) {
            users.add(user.getAuthor());
        }

        for (EntryComment entryComment : entry.getComments()) {
            users.add(entryComment.getAuthor());

            for (User user : entryComment.getVoters()) {
                users.add(user.getAuthor());
            }
        }


    }

	@Override
	public void reset() {
		users.clear();
	}
	
	public int getNumberOfUsers() {
		return users.size();
	}

}

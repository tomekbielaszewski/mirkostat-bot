package old.org.grizz.statistics.collector;

import old.org.grizz.model.Entry;
import old.org.grizz.model.EntryComment;
import old.org.grizz.model.User;
import old.org.grizz.model.UserGroup;
import org.springframework.stereotype.Service;

/**
 * Created by Grizz on 2014-07-05.
 */
@Service
public class UserGroupActivityCollector extends AbstractStatCounter {
    @Override
    public void collect(Entry entry) {
        addToCounter(UserGroup.byValue(entry.getAuthorGroup()), 1);

        for (User user : entry.getVoters()) {
            addToCounter(UserGroup.byValue(user.getAuthorGroup()), 1);
        }

        for (EntryComment entryComment : entry.getComments()) {
            addToCounter(UserGroup.byValue(entryComment.getAuthorGroup()), 1);

            for (User user : entryComment.getVoters()) {
                addToCounter(UserGroup.byValue(user.getAuthorGroup()), 1);
            }
        }
    }

    public int getNumberOfUserGroups() {
        return getCounter().size();
    }

    public UserGroup getUserGroupOnPosition(int position) {
        return (UserGroup) getEntryOnPosition(position).getKey();
    }

    public int getUserGroupCountOnPosition(int position) {
        return getEntryOnPosition(position).getValue();
    }
}

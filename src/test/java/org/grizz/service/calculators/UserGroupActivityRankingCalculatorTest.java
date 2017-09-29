package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.grizz.service.calculators.structures.RankedObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;
import pl.grizwold.microblog.model.User;
import pl.grizwold.microblog.model.UserGroup;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class UserGroupActivityRankingCalculatorTest {
    private static final User USER_BLACK = User.builder().authorGroup(UserGroup.BLACK).build();
    private static final User USER_BLUE = User.builder().authorGroup(UserGroup.BLUE).build();

    private UserGroupActivityRankingCalculator calculator = new UserGroupActivityRankingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().authorGroup(UserGroup.BLACK).voters(Lists.newArrayList()).build();
        EntryComment secondComment = EntryComment.builder().authorGroup(UserGroup.BLUE).voters(Lists.newArrayList()).build();
        EntryComment thirdComment = EntryComment.builder().authorGroup(UserGroup.BLACK).voters(Lists.newArrayList()).build();

        Entry entry = Entry.builder().authorGroup(UserGroup.BLACK).voters(Lists.newArrayList(USER_BLACK, USER_BLUE))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().authorGroup(UserGroup.GREEN).voters(Lists.newArrayList(USER_BLUE))
                .comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry, entry2));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(3));
        assertThat(value.get(0), is(new RankedObject(UserGroup.BLACK, 4)));
        assertThat(value.get(1), is(new RankedObject(UserGroup.BLUE, 3)));
        assertThat(value.get(2), is(new RankedObject(UserGroup.GREEN, 1)));
    }

}
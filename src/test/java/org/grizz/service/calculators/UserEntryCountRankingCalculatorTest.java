package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.grizz.service.calculators.structures.RankedObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;
import pl.grizwold.microblog.model.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class UserEntryCountRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";
    private static final String AUTHOR_3 = "auth3";
    private static final String AUTHOR_4 = "auth4";
    private static final String AUTHOR_5 = "auth5";
    private static final String AUTHOR_6 = "auth6";
    private static final User USER1 = User.builder().author(AUTHOR_5).build();
    private static final User USER2 = User.builder().author(AUTHOR_6).build();

    private UserEntryCountRankingCalculator calculator = new UserEntryCountRankingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).voters(Lists.newArrayList()).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).voters(Lists.newArrayList()).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).voters(Lists.newArrayList()).build();

        Entry entry = Entry.builder().author(AUTHOR_3).voters(Lists.newArrayList(USER1, USER2))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().id(1l).author(AUTHOR_4).voters(Lists.newArrayList(USER1))
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().id(2l).author(AUTHOR_4).voters(Lists.newArrayList(USER1))
                .comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry, entry2, entry3));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(AUTHOR_4, 2)));
        assertThat(value.get(1), is(new RankedObject(AUTHOR_3, 1)));
    }
}
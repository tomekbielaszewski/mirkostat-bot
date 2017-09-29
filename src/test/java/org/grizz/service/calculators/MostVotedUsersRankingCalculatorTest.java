package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.grizz.service.calculators.structures.RankedObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class MostVotedUsersRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";

    private MostVotedUsersRankingCalculator calculator = new MostVotedUsersRankingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).voteCount(101).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).voteCount(100).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).voteCount(100).build();

        Entry entry = Entry.builder().author(AUTHOR_1).voteCount(100)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().id(1l).author(AUTHOR_1).voteCount(100)
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().id(2l).author(AUTHOR_1).voteCount(100)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry, entry2, entry3));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(AUTHOR_1, 501)));
        assertThat(value.get(1), is(new RankedObject(AUTHOR_2, 100)));
    }

}
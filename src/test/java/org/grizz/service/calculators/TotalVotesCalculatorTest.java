package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TotalVotesCalculatorTest {

    private TotalVotesCalculator calculator = new TotalVotesCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().voteCount(101).build();
        EntryComment secondComment = EntryComment.builder().voteCount(100).build();
        EntryComment thirdComment = EntryComment.builder().voteCount(100).build();

        Entry entry = Entry.builder().voteCount(100)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().id(1l).voteCount(100)
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().id(2l).voteCount(100)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry, entry2, entry3));
        int value = (int) calculator.getValue();

        assertThat(value, is(601));
    }
}
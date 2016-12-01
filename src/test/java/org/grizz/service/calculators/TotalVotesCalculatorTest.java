package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TotalVotesCalculatorTest {

    private TotalVotesCalculator calculator = new TotalVotesCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().votes(101).build();
        EntryComment secondComment = EntryComment.builder().votes(100).build();
        EntryComment thirdComment = EntryComment.builder().votes(100).build();

        Entry entry = Entry.builder().votes(100)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().id(1l).votes(100)
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().id(2l).votes(100)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        int value = (int) calculator.getValue();

        assertThat(value, is(601));
    }
}
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
public class TotalEntriesCalculatorTest {
    private TotalEntriesCalculator calculator = new TotalEntriesCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().build();
        EntryComment secondComment = EntryComment.builder().build();
        EntryComment thirdComment = EntryComment.builder().build();

        Entry entry = Entry.builder()
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder()
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2));
        int value = (int) calculator.getValue();

        assertThat(value, is(2));
    }
}
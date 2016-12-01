package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.RandomStringUtils;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TotalUntaggedEntriesCalculatorTest {
    private TotalUntaggedEntriesCalculator calculator = new TotalUntaggedEntriesCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().body("blah blah "+tag()+" fljsdl jkl l "+tag()+" jklfsd.").build();
        EntryComment secondComment = EntryComment.builder().body("").build();
        EntryComment thirdComment = EntryComment.builder().body(tag()).build();

        Entry entry = Entry.builder().body(tag())
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().body(tag() + " jkl fldskj jkl fdskl" + tag())
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().body("")
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        int value = (int) calculator.getValue();

        assertThat(value, is(1));
    }

    private String tag() {
        return '#'+ RandomStringUtils.randomAlphabetic(10);
    }
}
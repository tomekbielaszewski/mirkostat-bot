package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TotalTagsUsedCalculatorTest {
    private TotalTagsUsedCalculator calculator = new TotalTagsUsedCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().body("blah blah " + tag() + " fljsdl jkl l " + tag() + " jklfsd.\n" + tag()).build();
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

        calculator.consume(Lists.newArrayList(entry, entry2, entry3));
        int value = (int) calculator.getValue();

        assertThat(value, is(6));
    }

    private String tag() {
        return '#' + RandomStringUtils.randomAlphabetic(10);
    }

}
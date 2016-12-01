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
public class TotalUsersNotTaggingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";
    private static final String AUTHOR_3 = "auth3";

    private TotalUsersNotTaggingCalculator calculator = new TotalUsersNotTaggingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).body("blah blah "+tag()+" fljsdl jkl l "+tag()+" jklfsd.").build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).body("").build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_3).body(tag()).build();

        Entry entry = Entry.builder().body(tag()).author(AUTHOR_1)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().body(tag() + " jkl fldskj jkl fdskl" + tag()).author(AUTHOR_2)
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().body("").author(AUTHOR_3)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        int value = (int) calculator.getValue();

        assertThat(value, is(1));
    }

    private String tag() {
        return '#'+ RandomStringUtils.randomAlphabetic(10);
    }
}
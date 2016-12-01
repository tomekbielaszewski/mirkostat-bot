package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.RandomStringUtils;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.service.calculators.structures.RankedObject;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TotalCharactersTypedCalculatorTest {
    private TotalCharactersTypedCalculator calculator = new TotalCharactersTypedCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().body(random(54354)).build();
        EntryComment secondComment = EntryComment.builder().body(random(12345)).build();
        EntryComment thirdComment = EntryComment.builder().body(random(5435)).build();

        Entry entry = Entry.builder().body(random(5334))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().body(random(123))
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().body(random(6643))
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        int value = (int) calculator.getValue();

        assertThat(value, is(54354+12345+5435+5334+123+6643));
    }

}
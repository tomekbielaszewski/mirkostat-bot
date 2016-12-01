package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.service.calculators.structures.RankedObject;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class MostVotedTagsRankingCalculatorTest {
    private static final String TAG1 = "#first";
    private static final String TAG2 = "#second2";

    private MostVotedTagsRankingCalculator calculator = new MostVotedTagsRankingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().body("blah blah "+TAG1+" fljsdl jkl l "+TAG2+" jklfsd.").votes(101).build();
        EntryComment secondComment = EntryComment.builder().body("").votes(100).build();
        EntryComment thirdComment = EntryComment.builder().body(TAG1).votes(100).build();

        Entry entry = Entry.builder().votes(100).body(TAG2)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().votes(100).body(TAG1 + " jkl fldskj jkl fdskl" + TAG2)
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().votes(100).body("")
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(TAG1, 301)));
        assertThat(value.get(1), is(new RankedObject(TAG2, 201)));
    }

}
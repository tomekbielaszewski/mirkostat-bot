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
public class MostVotedTagsRankingCalculatorTest {
    private static final String TAG1 = "first";
    private static final String TAG2 = "second2";

    private MostVotedTagsRankingCalculator calculator = new MostVotedTagsRankingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().body("blah blah " + tag(TAG1) + " fljsdl jkl l " + tag(TAG2) + " jklfsd.").voteCount(101).build();
        EntryComment secondComment = EntryComment.builder().body("").voteCount(100).build();
        EntryComment thirdComment = EntryComment.builder().body(tag(TAG1)).voteCount(100).build();

        Entry entry = Entry.builder().voteCount(100).body(tag(TAG2))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().voteCount(100).body(tag(TAG1) + " jkl fldskj jkl fdskl" + tag(TAG2))
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().voteCount(100).body("")
                .comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry, entry2, entry3));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(TAG1, 301)));
        assertThat(value.get(1), is(new RankedObject(TAG2, 201)));
    }

    private String tag(String name) {
        return '#' + name;
    }
}
package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.service.calculators.structures.RankedObject;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentsRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";

    private CommentsRankingCalculator calculator = new CommentsRankingCalculator();

    @Test
    public void doesNotCountingEntryWithoutComments() throws Exception {
        Entry entry = Entry.builder().author(AUTHOR_1).votes(123).comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(0));
    }

    @Test
    public void commentsAreOrderedBasedOnVotes() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).votes(8922).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).votes(8921).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).votes(784).build();

        Entry entry = Entry.builder().author(AUTHOR_1).body(random(4567))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        calculator.consume(Sets.newHashSet(entry));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(3));
        assertThat(value.get(0), is(new RankedObject(firstComment, 8922)));
        assertThat(value.get(1), is(new RankedObject(secondComment, 8921)));
        assertThat(value.get(2), is(new RankedObject(thirdComment, 784)));
    }

    @Test
    public void countsCommentsFromTwoDifferentEntries() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).votes(8922).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).votes(8921).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).votes(784).build();
        EntryComment fourthComment = EntryComment.builder().author(AUTHOR_2).votes(783).build();

        Entry entry = Entry.builder().author(AUTHOR_1)
                .comments(Lists.newArrayList(
                        thirdComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().author(AUTHOR_2)
                .comments(Lists.newArrayList(
                        fourthComment,
                        secondComment
                )).build();

        calculator.consume(Sets.newHashSet(entry, entry2));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(4));
        assertThat(value.get(0), is(new RankedObject(firstComment, 8922)));
        assertThat(value.get(1), is(new RankedObject(secondComment, 8921)));
        assertThat(value.get(2), is(new RankedObject(thirdComment, 784)));
        assertThat(value.get(3), is(new RankedObject(fourthComment, 783)));
    }
}
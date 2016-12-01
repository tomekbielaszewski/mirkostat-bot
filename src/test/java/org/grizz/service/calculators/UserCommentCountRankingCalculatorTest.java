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

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class UserCommentCountRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";

    private UserCommentCountRankingCalculator calculator = new UserCommentCountRankingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).votes(8922).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).votes(8921).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).votes(784).build();
        EntryComment fourthComment = EntryComment.builder().author(AUTHOR_2).build();
        EntryComment fifthComment = EntryComment.builder().author(AUTHOR_1).build();

        Entry entry = Entry.builder().author(AUTHOR_1).body(random(4567))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder()
                .comments(Lists.newArrayList(fourthComment, fifthComment)).build();

        Entry entry3 = Entry.builder().author(AUTHOR_1)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(AUTHOR_1, 3)));
        assertThat(value.get(1), is(new RankedObject(AUTHOR_2, 2)));
    }

}
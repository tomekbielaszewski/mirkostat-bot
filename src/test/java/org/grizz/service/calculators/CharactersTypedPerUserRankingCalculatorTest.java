package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.grizz.service.calculators.structures.RankedObject;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class CharactersTypedPerUserRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";

    private CharactersTypedPerUserRankingCalculator calculator = new CharactersTypedPerUserRankingCalculator();

    @Test
    public void countsTwoDifferentUsersEntries() throws Exception {
        Entry entry1 = Entry.builder().author(AUTHOR_1).body(random(4567)).comments(Lists.newArrayList()).build();
        Entry entry2 = Entry.builder().author(AUTHOR_2).body(random(6233)).comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry1, entry2));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, CoreMatchers.hasItem(new RankedObject(AUTHOR_1, 4567)));
        assertThat(value, CoreMatchers.hasItem(new RankedObject(AUTHOR_2, 6233)));
    }

    @Test
    public void sumsCommentAndEntryOfSameUser() throws Exception {
        Entry entry1 = Entry.builder().author(AUTHOR_1).body(random(4567))
                .comments(Lists.newArrayList(EntryComment.builder().author(AUTHOR_1).body(random(784)).build())).build();

        calculator.consume(Lists.newArrayList(entry1));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, CoreMatchers.hasItem(new RankedObject(AUTHOR_1, 4567 + 784)));
    }

    @Test
    public void countsCOmmentAndEntryOfSameUserAndAnotherUserComment() throws Exception {
        Entry entry1 = Entry.builder().author(AUTHOR_1).body(random(4567))
                .comments(Lists.newArrayList(
                        EntryComment.builder().author(AUTHOR_1).body(random(784)).build(),
                        EntryComment.builder().author(AUTHOR_2).body(random(8921)).build()
                )).build();

        calculator.consume(Lists.newArrayList(entry1));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, CoreMatchers.hasItem(new RankedObject(AUTHOR_1, 4567 + 784)));
        assertThat(value, CoreMatchers.hasItem(new RankedObject(AUTHOR_2, 8921)));
    }
}
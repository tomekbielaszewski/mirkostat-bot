package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.grizz.model.*;
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
public class GenderActivityRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private static final User MALE_USER = User.builder().authorSex(MALE).build();
    private static final User FEMALE_USER = User.builder().authorSex(FEMALE).build();

    private GenderActivityRankingCalculator calculator = new GenderActivityRankingCalculator();

    @Test
    public void countActivityInEntriesVotesAndComments() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).authorSex(MALE).voters(Lists.newArrayList()).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).authorSex(FEMALE).voters(Lists.newArrayList()).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).voters(Lists.newArrayList()).build();

        Entry entry = Entry.builder().author(AUTHOR_1).authorSex(FEMALE).voters(Lists.newArrayList(MALE_USER, FEMALE_USER, MALE_USER))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().author(AUTHOR_1).authorSex(MALE).voters(Lists.newArrayList(FEMALE_USER, MALE_USER))
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(MALE, 5)));
        assertThat(value.get(1), is(new RankedObject(FEMALE, 4)));
    }
}
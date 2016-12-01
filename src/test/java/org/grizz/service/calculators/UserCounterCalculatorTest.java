package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.model.User;
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
public class UserCounterCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";
    private static final String AUTHOR_3 = "auth3";
    private static final String AUTHOR_4 = "auth4";
    private static final String AUTHOR_5 = "auth5";
    private static final String AUTHOR_6 = "auth6";
    private static final User USER1 = User.builder().author(AUTHOR_5).build();
    private static final User USER2 = User.builder().author(AUTHOR_6).build();

    private UserCounterCalculator calculator = new UserCounterCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).voters(Lists.newArrayList()).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).voters(Lists.newArrayList()).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).voters(Lists.newArrayList()).build();

        Entry entry = Entry.builder().author(AUTHOR_3).voters(Lists.newArrayList(USER1, USER2))
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().author(AUTHOR_4).voters(Lists.newArrayList(USER1))
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2));
        int value = (int) calculator.getValue();

        assertThat(value, is(6));
    }

}
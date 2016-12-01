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
public class UserAppRankingCalculatorTest {
    private static final String APP_1 = "app1";
    private static final String APP_2 = "app2";
    private static final String APP_3 = "app3";

    private UserAppRankingCalculator calculator = new UserAppRankingCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().app(APP_1).build();
        EntryComment secondComment = EntryComment.builder().app(APP_2).build();
        EntryComment thirdComment = EntryComment.builder().app(APP_2).build();
        EntryComment fourthComment = EntryComment.builder().app(APP_1).build();
        EntryComment fifthComment = EntryComment.builder().build();

        Entry entry = Entry.builder().app(APP_3)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder()
                .comments(Lists.newArrayList(fourthComment, fifthComment)).build();

        Entry entry3 = Entry.builder().app(APP_1)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(3));
        assertThat(value.get(0), is(new RankedObject(APP_1, 3)));
        assertThat(value.get(1), is(new RankedObject(APP_2, 2)));
        assertThat(value.get(2), is(new RankedObject(APP_3, 1)));
    }

}
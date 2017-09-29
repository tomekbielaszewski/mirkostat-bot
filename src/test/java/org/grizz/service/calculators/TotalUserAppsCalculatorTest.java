package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class TotalUserAppsCalculatorTest {
    private static final String APP_1 = "app1";
    private static final String APP_2 = "app2";
    private static final String APP_3 = "app3";

    private TotalUserAppsCalculator calculator = new TotalUserAppsCalculator();

    @Test
    public void consume() throws Exception {
        EntryComment firstComment = EntryComment.builder().app(APP_1).build();
        EntryComment secondComment = EntryComment.builder().app(APP_2).build();
        EntryComment thirdComment = EntryComment.builder().build();

        Entry entry = Entry.builder().app(APP_3)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder()
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().app(APP_1)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry, entry2, entry3));
        int value = (int) calculator.getValue();

        assertThat(value, is(3));
    }

}
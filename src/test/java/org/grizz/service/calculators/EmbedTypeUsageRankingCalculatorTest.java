package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import org.grizz.service.calculators.structures.RankedObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.grizwold.microblog.model.Embed;
import pl.grizwold.microblog.model.EmbedType;
import pl.grizwold.microblog.model.Entry;
import pl.grizwold.microblog.model.EntryComment;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class EmbedTypeUsageRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";

    private EmbedTypeUsageRankingCalculator calculator = new EmbedTypeUsageRankingCalculator();

    @Test
    public void countEmbedsInEntryAndComments() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).embed(Embed.builder().type(EmbedType.IMAGE).build()).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).embed(Embed.builder().type(EmbedType.VIDEO).build()).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).embed(Embed.builder().type(EmbedType.VIDEO).build()).build();

        Entry entry = Entry.builder().author(AUTHOR_1).embed(Embed.builder().type(EmbedType.IMAGE).build())
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().author(AUTHOR_1).embed(Embed.builder().type(EmbedType.VIDEO).build())
                .comments(Lists.newArrayList()).build();

        calculator.consume(Lists.newArrayList(entry, entry2));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(EmbedType.VIDEO, 3)));
        assertThat(value.get(1), is(new RankedObject(EmbedType.IMAGE, 2)));
    }
}
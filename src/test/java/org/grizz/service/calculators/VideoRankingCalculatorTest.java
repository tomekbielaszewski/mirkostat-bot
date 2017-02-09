package org.grizz.service.calculators;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.grizz.model.Embed;
import org.grizz.model.EmbedType;
import org.grizz.model.Entry;
import org.grizz.model.EntryComment;
import org.grizz.service.calculators.structures.RankedObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class VideoRankingCalculatorTest {
    private static final String AUTHOR_1 = "auth1";
    private static final String AUTHOR_2 = "auth2";
    private static final Embed IMAGE = Embed.builder().type(EmbedType.IMAGE.name()).build();
    private static final Embed VIDEO = Embed.builder().type(EmbedType.VIDEO.name()).build();

    private VideoRankingCalculator calculator = new VideoRankingCalculator();

    @Test
    public void countsOnlyImagesInCommentsAndEntries() throws Exception {
        EntryComment firstComment = EntryComment.builder().author(AUTHOR_1).embed(IMAGE).votes(100).build();
        EntryComment secondComment = EntryComment.builder().author(AUTHOR_2).embed(VIDEO).votes(101).build();
        EntryComment thirdComment = EntryComment.builder().author(AUTHOR_1).votes(100).build();

        Entry entry = Entry.builder().author(AUTHOR_1).embed(IMAGE).votes(100)
                .comments(Lists.newArrayList(
                        thirdComment,
                        secondComment,
                        firstComment
                )).build();

        Entry entry2 = Entry.builder().author(AUTHOR_1).embed(VIDEO).votes(99)
                .comments(Lists.newArrayList()).build();

        Entry entry3 = Entry.builder().author(AUTHOR_1).votes(100)
                .comments(Lists.newArrayList()).build();

        calculator.consume(Sets.newHashSet(entry, entry2, entry3));
        List<RankedObject> value = (List<RankedObject>) calculator.getValue();

        assertThat(value, hasSize(2));
        assertThat(value.get(0), is(new RankedObject(secondComment, 101)));
        assertThat(value.get(1), is(new RankedObject(entry2, 99)));
    }

}
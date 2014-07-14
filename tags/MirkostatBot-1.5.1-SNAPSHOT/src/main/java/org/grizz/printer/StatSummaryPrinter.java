package org.grizz.printer;

import org.grizz.output.Output;
import org.grizz.statistics.collector.*;
import org.grizz.utils.StringPlural;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatSummaryPrinter implements StatPrinter {

	@Autowired
	private UserCounter userCounter;
	@Autowired
	private EntryCounter entryCounter;
	@Autowired
	private EntryLengthCollector entryLengthCollector;
	@Autowired
	private TagCounter tagCounter;
    @Autowired
    private EntryCommentCounter entryCommentCounter;
    @Autowired
    private VoteCounter voteCounter;
    @Autowired
    private UntaggedEntriesCounter untaggedEntriesCounter;
    @Autowired
    private UsersNotTaggingCounter usersNotTaggingCounter;
	
	@Override
	public void print(Output output) {
		int amountOfUsers = userCounter.getNumberOfUsers();
		int amountOfEntries = entryCounter.getNumberOfEntries();
        int amountOfComments = entryCommentCounter.getNumberOfComments();
        int amountOfVotes = voteCounter.getTotalNumberOfVotes();
		int overallCharactersTyped = entryLengthCollector.getOverallLength();
		int overallTagsUsed = tagCounter.getNumberOfTags();
        int untaggedEntriesCount = untaggedEntriesCounter.getTotalUstaggedEntries();
        int notTaggingUsersCount = usersNotTaggingCounter.getNumberOfNotTaggingUsers();
		
		String mirekPlural = StringPlural.choose(new String[]{"Mirek** napisal","Mirki** napisaly","Mirkow** napisalo"}, amountOfUsers);
		String entryPlural = StringPlural.choose(new String[]{"wpis","wpisy","wpisow"}, amountOfEntries);
		String commentsPlural = StringPlural.choose(new String[]{"komentarz","komentarze","komentarzy"}, amountOfComments);
		String characterPlural = StringPlural.choose(new String[]{"znak","znaki","znakow"}, overallCharactersTyped);
		String tagPlural = StringPlural.choose(new String[]{"unikatowy tag","unikatowe tagi","unikatowych tagow"}, overallTagsUsed);
		String votePlural = StringPlural.choose(new String[]{"plus","plusy","plusow"}, amountOfVotes);
		String notTaggingMirekPlural = StringPlural.choose(new String[]{"Mirek** napisal","Mirki** napisaly","Mirkow** napisalo"}, notTaggingUsersCount);
		String untaggedPlural = StringPlural.choose(new String[]{"nieotagowany wpis","nieotagowane wpisy","nieotagowanych wpisow"}, untaggedEntriesCount);

		String summary = 
			String.format("Przez ostatnie 24 godziny **%d %s **%d %s** i **%d %s** "
					+ "o lacznej dlugosci **%d %s**. Uzyto przy tym **%d %s** i rozdano **%d %s**.\n" +
                      "Jednakze **%d %s **%d %s**... Nieladnie... #tagujtogowno! A moze #nietagujebonocna?",
			amountOfUsers, mirekPlural, 
			amountOfEntries, entryPlural,
            amountOfComments, commentsPlural,
			overallCharactersTyped, characterPlural,
			overallTagsUsed, tagPlural,
            amountOfVotes, votePlural,
            notTaggingUsersCount, mirekPlural,
            untaggedEntriesCount, untaggedPlural);
		
		output.append(summary);
		output.append("\n\n");
	}

}

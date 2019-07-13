package org.grizz.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EntryComment {
    private Long id;

    private String author;

    @SerializedName("author_group")
    private int authorGroup;

    @SerializedName("author_sex")
    private String authorSex;

    private String app;

    @SerializedName("date")
    private Date dateAdded;

    private Embed embed;

    private String body;

    private String url;

    @SerializedName("vote_count")
    private int votes;

    private List<User> voters;

    @SerializedName("entry_id")
    private int entryId;
}

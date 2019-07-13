package org.grizz.model;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String author;
    @SerializedName("author_sex")
    private String authorSex;
    @SerializedName("author_group")
    private int authorGroup;
}

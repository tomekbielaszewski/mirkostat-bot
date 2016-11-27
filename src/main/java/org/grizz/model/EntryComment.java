package org.grizz.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.Builder;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class EntryComment extends Entry {
    @SerializedName("entry_id")
    private int entryId;

    @Override
    public void setComments(List<EntryComment> comments) {}

    @SuppressWarnings("unchecked")
    @Override
    public List<EntryComment> getComments() {
        return Collections.EMPTY_LIST;
    }
}

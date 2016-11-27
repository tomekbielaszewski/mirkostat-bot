package old.org.grizz.model;

import com.google.gson.annotations.SerializedName;

public class User {
    private String author;
    @SerializedName("author_sex")
    private String authorSex;
    @SerializedName("author_group")
    private int authorGroup;

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

    public String getAuthorSex() {
        return authorSex;
    }

    public void setAuthorSex(String authorSex) {
        this.authorSex = authorSex;
    }

    public int getAuthorGroup() {
        return authorGroup;
    }

    public void setAuthorGroup(int authorGroup) {
        this.authorGroup = authorGroup;
    }
}

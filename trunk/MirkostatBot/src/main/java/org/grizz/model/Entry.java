package org.grizz.model;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Entry implements Identifable {

    private Long id;

    private String author;

    @SerializedName("author_group")
    private String authorGroup;

    @SerializedName("date")
    private Date dateAdded;

    private String body;

    private String url;
    
    @SerializedName("vote_count")
    private int votes;
    
    private List<EntryComment> comments;
    
    private List<User> voters;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorGroup() {
		return authorGroup;
	}

	public void setAuthorGroup(String authorGroup) {
		this.authorGroup = authorGroup;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public List<EntryComment> getComments() {
		return comments;
	}

	public void setComments(List<EntryComment> comments) {
		this.comments = comments;
	}

	public List<User> getVoters() {
		return voters;
	}

	public void setVoters(List<User> voters) {
		this.voters = voters;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entry other = (Entry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", author=" + author + ", authorGroup="
				+ authorGroup + ", dateAdded=" + dateAdded + ", body=" + body
				+ ", url=" + url + ", votes=" + votes + ", comments="
				+ comments + ", voters=" + voters + "]";
	}
    
    
}

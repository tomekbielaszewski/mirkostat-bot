// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package org.grizz.model;

import java.util.Date;
import java.util.Set;
import org.grizz.model.Entry;
import org.grizz.model.Tag;

privileged aspect Entry_Roo_JavaBean {
    
    public Long Entry.getId() {
        return this.id;
    }
    
    public void Entry.setId(Long id) {
        this.id = id;
    }
    
    public String Entry.getAuthor() {
        return this.author;
    }
    
    public void Entry.setAuthor(String author) {
        this.author = author;
    }
    
    public String Entry.getAuthorGroup() {
        return this.authorGroup;
    }
    
    public void Entry.setAuthorGroup(String authorGroup) {
        this.authorGroup = authorGroup;
    }
    
    public Date Entry.getDateAdded() {
        return this.dateAdded;
    }
    
    public void Entry.setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public String Entry.getBody() {
        return this.body;
    }
    
    public void Entry.setBody(String body) {
        this.body = body;
    }
    
    public String Entry.getUrl() {
        return this.url;
    }
    
    public void Entry.setUrl(String url) {
        this.url = url;
    }
    
    public Set<Tag> Entry.getTags() {
        return this.tags;
    }
    
    public void Entry.setTags(Set<Tag> tags) {
        this.tags = tags;
    }
    
}

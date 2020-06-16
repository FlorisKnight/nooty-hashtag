package com.nooty.nootyhashtag.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hashtag {
    @Id
    private String id;
    private String hashtag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}

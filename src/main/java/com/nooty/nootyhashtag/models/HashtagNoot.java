package com.nooty.nootyhashtag.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class HashtagNoot {
    @Id
    private String id;
    private String hashtagId;
    private String nootId;
    private String userId;
    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(String hashtagId) {
        this.hashtagId = hashtagId;
    }

    public String getNootId() {
        return nootId;
    }

    public void setNootId(String nootId) {
        this.nootId = nootId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new Date(System.currentTimeMillis());
    }
}

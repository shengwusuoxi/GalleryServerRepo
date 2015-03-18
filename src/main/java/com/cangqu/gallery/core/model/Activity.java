package com.cangqu.gallery.core.model;

import com.cangqu.gallery.base.model.BaseModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name="activity")
public class Activity extends BaseModel implements Serializable {

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String description;

    @Column
    private String imageUrl;

    @ManyToMany(mappedBy="activities",fetch=FetchType.LAZY, cascade={CascadeType.MERGE})
    private Set<User> users = new HashSet<User>();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
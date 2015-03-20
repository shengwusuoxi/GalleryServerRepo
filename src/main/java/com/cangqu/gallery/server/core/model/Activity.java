package com.cangqu.gallery.server.core.model;

import com.cangqu.gallery.server.base.model.BaseModel;

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

    /**
     * 所有参加活动的用户
     */
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

    @Override
    public String toString() {
        return "Activity{" +
                "time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", users=" + users +
                '}';
    }
}
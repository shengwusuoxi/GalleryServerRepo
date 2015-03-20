package com.cangqu.gallery.server.core.vo;

import java.io.Serializable;

public class ActivityInfo implements Serializable {

    private String id;

    /**
     * 活动发起人用户名
     */
    private String createUserName;

    private String time;

    private String place;

    private String description;

    private String imageUrl;

    /**
     * 参加人数
     */
    private String participantsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

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

    public String getParticipantsCount() {
        return participantsCount;
    }

    public void setParticipantsCount(String participantsCount) {
        this.participantsCount = participantsCount;
    }

    @Override
    public String toString() {
        return "ActivityInfo{" +
                "id='" + id + '\'' +
                ", createUserName='" + createUserName + '\'' +
                ", time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", participantsCount='" + participantsCount + '\'' +
                '}';
    }
}
package com.cangqu.gallery.server.core.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TangLiuJun on 2015/3/23.
 */

@Entity(name="user")
public class User extends BaseUser{

    /**
     * 用户手机号
     */
    @Column(nullable = false, unique = true)
    private String telephone;

    /**
     * 活动
     */
    @Column
    @ManyToMany( fetch = FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "user_activity",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id",referencedColumnName="id")})
    private Set<Activity> activities = new HashSet<Activity>();

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
package com.cangqu.gallery.server.core.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by TangLiuJun on 2015/3/23.
 */

@Entity(name="institution")
public class Institution extends BaseUser {
    /**
     * 活动
     */
    @Column
    @ManyToMany( fetch = FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "institution_activity",
            joinColumns = {@JoinColumn(name = "institution_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id",referencedColumnName="id")})
    private Set<Activity> activities = new HashSet<Activity>();

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}

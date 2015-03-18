package com.cangqu.gallery.core.model;

import com.cangqu.gallery.base.model.BaseModel;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity(name="user")
public class User extends BaseModel implements Serializable {


    /**
     * 用户名
     */
    @Column(nullable = false, unique = true)
    private String userName;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;

    /**
     * 用户类型
     */
    @Column(nullable = false)
    private String userType;

    /**
     * 账号状态
     */
    @Column(nullable = false)
    private String state;

    /**
     * 登陆次数
     */
    @Column
    private String loginCount;

    /**
     * 最近登录时间
     */
    @Column
    private Timestamp lastLoginTime;

    /**
     * 登录IP
     */
    @Column
    private String lastLoginIp;

    /**
     * 活动
     */
    @Column
    @ManyToMany( fetch = FetchType.EAGER, cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "user_activity",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id",referencedColumnName="id")})
    private Set<Activity> activities = new HashSet<Activity>();

    /**
     * 更新登陆次数
     * @return
     */
    public String updateLoginCount() {
        if (StringUtils.isNotEmpty(loginCount)) {
            try {
                Long count = Long.parseLong(loginCount) + 1;
                loginCount = count.toString();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            loginCount = "1";
        }
        return loginCount;
    }




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(String loginCount) {
        this.loginCount = loginCount;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
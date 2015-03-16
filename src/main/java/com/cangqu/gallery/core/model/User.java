package com.cangqu.gallery.core.model;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(nullable = false)
    private String id;

    /**
     * 账号创建时间
     */
    @Column(nullable = false)
    private Timestamp createTime;


    /**
     * 用户名
     */
    @Column(nullable = false, unique = true)
    private String username;

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

    public String getId() {
        return id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
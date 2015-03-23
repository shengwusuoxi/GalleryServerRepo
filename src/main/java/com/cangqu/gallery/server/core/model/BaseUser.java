package com.cangqu.gallery.server.core.model;

import com.cangqu.gallery.server.base.model.BaseModel;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseUser extends BaseModel implements Serializable {

    /**
     * 用户名
     */
    @Column(nullable = true)
    private String name;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String password;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "BaseUser{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", state='" + state + '\'' +
                ", loginCount='" + loginCount + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}
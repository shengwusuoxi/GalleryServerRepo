package com.cangqu.gallery.server.base.model;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class BaseModel implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid.hex")
    @Column(nullable = false)
    private String id;

    /**
     * 创建时间
     */
    @Column(nullable = false)
    private Timestamp createTime;

    /**
     * 创建者用户ID
     */
    private String createUserId;


    /**
     * 创建者用户名
     */
    private String createUserName;


    /**
     * 更新时间
     */
    private Timestamp updateTime;

    /**
     * 更新者用户名
     */
    private String updateUser;

    /**
     * 版本
     */
    private String version;


    /**
     * 更新版本
     * @return
     */
    public String updateVersion() {
        if (StringUtils.isNotEmpty(version)) {
            try {
                Long count = Long.parseLong(version) + 1;
                version = count.toString();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            version = "1";
        }
        return version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        BaseModel baseModel = (BaseModel) o;
//
//        if (!id.equals(baseModel.id)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id!=null?id.hashCode():0;
//        result = result+(int)'f';
//        return result;
//    }

}
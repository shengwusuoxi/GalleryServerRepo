package com.cangqu.gallery.core.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/3/15.
 */
@ApiModel(value = "UserInfo", description = "用户信息")
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private String id;


    @ApiModelProperty(value = "用户名")
    private String userName;


    @ApiModelProperty(value = "密码")
    private String password;


    @ApiModelProperty(value = "用户类型")
    private String userType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

}

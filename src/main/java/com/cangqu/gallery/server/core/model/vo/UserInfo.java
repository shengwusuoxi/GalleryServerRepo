package com.cangqu.gallery.server.core.model.vo;

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


    @ApiModelProperty(value = "手机号")
    private String telephone;

    @ApiModelProperty(value = "用户名")
    private String name;


    @ApiModelProperty(value = "密码")
    private String password;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

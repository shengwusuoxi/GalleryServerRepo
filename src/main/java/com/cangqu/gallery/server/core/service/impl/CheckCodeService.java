package com.cangqu.gallery.server.core.service.impl;


import com.cangqu.gallery.server.core.service.ICheckCodeService;

/**
 * Created by TangLiuJun on 2015/3/19.
 */
public class CheckCodeService implements ICheckCodeService{

    @Override
    public String sendCode(String phone) {

        //TODO：通过第三方短息平台发送验证码
        return null;
    }

    public boolean verifyCode(String checkCode){
        return true;
    }
}

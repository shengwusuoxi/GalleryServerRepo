package com.cangqu.gallery.server.core.service;

/**
 * Created by Administrator on 2015/3/11 0011.
 * 验证码服务接口
 */
public interface ICheckCodeService {
    String sendCode(String phone);
}
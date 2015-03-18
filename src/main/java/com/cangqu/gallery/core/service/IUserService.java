package com.cangqu.gallery.core.service;

import com.cangqu.gallery.base.Exception.AuthorizationException;
import com.cangqu.gallery.base.dao.IOperations;
import com.cangqu.gallery.core.model.User;
import com.cangqu.gallery.core.vo.UserInfo;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
public interface IUserService extends IOperations<User, String> {
    String register(String userName, String password) throws AuthorizationException;

    String login(String userName, String password, String loginIp) throws AuthorizationException;

    UserInfo getUserInfo(String id) throws AuthorizationException;
}
package com.cangqu.gallery.server.core.service;

import com.cangqu.gallery.server.base.Exception.AuthorizationException;
import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.core.model.User;
import com.cangqu.gallery.server.core.model.vo.UserInfo;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
public interface IUserService extends IOperations<User, String> {
    UserInfo getUserInfo(String id) throws AuthorizationException;
}
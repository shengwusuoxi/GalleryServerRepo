package com.cangqu.gallery.server.core.service;

import com.cangqu.gallery.server.base.Exception.AuthorizationException;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
public interface IAccountService {

    String register(String telephone, String password) throws AuthorizationException;

    String login(String telephone, String password, String loginIp) throws AuthorizationException;

}
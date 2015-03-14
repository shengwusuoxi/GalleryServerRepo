package com.cangqu.gallery.core.service.impl;

import com.cangqu.gallery.base.dao.IOperations;
import com.cangqu.gallery.base.service.AbstractService;
import com.cangqu.gallery.core.dao.IUserDao;
import com.cangqu.gallery.core.model.User;
import com.cangqu.gallery.core.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Service("userService")
public class UserService extends AbstractService<User, String> implements IUserService {
    @Resource(name="userHibernateDAO")
    private IUserDao dao;

    public UserService() {
        super();
    }

    @Override
    protected IOperations<User, String> getDao() {
        return this.dao;
    }
}

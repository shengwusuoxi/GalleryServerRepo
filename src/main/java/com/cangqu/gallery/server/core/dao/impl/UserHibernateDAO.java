package com.cangqu.gallery.server.core.dao.impl;

import com.cangqu.gallery.server.base.dao.impl.AbstractHibernateDao;
import com.cangqu.gallery.server.core.dao.IUserDao;
import com.cangqu.gallery.server.core.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Repository("userHibernateDAO")
public class UserHibernateDAO  extends AbstractHibernateDao<User, String> implements IUserDao {
    public UserHibernateDAO() {
        super();
        setClazz(User.class);
    }
}

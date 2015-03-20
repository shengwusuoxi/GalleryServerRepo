package com.cangqu.gallery.server.core.dao.impl;

import com.cangqu.gallery.server.base.dao.impl.AbstractHibernateDao;
import com.cangqu.gallery.server.core.dao.IActivityDao;
import com.cangqu.gallery.server.core.model.Activity;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Repository("activityHibernateDAO")
public class ActivityHibernateDAO extends AbstractHibernateDao<Activity, String> implements IActivityDao {
    public ActivityHibernateDAO() {
        super();
        setClazz(Activity.class);
    }
}

package com.cangqu.gallery.core.dao.impl;

import com.cangqu.gallery.base.dao.impl.AbstractHibernateDao;
import com.cangqu.gallery.core.dao.IActivityDao;
import com.cangqu.gallery.core.model.Activity;
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

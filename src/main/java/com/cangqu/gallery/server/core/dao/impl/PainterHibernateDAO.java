package com.cangqu.gallery.server.core.dao.impl;

import com.cangqu.gallery.server.base.dao.impl.AbstractHibernateDao;
import com.cangqu.gallery.server.core.dao.IPainterDao;
import com.cangqu.gallery.server.core.model.Painter;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Repository("painterHibernateDAO")
public class PainterHibernateDAO extends AbstractHibernateDao<Painter, String> implements IPainterDao {
    public PainterHibernateDAO() {
        super();
        setClazz(Painter.class);
    }
}

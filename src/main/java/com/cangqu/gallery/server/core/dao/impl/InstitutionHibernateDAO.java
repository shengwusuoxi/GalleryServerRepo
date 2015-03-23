package com.cangqu.gallery.server.core.dao.impl;

import com.cangqu.gallery.server.base.dao.impl.AbstractHibernateDao;
import com.cangqu.gallery.server.core.dao.IInstitutionDao;
import com.cangqu.gallery.server.core.model.Institution;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Repository("institutionHibernateDAO")
public class InstitutionHibernateDAO extends AbstractHibernateDao<Institution, String> implements IInstitutionDao {
    public InstitutionHibernateDAO() {
        super();
        setClazz(Institution.class);
    }
}

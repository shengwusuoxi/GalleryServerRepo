package com.cangqu.gallery.server.core.service.impl;

import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.base.service.AbstractService;
import com.cangqu.gallery.server.core.dao.IInstitutionDao;
import com.cangqu.gallery.server.core.model.Institution;
import com.cangqu.gallery.server.core.service.IInstitutionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Service("institutionService")
public class InstitutionService extends AbstractService<Institution, String> implements IInstitutionService {
    private static final Log LOGGER = LogFactory.getLog(InstitutionService.class);

    @Resource(name="institutionHibernateDAO")
    private IInstitutionDao institutionDao;

    public InstitutionService() {
        super();
    }

    @Override
    protected IOperations<Institution, String> getDao() {
        return this.institutionDao;
    }

}

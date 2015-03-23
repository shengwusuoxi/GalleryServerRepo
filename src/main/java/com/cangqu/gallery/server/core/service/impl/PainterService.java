package com.cangqu.gallery.server.core.service.impl;

import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.base.service.AbstractService;
import com.cangqu.gallery.server.core.dao.IPainterDao;
import com.cangqu.gallery.server.core.model.Painter;
import com.cangqu.gallery.server.core.service.IPainterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Service("painterService")
public class PainterService extends AbstractService<Painter, String> implements IPainterService {
    private static final Log LOGGER = LogFactory.getLog(PainterService.class);

    @Resource(name="painterHibernateDAO")
    private IPainterDao painterDao;

    public PainterService() {
        super();
    }

    @Override
    protected IOperations<Painter, String> getDao() {
        return this.painterDao;
    }

}

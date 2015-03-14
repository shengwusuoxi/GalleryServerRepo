package com.cangqu.gallery.base.dao.impl;

import com.cangqu.gallery.base.dao.IOperations;
import com.cangqu.gallery.base.dao.impl.support.Condition;
import com.cangqu.gallery.base.dao.impl.support.Page;
import com.cangqu.gallery.base.utils.ReflectionUtils;
import com.google.common.base.Preconditions;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHibernateDao<T extends Serializable, PK extends Serializable> implements IOperations<T, PK> {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Class<T> clazz;
    protected final Log logger = LogFactory.getLog(getClass());

    protected final void setClazz(final Class<T> clazzToSet) {
        this.clazz = Preconditions.checkNotNull(clazzToSet);
    }

    protected final Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public final T findOne(final String id) {
        return (T)getCurrentSession().get(clazz, id);
    }

    @Override
    public final List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName()).list();
    }

    /**
     * 按条件查找
     * @param condition
     * @param page
     * @return
     */
    public List<T> findByCondition(Condition condition, Page page){
        if (condition == null || condition.toDetachedCriteria() == null) {
            return null;
        }
        Session session = getCurrentSession();
        Criteria criteria = condition.toDetachedCriteria().getExecutableCriteria(session);
        if (page != null) {
            page.setTotalCount(countCriteriaResult(criteria));
            criteria.setFirstResult(page.getFirst() - 1);
            criteria.setMaxResults(page.getPageSize());
        }
        return criteria.list();
    }

    /**
     * 执行count查询获得本次Criteria查询所能获得的对象总数.
     */
    protected long countCriteriaResult(final Criteria c) {
        CriteriaImpl impl = (CriteriaImpl) c;

        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();

        List<CriteriaImpl.OrderEntry> orderEntries = null;
        try {
            orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
            ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
        } catch (Exception e) {
            logger.error("不可能抛出的异常:{}", e);
        }

        // 执行Count查询
        long totalCount = (Long) c.setProjection(Projections.rowCount()).uniqueResult();

        // 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
        c.setProjection(projection);

        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null) {
            c.setResultTransformer(transformer);
        }
        try {
            ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
        } catch (Exception e) {
            logger.error("不可能抛出的异常:{}", e);
        }

        return totalCount;
    }

    @Override
    public final void create(final T entity) {
        Preconditions.checkNotNull(entity);
        // getCurrentSession().persist(entity);
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public final T update(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().update(entity);
        return entity;
        //return (T)getCurrentSession().merge(entity);
    }

    @Override
    public final void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    @Override
    public final void deleteById(final String entityId) {
        final T entity = findOne(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }
}
package com.cangqu.gallery.server.base.service;

import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.base.dao.impl.support.Condition;
import com.cangqu.gallery.server.base.dao.impl.support.Page;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractService<T extends Serializable, PK extends Serializable> implements IOperations<T, PK> {

    protected abstract IOperations<T, PK> getDao();

    @Override
    public T getById(final String id) {
        return getDao().getById(id);
    }

    @Override
    public List<T> findAll() {
        return getDao().findAll();
    }

    @Override
    public List<T> findByCondition(Condition condition, Page page){
        return getDao().findByCondition(condition, page);
    }

    @Override
    public PK create(final T entity) {
        return getDao().create(entity);
    }

    @Override
    public T update(final T entity) {
        return getDao().update(entity);
    }

    @Override
    public void delete(final T entity) {
        getDao().delete(entity);
    }

    @Override
    public void deleteById(final String entityId) {
        getDao().deleteById(entityId);
    }
}
package com.cangqu.gallery.core.dao;

import com.cangqu.gallery.base.dao.IOperations;
        import com.cangqu.gallery.core.model.User;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
public interface IUserDao extends IOperations<User,String> {
    //让所有的DAO都实现基本的操作接口IOperations
    //除了实现IOperations中的基本操作之外，特定的DAO要实现其他操作可以在对应的接口DAO中定义方法，
    //此处UserDao的接口IUserDao不需要实现其他方法
}

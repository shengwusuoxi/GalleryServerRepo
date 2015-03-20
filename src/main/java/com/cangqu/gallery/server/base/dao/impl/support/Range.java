package com.cangqu.gallery.server.base.dao.impl.support;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 2010-7-1
 * Time: 2:40:15
 */
public interface Range {

    Object getFromValue();

    Object getToValue();

    GType getFromType();

    LType getToType();

    enum GType {
        ge, gt
    }

    enum LType {
        le, lt
    }
}

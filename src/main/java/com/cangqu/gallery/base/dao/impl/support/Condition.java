package com.cangqu.gallery.base.dao.impl.support;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 条件查询接口
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 2010-7-1
 * Time: 2:24:43
 */
public interface Condition {
    /**
     * 添加排序条件
     * 排序条件规则   dictName:desc,dictGroup:asc
     *
     * @param orders
     * @return
     */
    QuickCondition addOrder(String orders);

    enum Bracket {
        LEFT, RIGHT, ALL, NONE
    }

    DetachedCriteria toDetachedCriteria();

    QuickCondition addEqual(String fieldName, Object o);

    QuickCondition addLike(String fieldName, Object o);

    QuickCondition addRange(String fieldName, Range range);

    QuickCondition addLike(String fieldName, Object o, Bracket bracket);

    /**
     * 添加IN查询条件
     *
     * @param fieldName 需要IN查询的字段名
     * @param in        IN查询对象
     * @return
     */
    QuickCondition addIn(String fieldName, In in);
}

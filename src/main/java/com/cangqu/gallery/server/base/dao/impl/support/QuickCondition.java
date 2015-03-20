package com.cangqu.gallery.server.base.dao.impl.support;

import com.cangqu.gallery.server.base.utils.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.<BR/>
 * 构造一个DetachedCriteria查询条件的条件组<BR/>
 * 其中fieldName使用格式为：<BR/>
 * 如果A对象包含一个B对象b，B对象包含一个C对象c，现在条件是c的name为“abc”，<BR/>
 * 那么fieldName可以写作"b.c.name",条件写作condition.addEqual("b.c.name","abc")<BR/>
 * 如果条件是b的age为19，那么可以写作"b.age",条件写作condition.addEqual("b.age",19)<BR/>
 * 如果条件是A自身的state为1，那么可以写作"state",条件写作condition.addEqual("state","1")<BR/>
 * 综上所述，fieldName应该是对象的属性路径，并且是大小写敏感的<BR/>
 * User: root<BR/>
 * Date: 2010-6-30<BR/>
 * Time: 21:32:22<BR/>
 */
public class QuickCondition implements Condition, Serializable {
    protected Logger log = LoggerFactory.getLogger(getClass());
    protected DetachedCriteria dc;
    protected Map<String, String> joinAliasMap;
    protected int aliasCount;

    protected static String ASC = "ASC";
    protected static String DESC = "DESC";

    public QuickCondition(Class entityClass) {
        this.dc = DetachedCriteria.forClass(entityClass);
        this.joinAliasMap = new HashMap<String, String>();
        this.aliasCount = 1;
    }

    /**
     * 返回由该组查询条件构造的DetachedCriteria
     *
     * @return
     */
    @Override
    public DetachedCriteria toDetachedCriteria() {
        return this.dc;
    }

    /**
     * 添加一个等值查询条件
     *
     * @param fieldName 字段名
     * @param o         等值匹配值
     * @return
     */
    @Override
    public QuickCondition addEqual(String fieldName, Object o) {
        if (!StringUtil.isEmpty(o)) {
            this.dc.add(Restrictions.eq(complexFieldName(fieldName), o));
        } else {
            log.info("查询条件不能为空，" + fieldName + "条件将被忽略");
        }
        return this;
    }

    /**
     * 添加一个like查询条件
     *
     * @param fieldName 字段名
     * @param o         like匹配值
     * @return
     */
    @Override
    public QuickCondition addLike(String fieldName, Object o) {
        return addLike(fieldName, o, Bracket.ALL);
    }

    /**
     * 添加一个like查询条件,允许自定义使用%符号，若使用了%，则按照%规定进行查询，忽略BRACKET设置，若没有%，则按照BRACKET设置进行查询
     *
     * @param fieldName 字段名
     * @param o         like匹配值
     * @param bracket   边界条件
     * @return
     */
    @Override
    public QuickCondition addLike(String fieldName, Object o, Bracket bracket) {
        if (!StringUtil.isEmpty(o)) {
            if (o instanceof String) {
                if (((String) o).indexOf("%") < 0) {
                    String s = (String) o;
                    switch (bracket) {
                        case ALL:
                            this.dc.add(Restrictions.like(complexFieldName(fieldName), s, MatchMode.ANYWHERE));
                            break;
                        case LEFT:
                            this.dc.add(Restrictions.like(complexFieldName(fieldName), s, MatchMode.END));
                            break;
                        case RIGHT:
                            this.dc.add(Restrictions.like(complexFieldName(fieldName), s, MatchMode.START));
                            break;
                        default:

                    }
                } else {
                    this.dc.add(Restrictions.like(complexFieldName(fieldName), o));
                }
            } else {
                log.info("LIKE查询条件应是String类型，" + fieldName + "条件将被忽略");
            }
        } else {
            log.info("查询条件不能为空，" + fieldName + "条件将被忽略");
        }
        return this;
    }

    /**
     * 添加一个区间查询条件
     *
     * @param fieldName
     * @param range
     * @return
     */
    @Override
    public QuickCondition addRange(String fieldName, Range range) {
        if (!StringUtil.isEmpty(fieldName) && !StringUtil.isEmpty(range)) {
            String complexFieldName = complexFieldName(fieldName);
            if (!StringUtil.isEmpty(range.getFromValue())) {
                switch (range.getFromType()) {
                    case ge:
                        this.dc.add(Restrictions.ge(complexFieldName, range.getFromValue()));
                        break;
                    case gt:
                        this.dc.add(Restrictions.gt(complexFieldName, range.getFromValue()));
                        break;
                }
            }
            if (!StringUtil.isEmpty(range.getToValue())) {
                switch (range.getToType()) {
                    case le:
                        this.dc.add(Restrictions.le(complexFieldName, range.getToValue()));
                        break;
                    case lt:
                        this.dc.add(Restrictions.lt(complexFieldName, range.getToValue()));
                        break;
                }
            }
        }
        return this;
    }

    /**
     * 添加排序条件
     * 排序条件规则   dictName:desc,dictGroup:asc
     *
     * @param orders
     * @return
     */
    @Override
    public QuickCondition addOrder(String orders) {
        if (!StringUtil.isEmpty(orders)) {
            String[] orderArr = orders.split(",");
            for (String orderSector : orderArr) {
                String[] orderStl = orderSector.split(":");
                if (ASC.equalsIgnoreCase(orderStl[1])) {
                    this.dc.addOrder(Order.asc(complexFieldName(orderStl[0])));
                } else {
                    this.dc.addOrder(Order.desc(complexFieldName(orderStl[0])));
                }
            }
        } else {
            log.info("排序字段为空，排序将被忽略");
        }
        return this;
    }

    /**
     * 添加IN查询条件
     *
     * @param fieldName 需要IN查询的字段名
     * @param in        IN查询对象
     * @return
     */
    @Override
    public QuickCondition addIn(String fieldName, In in) {
        if (!StringUtil.isEmpty(fieldName) && !StringUtil.isEmpty(in)) {
            if (in.getValues() == null) {
                log.error("查询条件为空，" + fieldName + "的IN查询将被忽略");
                return this;
            } else {
                if (in.getIn() == In.IN.in) {
                    this.dc.add(Restrictions.in(complexFieldName(fieldName), in.getValues()));
                } else {
                    this.dc.add(Restrictions.not(Restrictions.in(complexFieldName(fieldName), in.getValues())));
                }
            }
        }
        return this;
    }

    protected String complexFieldName(String fieldName) {
        if (isSimpleField(fieldName)) {
            return fieldName;
        } else {
            String[] fieldNamePath = fieldName.split("\\.");
            String lastAlias = null;
            for (int i = 0; i < fieldNamePath.length - 1; i++) {
                String aliasPath = createAliasPath(fieldNamePath, i + 1);
                String alias = this.joinAliasMap.get(aliasPath);
                if (alias == null) {
                    alias = "t" + this.aliasCount++;
                    this.dc.createAlias(aliasPath, alias);
                    this.joinAliasMap.put(aliasPath, alias);
                }
                lastAlias = alias;
            }
            return lastAlias + "." + fieldNamePath[fieldNamePath.length - 1];
        }
    }

    protected boolean isSimpleField(String fieldName) {
        return fieldName.indexOf(".") == -1;
    }

    protected String createAliasPath(String[] fieldNamePath, int i) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            builder.append(fieldNamePath[j]);
            if (j != i - 1) {
                builder.append(".");
            }
        }
        return builder.toString();
    }
}

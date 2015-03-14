package com.cangqu.gallery.base.dao.impl.support;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * hibernate creteria的封装，用于组合查询条件，查询条件之前的关系为并集 当传递的参数为空， 查询条件会被忽略
 * @author fastech.wangxiaobo 2010-8-5
 */
public class SearchCondition implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger log = LoggerFactory.getLogger(getClass());

	private DetachedCriteria criteria;

	public SearchCondition(Class entityClass) {
		this.criteria = DetachedCriteria.forClass(entityClass);
	}

	public DetachedCriteria getCriteria() {
		return this.criteria;
	}

	@SuppressWarnings("unchecked")
	public static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if (o instanceof String && o.toString().matches("\\s*")) {
			return true;
		}
		if (o instanceof List && ((List) o).size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 模糊查询,如果value 只含有null类型字符串，将忽略此条件
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 */
	public void like(String property, String value) {
		if (isEmpty(property) || isEmpty(value) || value.matches("\\s*%*\\s*null\\s*%*\\s*")) {
			return;
		}
		criteria.add(Restrictions.like(property, "%"+ value.trim() + "%"));
	}

	/**
	 * not like 模糊查询
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 */
	public void notLike(String property, String value) {
		if (isEmpty(property) || isEmpty(value) || value.contains("null")) {
			return;
		}
		criteria.add(Restrictions.not(Restrictions.like(property, value + "%")));
	}

	/**
	 * in 查询
	 * @param property 属性
	 * @param collection 集合，该项为空时忽略此条件
	 */
	public void in(String property, Collection collection) {
		if (isEmpty(property) || isEmpty(collection)) {
			return;
		}
		criteria.add(Restrictions.in(property, collection));
	}

	/**
	 * in 查询
	 * @param property 属性
	 * @param collection 集合 ，该项为空时忽略此条件
	 */
	public void in(String property, Object[] collection) {
		if (isEmpty(property) || isEmpty(collection)) {
			return;
		}
		criteria.add(Restrictions.in(property, collection));
	}

	/**
	 * not in 查询
	 * @param property 属性
	 * @param collection 集合，该项为空时忽略此条件
	 */
	public void notIn(String property, Collection collection) {
		if (isEmpty(property) || isEmpty(collection)) {
			return;
		}
		criteria.add(Restrictions.not(Restrictions.in(property, collection)));
	}

	/**
	 * not in 查询
	 * @param property 属性
	 * @param collection 集合，该项为空时忽略此条件
	 */
	public void notIn(String property, Object[] collection) {
		if (isEmpty(property) || isEmpty(collection)) {
			return;
		}
		criteria.add(Restrictions.not(Restrictions.in(property, collection)));
	}

	/**
	 * 等于
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 * @param ignoreNull  如果value为空时，是否忽略该查询条件，默认为true
	 */
	public void eq(String property, Object value, Boolean... ignoreNull) {
		if (ignoreNull == null || ignoreNull.length == 0 || ignoreNull[0]) {
			if (isEmpty(property) || isEmpty(value)) {
				return;
			}
		}
		criteria.add(Restrictions.eq(property, value));
	}

	/**
	 * 大于等于
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 */
	public void ge(String property, Object value) {
		if (isEmpty(property) || isEmpty(value)) {
			return;
		}
		criteria.add(Restrictions.ge(property, value));
	}

	/**
	 * 大于
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 */
	public void gt(String property, Object value) {
		if (isEmpty(property) || isEmpty(value)) {
			return;
		}
		criteria.add(Restrictions.gt(property, value));
	}

	/**
	 * 小于等于
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 */
	public void le(String property, Object value) {
		if (isEmpty(property) || isEmpty(value)) {
			return;
		}
		//如果是小于等于某个时间，则将该时间补全为 xxxx-xx-xx 23:59:59
		if (value instanceof Date) {
			Date d = (Date) value;
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);

			c.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND) - 1);

			d = c.getTime();
		}
		criteria.add(Restrictions.le(property, value));
	}

	/**
	 * 小于
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 */
	public void lt(String property, Object value) {
		if (isEmpty(property) || isEmpty(value)) {
			return;
		}
		criteria.add(Restrictions.lt(property, value));
	}

	/**
	 * 不等于
	 * @param property 属性
	 * @param value 值，该项为空时忽略此条件
	 */
	public void ne(String property, Object value) {
		if (isEmpty(property) || isEmpty(value)) {
			return;
		}
		criteria.add(Restrictions.ne(property, value));
	}

	/**
	 * 添加between 查询 构造条件：hi>= proprty >= lo
	 * @param property 属性
	 * @param lo 小值
	 * @param hi 大值
	 */
	public void between(String property, Object lo, Object hi) {
		if (isEmpty(property)) {
			return;
		}
		if (!isEmpty(lo)) {
			criteria.add(Restrictions.ge(property, lo));
		}
		if (!isEmpty(hi)) {
			criteria.add(Restrictions.le(property, hi));
		}
	}

	/**
	 * 添加排序
	 * @param property 排序依赖的属性
	 * @param order 排序类型 asc desc ，该项为空、或字符串不正确时，忽略此条件
	 */
	public void addOrder(String property, String order) {
		if (isEmpty(property) || isEmpty(order)) {
			return;
		}
		if (order.toUpperCase().equals("ASC")) {
			criteria.addOrder(Property.forName(property).asc());
		} else if (order.toUpperCase().equals("DESC")) {
			criteria.addOrder(Property.forName(property).desc());
		} else {
			log.info("排序方式无效");
		}
	}

	/**
	 * 或查询,例： <code>
	 * SearchCondition sc = new SearchCondition(Person.class);
	 * sc.or( Restrictions.like("name", "Fritz%"),Restrictions.like("name", "Fritz%"));
	 * </code>
	 * @param lhs 条件1
	 * @param rhs 条件2
	 */
	public void or(Criterion lhs, Criterion rhs) {
		criteria.add(Restrictions.or(lhs, rhs));
	}

	/**
	 * 某属性值为null
	 * @param property
	 */
	public void isNull(String property) {
		if (isEmpty(property)) {
			return;
		}
		criteria.add(Restrictions.isNull(property));
	}

	/**
	 * 某属性值不为null
	 * @param property
	 */
	public void isNotNull(String property) {
		if (isEmpty(property)) {
			return;
		}
		criteria.add(Restrictions.isNotNull(property));
	}

}

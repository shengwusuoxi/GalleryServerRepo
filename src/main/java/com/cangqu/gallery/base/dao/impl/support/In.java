package com.cangqu.gallery.base.dao.impl.support;

import com.cangqu.gallery.base.utils.StringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * IN 查询对象
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 2010-7-13
 * Time: 11:40:56
 * To change this template use File | Settings | File Templates.
 */
public class In {
    private IN in;
    private List values;
    public static IN IS_IN = IN.in;
    public static IN NOT_IN = IN.notIn;

    /**
     * 判断IN查询还是NOTIN查询
     */
    public enum IN{
        in,notIn
    }

    public In(IN in,Object[] values) {
        this.in=in;
        this.values= Arrays.asList(values);
    }

    public In(IN in, List valueList) {
        this.in=in;
        this.values = valueList;
    }

    public In(IN in, String valueStr) {
        this.in=in;
        if(!StringUtil.isEmpty(valueStr)){
            String[] _values = valueStr.split(",");
            this.values = Arrays.asList(_values);
        }
    }

    public IN getIn() {
        return in;
    }

    public void setIn(IN in) {
        this.in = in;
    }

    public List getValues() {
        return values;
    }

    public void setValues(List values) {
        this.values = values;
    }
}

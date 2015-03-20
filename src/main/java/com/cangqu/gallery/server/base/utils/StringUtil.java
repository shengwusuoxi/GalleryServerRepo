package com.cangqu.gallery.server.base.utils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: root
 * Date: 2010-3-25
 * Time: 15:33:12
 */
public class StringUtil {
    /**
     * 判断一个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
    	if (o == null) {
            return true;
        }else if (o instanceof String) {
            String s = (String) o;
            if("".equalsIgnoreCase(s.trim()) || "null".equalsIgnoreCase(s))
                return true;
            else
            	return false;
        }else {
            return false;
        }
    }

    /**
     * 判断一个对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean parseBool(Object o) {
        if (o == null) return false;
        String s = (String) o;
        if ("TRUE".equals(s.toUpperCase())) return true;
        else if ("FALSE".equals(s.toUpperCase())) return false;
        else return false;
    }

    /**
     * 获取一个UUID值
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 将逗号分隔的字符串转换为一个Set
     *
     * @param str
     * @return
     */
    public static Set<String> makeString2Set(String str) {
        Set<String> set = new LinkedHashSet<String>();
        if (!isEmpty(str)) {
            String[] strs = str.split(",");
            set.addAll(Arrays.asList(strs));
        }
        return set;
    }
    
    /**
     * 将数组字符串转换为一个逗号相隔的、带单引号的字符串
     *
     * @param ids
     * @return
     */
    public static String getStrByArr(String[] ids){
    	String str=null;
    	if(ids != null && ids.length > 0){
    		for(String id:ids){
    			if(str == null)
    				str = "'"+id+"'";
    			else
    				str += ",'"+id+"'";
    		}
    	}
    	return str;
    }
}

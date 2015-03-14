package com.cangqu.gallery.base.utils;

import org.apache.commons.collections.FastHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 本地线程控制类
 *
 * @author qiukx
 */
public class ThreadLocalUtil {
    private static Logger logger = LoggerFactory.getLogger(ThreadLocalUtil.class);
    public static final String SESSION = "SESSION";//session
    private static ThreadLocal local = new ThreadLocal() {
        protected synchronized Object initialValue() {
            return new FastHashMap();
        }
    };

    public ThreadLocalUtil() {
    }

    public static Object put(Object key, Object value) {
        return getThreadLocalMap().put(key, value);
    }

    public static Object get(Object key) {
        Object val = getThreadLocalMap().get(key);
        if (val == null) {
            Object sessionVal = getThreadLocalMap().get(SESSION);
            if (sessionVal != null && key instanceof String) {
                HttpSession session = (HttpSession) sessionVal;
                val = session.getAttribute((String) key);
            } else {
            }
        }
        return val;
    }

    public static Object remove(Object key) {
        return getThreadLocalMap().remove(key);
    }

    public static Map getThreadLocalMap() {
        return (Map) local.get();
    }

    public static boolean containsKey(Object key) {
        return getThreadLocalMap().containsKey(key);
    }

    public static boolean containsValue(Object value) {
        return getThreadLocalMap().containsValue(value);
    }

    public static void clear() {
        getThreadLocalMap().clear();
    }

    static {

    }
}
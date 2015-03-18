package com.cangqu.gallery.base.Exception;

/**
 * Created by TangLiuJun on 2015/3/18.
 */
public class AuthorizationException extends BaseException {

    public AuthorizationException(){
        super();
    }

    public AuthorizationException(int code, String message){
        super(code, message);
    }

    public AuthorizationException(Throwable t){
        super(t);
    }
}

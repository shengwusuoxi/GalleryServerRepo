package com.cangqu.gallery.server.base.Exception;

/**
 * Created by TangLiuJun on 2015/3/18.
 */
public class BaseException extends Exception {

    private int code;

    public BaseException(){
        super();
    }

    public BaseException(int code, String message){
        super(message);
        this.code = code;
    }

    public BaseException(Throwable t){
        super(t.getMessage());
        this.setStackTrace(t.getStackTrace());
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "code=" + code +
                ", message='" + getMessage() + '\'' +
                ", stack='" + getStackTrace()[0] + '\'' +
                '}';
    }
}

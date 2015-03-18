package com.cangqu.gallery.base.controller;

import com.cangqu.gallery.base.Exception.BaseException;
import com.cangqu.gallery.base.vo.BaseResultVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
public class BaseController {
    private static final Log LOGGER = LogFactory.getLog(BaseController.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    protected BaseResultVo buildSuccessResultInfo(Object resultData)
    {
        BaseResultVo resultVo = new BaseResultVo();
        resultVo.setResultCode(0);
        resultVo.setResultMessage("success");
        resultVo.setResultData(resultData);
        return resultVo;
    }

    /**
     *  构造失败返回结果
     * @param resultCode
     *            :任意非0数字，代表失败
     * @param failedMsg
     *            ：失败信息
     * @return
     */
    protected BaseResultVo buildFailedResultInfo(int resultCode, String failedMsg)
    {
        return new BaseResultVo(resultCode, failedMsg);
    }

    /**
     * 构造失败返回结果
     * @param e
     *         :自定义异常
     * @return
     *         失败信息
     */
    protected BaseResultVo buildFailedResultInfo(BaseException e)
    {
        return new BaseResultVo(e.getCode(), e.getMessage());
    }


    /**
     * @description: 获取客户端IP地址
     * @return
     */
    protected  String getClientIp() {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}

package com.cangqu.gallery.server.core.controller;

import com.cangqu.gallery.server.base.Exception.BaseException;
import com.cangqu.gallery.server.base.controller.BaseController;
import com.cangqu.gallery.server.base.vo.BaseResultVo;
import com.cangqu.gallery.server.core.constant.RequestConstant;
import com.cangqu.gallery.server.core.service.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Api(basePath = "v1/account", value = "", description = "账号")
@Controller
@RequestMapping(value = "v1/account", produces = RequestConstant.CONTROLLER_PRODUCES)
public class AccountController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(AccountController.class);

    @Resource(name="userService")
    IUserService userService;

    @ApiOperation(value = "注册账号", httpMethod = "POST", response = BaseResultVo.class, notes = "注册用户账号")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody BaseResultVo register(@RequestParam @ApiParam(value = "用户名") String userName,
                                               @RequestParam @ApiParam(value = "密码") String password,
                                               @RequestParam @ApiParam(value = "验证码") String checkCode ) {
        LOGGER.debug("userName:" + userName + "pwd:" + password + "checkCode" + checkCode);
        try {
            userService.register(userName,password);
            userService.login(userName,password,getClientIp());
        } catch (BaseException e) {
            return buildFailedResultInfo(e);
        }
        return buildSuccessResultInfo("账号注册成功！");
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST", response = BaseResultVo.class, notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody BaseResultVo login(@RequestParam @ApiParam(value = "用户名") String userName,
                                            @RequestParam @ApiParam(value = "密码") String password,
                                            @RequestParam @ApiParam(value = "验证码") String checkCode) {
        LOGGER.debug("userName:" + userName + "pwd:" + password + "checkCode" + checkCode);
        try {
            userService.login(userName,password,getClientIp());
            session.setAttribute("loginState","in");
        } catch (BaseException e) {
            return buildFailedResultInfo(e);
        }
        return buildSuccessResultInfo("登录成功！");
    }

}
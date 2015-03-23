package com.cangqu.gallery.server.core.web.controller;

import com.cangqu.gallery.server.base.Exception.BaseException;
import com.cangqu.gallery.server.base.model.vo.BaseResultVo;
import com.cangqu.gallery.server.base.web.controller.BaseController;
import com.cangqu.gallery.server.core.constant.RequestConstant;
import com.cangqu.gallery.server.core.service.IAccountService;
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
@Api(basePath = "account", value = "", description = "账号")
@Controller
@RequestMapping(value = "account", produces = RequestConstant.CONTROLLER_PRODUCES)
public class AccountController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(AccountController.class);

    @Resource(name="accountService")
    IAccountService accountService;

    @ApiOperation(value = "注册账号", httpMethod = "POST", response = BaseResultVo.class, notes = "注册用户账号")
         @RequestMapping(value = "/register", method = RequestMethod.POST)
         @ResponseStatus(HttpStatus.CREATED)
         public @ResponseBody BaseResultVo register(@RequestParam @ApiParam(value = "电话") String telephone,
                                                    @RequestParam @ApiParam(value = "密码") String password,
                                                    @RequestParam @ApiParam(value = "验证码") String checkCode ) {
        LOGGER.debug("telephone:" + telephone + "pwd:" + password + "checkCode" + checkCode);
        try {
            accountService.register(telephone,password);
            accountService.login(telephone,password,getClientIp());
        } catch (BaseException e) {
            return buildFailedResultInfo(e);
        }
        return buildSuccessResultInfo("账号注册成功！");
    }

    @ApiOperation(value = "用户登录", httpMethod = "POST", response = BaseResultVo.class, notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody BaseResultVo login(@RequestParam @ApiParam(value = "电话") String telephone,
                                            @RequestParam @ApiParam(value = "密码") String password,
                                            @RequestParam @ApiParam(value = "验证码") String checkCode) {
        LOGGER.debug("telephone:" + telephone + "pwd:" + password + "checkCode" + checkCode);
        try {
            accountService.login(telephone, password, getClientIp());
        } catch (BaseException e) {
            return buildFailedResultInfo(e);
        }
        return buildSuccessResultInfo("登录成功！");
    }

}
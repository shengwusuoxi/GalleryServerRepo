package com.cangqu.gallery.server.core.web.controller;

import com.cangqu.gallery.server.base.Exception.BaseException;
import com.cangqu.gallery.server.base.model.vo.BaseResultVo;
import com.cangqu.gallery.server.base.web.controller.BaseController;
import com.cangqu.gallery.server.core.model.vo.UserInfo;
import com.cangqu.gallery.server.core.service.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Api(basePath = "user", value = "", description = "用户")
@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(UserController.class);

    @Resource(name = "userService")
    IUserService userService;


    @ApiOperation(value = "获取用户信息", httpMethod = "GET", response = BaseResultVo.class, notes = "获取用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo getUserInfo(@PathVariable("id") String id) {
        try {
            UserInfo userInfo = userService.getUserInfo(id);
            return buildSuccessResultInfo(userInfo);
        } catch (BaseException e) {
           return buildFailedResultInfo(e);
        }
    }

}
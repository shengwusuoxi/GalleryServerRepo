package com.cangqu.gallery.core.controller;

import com.cangqu.gallery.base.controller.BaseController;
import com.cangqu.gallery.base.utils.EncodeUtils;
import com.cangqu.gallery.base.vo.BaseResultVo;
import com.cangqu.gallery.core.model.User;
import com.cangqu.gallery.core.service.IUserService;
import com.cangqu.gallery.core.vo.UserInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Api(basePath = "v1/users", value = "", description = "用户管理")
@Controller
@RequestMapping(value = "v1/users")
public class UserController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(UserController.class);

    @Resource(name = "userService")
    IUserService userService;


    @ApiOperation(value = "获取用户信息", httpMethod = "GET", response = BaseResultVo.class, notes = "获取用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo getUserInfo(@PathVariable("id") String id) {
        User user = userService.findOne(id);
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo, "password");//忽略密码
            return buildSuccessResultInfo(userInfo);
        } else {
            return buildFailedResultInfo(-1, "用户不存在！");
        }
    }

    @ApiOperation(value = "更新用户信息", httpMethod = "PUT", response = BaseResultVo.class, notes = "更新用户信息",consumes = "application/json")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo updateUserInfo(@PathVariable("id") String id, UserInfo userInfo) {
        User user = userService.findOne(id);
        if (user != null) {
            user.setUsername(userInfo.getUsername());
            user.setPassword(EncodeUtils.sha(EncodeUtils.md5(userInfo.getPassword())));
            userService.update(user);
            return buildSuccessResultInfo("用户信息更新成功！");
        } else {
            return buildFailedResultInfo(-1, "用户不存在！");
        }
    }

}
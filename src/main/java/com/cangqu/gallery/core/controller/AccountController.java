package com.cangqu.gallery.core.controller;

import com.cangqu.gallery.base.controller.BaseController;
import com.cangqu.gallery.base.dao.impl.support.Condition;
import com.cangqu.gallery.base.dao.impl.support.QuickCondition;
import com.cangqu.gallery.base.utils.EncodeUtils;
import com.cangqu.gallery.base.vo.BaseResultVo;
import com.cangqu.gallery.core.model.User;
import com.cangqu.gallery.core.service.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Api(basePath = "/account", value = "AccountController", description = "账号管理")
@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(AccountController.class);

    @Resource(name="userService")
    IUserService userService;


    @ApiOperation(value = "注册用户", httpMethod = "POST", response = BaseResultVo.class, notes = "用于注册用户账号")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody BaseResultVo register(@RequestParam(value = "username") String userName, @RequestParam String password) {
        LOGGER.debug("username:" + userName + "pwd:" + password);
        Condition condition = new QuickCondition(User.class);
        condition.addEqual("username", userName);
        List<User> userList = userService.findByCondition(condition, null);
        if (userList.size() == 0) {
            User user = new User();
            user.setUsername(userName);
            user.setState("0");
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.setUserType("0");//客户端注册的都为普通用户
            user.setPassword(EncodeUtils.sha(EncodeUtils.md5(password)));
            String id = userService.create(user);
            LOGGER.debug("newID:" + id);
            return buildSuccessResultInfo("账号注册成功！");
        }
        return buildFailedResultInfo(-1,"用户名已经被使用！");

    }

    @ApiOperation(value = "用户登录", httpMethod = "POST", response = BaseResultVo.class)
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo login(@RequestParam(value = "username") String username,@RequestParam  String password) {
        LOGGER.debug("username:" + username + "pwd:" + password);
        Condition condition = new QuickCondition(User.class);
        condition.addEqual("username", username);
        List<User> userList = userService.findByCondition(condition, null);
        if (userList.size() != 0) {
            User user = userList.get(0);
            if(user.getPassword().equals(EncodeUtils.sha(EncodeUtils.md5(password)))){
                return buildSuccessResultInfo("登录成功！");
            }
            return buildFailedResultInfo(-1, "密码错误！");
        }
        return buildFailedResultInfo(-1, "用户名不存在！");
    }

}
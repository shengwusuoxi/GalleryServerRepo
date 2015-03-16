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
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Api(basePath = "v1/accounts", value = "", description = "账号管理")
@Controller
@RequestMapping(value = "v1/accounts")
public class AccountController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(AccountController.class);

    @Resource(name="userService")
    IUserService userService;


    @ApiOperation(value = "注册账号", httpMethod = "POST", response = BaseResultVo.class, notes = "注册用户账号")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody BaseResultVo register(@RequestParam @ApiParam(value = "用户名") String userName,
                                               @RequestParam @ApiParam(value = "密码") String password,
                                               @RequestParam @ApiParam(value = "验证码") String checkCode) {
        LOGGER.debug("userName:" + userName + "pwd:" + password + "checkCode" + checkCode);
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

    @ApiOperation(value = "用户登录", httpMethod = "POST", response = BaseResultVo.class, notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo login(@RequestParam @ApiParam(value = "用户名") String userName,
                                            @RequestParam @ApiParam(value = "密码") String password,
                                            @RequestParam @ApiParam(value = "验证码") String checkCode,
                                            HttpServletRequest request) {
        LOGGER.debug("userName:" + userName + "pwd:" + password + "checkCode" + checkCode);
        Condition condition = new QuickCondition(User.class);
        condition.addEqual("username", userName);
        List<User> userList = userService.findByCondition(condition, null);
        if (userList.size() != 0) {
            User user = userList.get(0);
            user.setLastLoginIp(getClientIp(request));
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            user.updateLoginCount();
            userService.update(user);
            if(user.getPassword().equals(EncodeUtils.sha(EncodeUtils.md5(password)))){
                return buildSuccessResultInfo("登录成功！");
            }
            return buildFailedResultInfo(-1, "密码错误！");
        }
        return buildFailedResultInfo(-1, "用户名不存在！");
    }

}
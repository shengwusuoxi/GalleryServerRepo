package com.cangqu.gallery.server.core.service.impl;

import com.cangqu.gallery.server.base.Exception.AuthorizationException;
import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.base.dao.impl.support.Condition;
import com.cangqu.gallery.server.base.dao.impl.support.QuickCondition;
import com.cangqu.gallery.server.base.service.AbstractService;
import com.cangqu.gallery.server.base.utils.EncodeUtils;
import com.cangqu.gallery.server.core.dao.IUserDao;
import com.cangqu.gallery.server.core.model.User;
import com.cangqu.gallery.server.core.service.IUserService;
import com.cangqu.gallery.server.core.vo.UserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Service("userService")
public class UserService extends AbstractService<User, String> implements IUserService {
    private static final Log LOGGER = LogFactory.getLog(UserService.class);

    @Resource(name="userHibernateDAO")
    private IUserDao userDao;

    public UserService() {
        super();
    }

    @Override
    protected IOperations<User, String> getDao() {
        return this.userDao;
    }

    /**
     * 注册用户
     * @param userName
     * @param password
     * @return
     * @throws com.cangqu.gallery.server.base.Exception.AuthorizationException
     */
    public String register(String userName, String password) throws AuthorizationException {
        Condition condition = new QuickCondition(User.class);
        condition.addEqual("userName", userName);
        List<User> userList = userDao.findByCondition(condition, null);
        if (userList.size() == 0) {
            User user = new User();
            user.setCreateUserName(userName);
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.updateVersion();
            user.setUserName(userName);
            user.setState("0");
            user.setUserType("0");//客户端注册的都为普通用户
            user.setPassword(EncodeUtils.sha(EncodeUtils.md5(password)));
            userDao.create(user);
        }else {
            throw new AuthorizationException(-1, "用户名已经被使用");
        }
        return "账号注册成功";
    }

    /**
     * 用户登录
     * @param userName
     * @param password
     * @param loginIp
     * @return
     * @throws AuthorizationException
     */
    public String login(String userName, String password, String loginIp) throws AuthorizationException{
        LOGGER.debug("userName:" + userName + "pwd:" + password);
        Condition condition = new QuickCondition(User.class);
        condition.addEqual("userName", userName);
        List<User> userList = userDao.findByCondition(condition, null);
        if (userList.size() != 0) {
            User user = userList.get(0);
            user.setLastLoginIp(loginIp);
            user.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            user.updateLoginCount();
            userDao.update(user);
            if(user.getPassword().equals(EncodeUtils.sha(EncodeUtils.md5(password)))){
                return "登录成功！";
            }else {
                throw new AuthorizationException(-1, "密码错误！");
            }
        }else {
            throw new AuthorizationException(-1, "用户名不存在！");
        }
    }

    /**
     * 根据用户ID获得用户信息
     * @param id
     * @return
     * @throws AuthorizationException
     */
    @Override
    public UserInfo getUserInfo(String id) throws AuthorizationException {
        User user = userDao.getById(id);
        if (user != null) {
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(user, userInfo, "password");//忽略密码
            return userInfo;
        } else {
            throw new AuthorizationException(-1, "用户不存在！");
        }
    }

}

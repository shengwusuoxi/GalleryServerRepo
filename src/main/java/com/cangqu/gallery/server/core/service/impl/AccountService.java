package com.cangqu.gallery.server.core.service.impl;

import com.cangqu.gallery.server.base.Exception.AuthorizationException;
import com.cangqu.gallery.server.base.dao.impl.support.Condition;
import com.cangqu.gallery.server.base.dao.impl.support.QuickCondition;
import com.cangqu.gallery.server.base.utils.EncodeUtils;
import com.cangqu.gallery.server.core.dao.IUserDao;
import com.cangqu.gallery.server.core.model.User;
import com.cangqu.gallery.server.core.service.IAccountService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Service("accountService")
@Transactional
public class AccountService implements IAccountService{
    private static final Log LOGGER = LogFactory.getLog(AccountService.class);

    @Resource(name="userHibernateDAO")
    private IUserDao userDao;

    /**
     * 注册用户
     * @param telephone
     * @param password
     * @return
     * @throws com.cangqu.gallery.server.base.Exception.AuthorizationException
     */
    public String register(String telephone, String password) throws AuthorizationException {
        Condition condition = new QuickCondition(User.class);
        condition.addEqual("telephone", telephone);
        List<User> userList = userDao.findByCondition(condition, null);
        if (userList.size() == 0) {
            User user = new User();
            user.setCreateTime(new Timestamp(System.currentTimeMillis()));
            user.updateVersion();
            user.setTelephone(telephone);
            user.setState("0");
            user.setPassword(EncodeUtils.sha(EncodeUtils.md5(password)));
            userDao.create(user);
        }else {
            throw new AuthorizationException(-1, "电话已经被使用");
        }
        return "账号注册成功";
    }

    /**
     * 用户登录
     * @param telephone
     * @param password
     * @param loginIp
     * @return
     * @throws com.cangqu.gallery.server.base.Exception.AuthorizationException
     */
    public String login(String telephone, String password, String loginIp) throws AuthorizationException{
        LOGGER.debug("telephone:" + telephone + "pwd:" + password);
        Condition condition = new QuickCondition(User.class);
        condition.addEqual("telephone", telephone);
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
            throw new AuthorizationException(-1, "用户不存在！");
        }
    }


}

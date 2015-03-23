package com.cangqu.gallery.server.core.service.impl;

import com.cangqu.gallery.server.base.Exception.AuthorizationException;
import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.base.service.AbstractService;
import com.cangqu.gallery.server.core.dao.IUserDao;
import com.cangqu.gallery.server.core.model.User;
import com.cangqu.gallery.server.core.service.IUserService;
import com.cangqu.gallery.server.core.model.vo.UserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

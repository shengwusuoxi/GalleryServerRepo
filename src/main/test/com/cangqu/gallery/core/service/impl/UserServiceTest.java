package com.cangqu.gallery.core.service.impl;

import com.cangqu.gallery.core.model.User;
import com.cangqu.gallery.core.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.Timestamp;

import static org.junit.Assert.assertNotNull;

/**
 * Created by TangLiuJun on 2015/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml","classpath:spring-hibernate.xml"})
public class UserServiceTest {

    @Resource(name="userService")
    private IUserService userService;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setUserName("test");
        user.setUserType("0");
        user.setPassword("sdsdsd");
        user.setState("0");
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userService.create(user);
    }

    @Test
    public void testGetUserById(){
        User user = userService.getById("40288a884c219aa0014c219b91660000");
        assertNotNull(user);
    }

}

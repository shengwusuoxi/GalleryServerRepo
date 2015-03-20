package com.cangqu.gallery.server.core.service.impl;

import com.cangqu.gallery.server.base.Exception.BaseException;
import com.cangqu.gallery.server.core.service.IActivityService;
import com.cangqu.gallery.server.core.service.IUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
/**
 * Created by TangLiuJun on 2015/3/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml","classpath:spring-hibernate.xml"})
public class ActivityServiceTest {

    @Resource(name = "activityService")
    IActivityService activityService;

    @Resource(name="userService")
    private IUserService userService;

    @Test
    @Transactional
    public void testInitiateActivity(){
        try {
            System.out.println(activityService.initiateActivity("40288a884c2ac098014c2ac254cf0000", "zxxz", "zxxz", "zxxz", "zxxz"));
        } catch (BaseException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testJoinActivity(){
        try {
            activityService.joinActivity("40288a884c2ac098014c2ac254cf0000","40288a884c2be30c014c2be36ab20000");
        } catch (BaseException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testListActivities(){
        try {
            System.out.println(activityService.listActivities("40288a884c2ac098014c2ac254cf0000"));
        } catch (BaseException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void testListParticipants(){
        try {
            System.out.println(activityService.listParticipants("40288a884c2be30c014c2be36ab20000"));
        } catch (BaseException e) {
            Assert.fail(e.toString());
        }
    }

}

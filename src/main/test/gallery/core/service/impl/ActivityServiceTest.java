package gallery.core.service.impl;

import com.cangqu.gallery.core.model.Activity;
import com.cangqu.gallery.core.model.User;
import com.cangqu.gallery.core.service.IActivityService;
import com.cangqu.gallery.core.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void testInitiateActivity(){
        User initiator = userService.getById("40288a884c27937e014c2793879c0000");
        if (initiator != null){
            Activity activity = new Activity();
            activity.setCreateUserId("40288a884c27937e014c2793879c0000");
            activity.setTime("zxxz");
            activity.setPlace("xzzx");
            activity.setDescription("xzxz");
            activity.setImageUrl("xzxzxz");
            initiator.getActivities().add(activity);
            activity.getUsers().add(initiator);
            userService.update(initiator);
            System.out.println("发布活动成功！");
        }else {
            System.out.println("用户ID不存在");
        }
    }

}

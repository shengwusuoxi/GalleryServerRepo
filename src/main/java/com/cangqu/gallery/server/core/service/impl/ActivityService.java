package com.cangqu.gallery.server.core.service.impl;

import com.cangqu.gallery.server.base.Exception.BaseException;
import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.base.service.AbstractService;
import com.cangqu.gallery.server.core.dao.IActivityDao;
import com.cangqu.gallery.server.core.dao.IPainterDao;
import com.cangqu.gallery.server.core.dao.IUserDao;
import com.cangqu.gallery.server.core.model.Activity;
import com.cangqu.gallery.server.core.model.Painter;
import com.cangqu.gallery.server.core.model.User;
import com.cangqu.gallery.server.core.service.IActivityService;
import com.cangqu.gallery.server.core.model.vo.ActivityInfo;
import com.cangqu.gallery.server.core.model.vo.UserInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Service("activityService")
public class ActivityService extends AbstractService<Activity, String> implements IActivityService {

    @Resource(name="activityHibernateDAO")
    private IActivityDao activityDao;

    @Resource(name="userHibernateDAO")
    private IUserDao userDao;

    @Resource(name="painterHibernateDAO")
    private IPainterDao painterDao;

    public ActivityService() {
        super();
    }

    @Override
    protected IOperations<Activity, String> getDao() {
        return this.activityDao;
    }

    /**
     * 发起活动
     * @param painterId
     * @param time
     * @param place
     * @param description
     * @param imageURL
     * @return
     * @throws com.cangqu.gallery.server.base.Exception.BaseException
     */
    @Override
    public Activity initiateActivity(String painterId, String time, String place, String description, String imageURL) throws BaseException {
        Painter painter = painterDao.getById(painterId);
        if (painter != null){
            Activity activity = new Activity();
            activity.setCreatorId(painterId);
            activity.setCreatorName(painter.getName());
            activity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            activity.updateVersion();
            activity.setTime(time);
            activity.setPlace(place);
            activity.setDescription(description);
            activity.setImageUrl(imageURL);
            painter.getActivities().add(activity);
            painterDao.update(painter);
            return activity;
        }else{
            throw new BaseException(-1, "用户不存在！");
        }
    }

    /**
     * 参加活动
     * @param userId
     * @param activityId
     * @throws BaseException
     */
    @Override
    public void joinActivity(String userId, String activityId) throws BaseException {
        User user = userDao.getById(userId);
        Activity activity = activityDao.getById(activityId);
        if (activity != null && user != null){
            activity.getUsers().add(user);
            user.getActivities().add(activity);
            activityDao.update(activity);
        }else {
            throw new BaseException(-1,"活动或用户不存在！");
        }
    }

    @Override
    public List<ActivityInfo> listActivities(String painterId) throws BaseException {
        Painter painter = painterDao.getById(painterId);
                if (painter != null){
                    List<ActivityInfo> activityInfoList = new ArrayList<ActivityInfo>();
                    for(Activity activity : painter.getActivities()){
                        ActivityInfo activityInfo = new ActivityInfo();
                        BeanUtils.copyProperties(activity, activityInfo);
                        activityInfo.setCreatorName(activity.getCreatorName());
                        activityInfo.setParticipantsCount(String.valueOf(activity.getUsers().size()));
                        activityInfoList.add(activityInfo);
            }
            return activityInfoList;
        }else {
            throw new BaseException(-1, "用户不存在！");
        }
    }

    @Override
    public List<UserInfo> listParticipants(String activityId) throws BaseException {
        Activity activity = activityDao.getById(activityId);
        if (activity != null){
            List<UserInfo> userInfoList = new ArrayList<UserInfo>();
            for(User user : activity.getUsers()){
                UserInfo userInfo = new UserInfo();
                BeanUtils.copyProperties(user, userInfo, "password");
                userInfoList.add(userInfo);
            }
            return userInfoList;
        }else {
           throw new BaseException(-1,"活动不存在！");
        }
    }

}

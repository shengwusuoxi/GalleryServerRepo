package com.cangqu.gallery.core.service.impl;

import com.cangqu.gallery.base.Exception.BaseException;
import com.cangqu.gallery.base.dao.IOperations;
import com.cangqu.gallery.base.service.AbstractService;
import com.cangqu.gallery.core.dao.IActivityDao;
import com.cangqu.gallery.core.dao.IUserDao;
import com.cangqu.gallery.core.model.Activity;
import com.cangqu.gallery.core.model.User;
import com.cangqu.gallery.core.service.IActivityService;
import com.cangqu.gallery.core.vo.ActivityInfo;
import com.cangqu.gallery.core.vo.UserInfo;
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

    public ActivityService() {
        super();
    }

    @Override
    protected IOperations<Activity, String> getDao() {
        return this.activityDao;
    }

    /**
     * 发起活动
     * @param initiatorId
     * @param time
     * @param place
     * @param description
     * @param imageURL
     * @return
     * @throws com.cangqu.gallery.base.Exception.BaseException
     */
    @Override
    public Activity initiateActivity(String initiatorId, String time, String place, String description, String imageURL) throws BaseException {
        User initiator = userDao.getById(initiatorId);
        if (initiator != null){
            Activity activity = new Activity();
            activity.setCreateUserId(initiatorId);
            activity.setCreateUserName(initiator.getUserName());
            activity.setCreateTime(new Timestamp(System.currentTimeMillis()));
            activity.updateVersion();
            activity.setTime(time);
            activity.setPlace(place);
            activity.setDescription(description);
            activity.setImageUrl(imageURL);
            initiator.getActivities().add(activity);
            userDao.update(initiator);
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
    public List<ActivityInfo> listActivities(String userId) throws BaseException {
        User user = userDao.getById(userId);
        if (user != null){
            List<ActivityInfo> activityInfoList = new ArrayList<ActivityInfo>();
            for(Activity activity : user.getActivities()){
                ActivityInfo activityInfo = new ActivityInfo();
                BeanUtils.copyProperties(activity, activityInfo);
                activityInfo.setCreateUserName(activity.getCreateUserName());
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

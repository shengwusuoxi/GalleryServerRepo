package com.cangqu.gallery.core.service;

import com.cangqu.gallery.base.Exception.BaseException;
import com.cangqu.gallery.base.dao.IOperations;
import com.cangqu.gallery.core.model.Activity;
import com.cangqu.gallery.core.vo.ActivityInfo;
import com.cangqu.gallery.core.vo.UserInfo;

import java.util.List;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
public interface IActivityService extends IOperations<Activity, String> {
    Activity initiateActivity(String initiatorId, String time, String place, String description, String imageURL) throws BaseException;

    void joinActivity(String userId, String activityId) throws BaseException;

    List<ActivityInfo> listActivities(String userId) throws BaseException;

    List<UserInfo> listParticipants(String activityId) throws BaseException;
}
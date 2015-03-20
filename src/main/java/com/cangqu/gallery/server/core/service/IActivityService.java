package com.cangqu.gallery.server.core.service;

import com.cangqu.gallery.server.base.Exception.BaseException;
import com.cangqu.gallery.server.base.dao.IOperations;
import com.cangqu.gallery.server.core.model.Activity;
import com.cangqu.gallery.server.core.vo.ActivityInfo;
import com.cangqu.gallery.server.core.vo.UserInfo;

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
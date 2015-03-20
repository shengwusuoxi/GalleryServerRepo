package com.cangqu.gallery.server.core.controller;

import com.cangqu.gallery.server.base.Exception.BaseException;
import com.cangqu.gallery.server.base.controller.BaseController;
import com.cangqu.gallery.server.base.vo.BaseResultVo;
import com.cangqu.gallery.server.core.service.IActivityService;
import com.cangqu.gallery.server.core.service.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/3/11 0011.
 */
@Api(basePath = "v1/activity", value = "", description = "活动")
@Controller
@RequestMapping(value = "v1/activity")
public class ActivityController extends BaseController {

    private static final Log LOGGER = LogFactory.getLog(ActivityController.class);

    @Resource(name = "activityService")
    IActivityService activityService;

    @Resource(name="userService")
    IUserService userService;

    @ApiOperation(value = "发起活动", httpMethod = "POST", response = BaseResultVo.class, notes = "画家发起活动")
    @RequestMapping(value = "/initiate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody BaseResultVo initiateActivity(@RequestParam @ApiParam(value = "活动发起人（画家/机构）ID") String initiatorId,
                                                       @RequestParam @ApiParam(value = "活动时间") String time,
                                                       @RequestParam @ApiParam(value = "活动地点") String place,
    @RequestParam @ApiParam(value = "活动内容(描述)") String description,
    @RequestParam @ApiParam(value = "图片URL") String imageURL) {
        try {
            return buildSuccessResultInfo(activityService.initiateActivity(
                    initiatorId,time,place,description,imageURL));
        } catch (BaseException e) {
            return buildFailedResultInfo(e);
        }
    }

    @ApiOperation(value = "参加活动", httpMethod = "POST", response = BaseResultVo.class, notes = "普通用户参加活动")
    @RequestMapping(value = "/join", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo joinActivity(@RequestParam @ApiParam(value = "活动ID") String activityId,
                                                   @RequestParam @ApiParam(value = "参加活动的用户ID") String userId) {
        try {
            activityService.joinActivity(userId,activityId);
        } catch (BaseException e) {
            return  buildFailedResultInfo(e);
        }
        return buildSuccessResultInfo("参加活动成功！");
    }

    @ApiOperation(value = "获得活动列表", httpMethod = "GET", response = BaseResultVo.class, notes = "获得用户发起或参与的活动列表")
    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo listActivities(@PathVariable("id") @ApiParam(value = "用户ID") String userId) {

        try {
            return buildSuccessResultInfo(activityService.listActivities(userId));
        } catch (BaseException e) {
            return buildFailedResultInfo(e);
        }
    }

    @ApiOperation(value = "获得参与某一活动的用户列表", httpMethod = "GET", response = BaseResultVo.class, notes = "根据活动ID获得参与该活动的所有用户信息")
    @RequestMapping(value = "/listUsers/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody BaseResultVo listParticipants(@PathVariable("id") @ApiParam(value = "活动ID") String activityId) {
        try {
            return buildSuccessResultInfo(activityService.listParticipants(activityId));
        } catch (BaseException e) {
            return buildFailedResultInfo(e);
        }
    }

}
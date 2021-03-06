package com.industrial.controller;

import com.industrial.common.constant.UserConstants;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.ActivityDto;
import com.industrial.common.utils.DateUtils;
import com.industrial.common.utils.StringUtils;
import com.industrial.common.vo.ActivityFileVo;
import com.industrial.common.vo.ActivityVo;
import com.industrial.domin.AppActivityLog;
import com.industrial.domin.AppCategory;
import com.industrial.service.AppActivityService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.industrial.common.annotation.Log;
import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.enums.BusinessType;
import com.industrial.domin.AppActivity;
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.common.core.page.TableDataInfo;
import javax.annotation.Resource;
import com.industrial.common.core.domain.model.LoginUser;

/**
 * @author chenjh
 * @date 2022年01月19日
 */
@RestController
@RequestMapping("/activity")
public class AppActivityController extends BaseController {
    @Resource
    private AppActivityService activityService;


    /**
     * 查询活动列表
     */
    //@PreAuthorize("@ss.hasPermi('activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppActivity appActivity)
    {
        startPage();
        List<ActivityDto> list = activityService.selectAppActivityList(appActivity);
        return getDataTable(list);
    }

    /**
     * 根据ID查询活动详情
     */
    @GetMapping("/details/{id}")
    public ResponseResult<ActivityDto> details(@PathVariable("id") Integer id) {
        ResponseResult<ActivityDto> result = null;
        ActivityDto activityDto = activityService.selectActivityById(id);
        result = ResponseResult.success(activityDto);
        return result;
    }

    /**
     * 添加活动
     */
    @PostMapping("/add")
    public ResponseResult<ActivityDto> add(@RequestBody ActivityVo activityVo) {
        ResponseResult<ActivityDto> result = null;
        if (activityService.insertActivity(activityVo)) {
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 修改活动
     */
    //@PreAuthorize("@ss.hasPermi('category:edit')")
    @Log(title = "活动", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody ActivityVo activityVo)
    {
        return toAjax(activityService.updateAppActivity(activityVo));
    }

    /**
     * 删除活动
     */
    @DeleteMapping("/remove/{id}")
    public ResponseResult<ActivityDto> remove(@PathVariable Integer id) {
        ResponseResult<ActivityDto> result = null;
        if (activityService.deleteActivityById(id)) {
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 更新活动状态(终止活动、锁定活动)
     */
    @PostMapping("/updateStatus")
    public ResponseResult<AppActivity> updateStatus(@RequestBody AppActivity appActivity) {
        ResponseResult<AppActivity> result = null;
        if (activityService.updateActivityById(appActivity)) {
            //写活动日志
            Long operId = getUserId();
            String operName = getUsername();
            AppActivityLog activityLog = new AppActivityLog();
            activityLog.setCreateId(operId.intValue());
            activityLog.setCreateName(operName);
            if (appActivity.getActivityStatus() == 5) {
                activityLog.setLogContents("终止活动");
            } else if (appActivity.getActivityStatus() == 3) {
                activityLog.setLogContents("锁定活动");
            }

            activityService.insertActivityLog(activityLog);
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 更新总结信息
     */
    @PostMapping("/updateConclusion")
    public ResponseResult<AppActivity> updateConclusion(@RequestBody AppActivity appActivity) {
        ResponseResult<AppActivity> result = null;
        Long operId = getUserId();
        //String operName = getUsername();
        appActivity.setConclusionPerson(operId);
        appActivity.setConclusionTime(DateUtils.getNowDate());

        if (activityService.updateActivityById(appActivity)) {
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 添加活动日志
     */
    @PostMapping("/insertActivityLog")
    public ResponseResult<AppActivityLog> insertActivityLog(@RequestBody AppActivityLog activityLog) {
        ResponseResult<AppActivityLog> result = null;
        Long operId = getUserId();
        String operName = getUsername();
        activityLog.setCreateId(operId.intValue());
        activityLog.setCreateName(operName);
        if (activityService.insertActivityLog(activityLog)) {
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 上传活动附件
     */
    @PostMapping("/insertActivityFile")
    public ResponseResult<AppActivity> insertActivityFile(@RequestBody ActivityFileVo activityFileVo) {
        ResponseResult<AppActivity> result = null;
        if (activityService.insertActivityFile(activityFileVo)) {
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 查询活动列表
     */
    //@PreAuthorize("@ss.hasPermi('activity:list')")
    @GetMapping("/fileList")
    public TableDataInfo fileList(AppActivity appActivity)
    {
        startPage();
        List<ActivityDto> list = activityService.selectAppActivityList(appActivity);
        return getDataTable(list);
    }
}

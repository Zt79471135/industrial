package com.industrial.controller;

import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.ActivityDto;
import com.industrial.common.vo.ActivityVo;
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

/**
 * @author zhu
 * @date 2022年01月14日 11:49
 */
@RestController
@RequestMapping("activity")
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
        List<AppActivity> list = activityService.selectAppActivityList(appActivity);
        return getDataTable(list);
    }

    /**
     * 根据ID查询活动详情
     */
    @GetMapping("/details")
    public ResponseResult<ActivityDto> details(@RequestParam Integer activityId) {
        ResponseResult<ActivityDto> result = null;
        ActivityDto activityDto = activityService.selectActivityById(activityId);
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
     * 删除活动
     */
    @PostMapping("/remove")
    public ResponseResult<ActivityDto> remove(@RequestBody Integer activityId) {
        ResponseResult<ActivityDto> result = null;
        if (activityService.deleteActivityById(activityId)) {
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
}

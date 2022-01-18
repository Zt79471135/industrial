package com.industrial.controller;

import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.ActivityDto;
import com.industrial.common.vo.ActivityVo;
import com.industrial.service.AppActivityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhu
 * @date 2022年01月14日 11:49 测试提交
 */
@RestController
@RequestMapping("activity")
public class AppActivityController {
    @Resource
    private AppActivityService activityService;

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

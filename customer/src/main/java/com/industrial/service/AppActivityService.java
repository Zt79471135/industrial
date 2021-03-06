package com.industrial.service;
import com.industrial.common.vo.ActivityFileVo;
import com.industrial.domin.AppActivityLog;
import com.industrial.mapper.AppActivityMapper;
import com.industrial.domin.AppActivity;
import com.industrial.common.dto.ActivityDto;
import com.industrial.common.vo.ActivityVo;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月14日 11:49
 */
public interface AppActivityService {

    /**
     * 查询活动列表
     *
     * @param appActivity 活动
     * @return 活动集合
     */
    public List<ActivityDto> selectAppActivityList(AppActivity appActivity);

    /**
     * 更加ID检索活动
     * @param activityId
     * @return
     */
    ActivityDto selectActivityById(Integer activityId);

    /**
     * 活动添加
     * @param activityVo
     * @return
     */
    boolean insertActivity(ActivityVo activityVo);

    /**
     * 修改活动
     *
     * @param activityVo 活动
     * @return 结果
     */
    public int updateAppActivity(ActivityVo activityVo);

    /**
     * 根据活动ID删除活动
     * @param activityId
     * @return
     */
    boolean deleteActivityById(Integer  activityId);

    /**
     * 更新活动信息
     *
     * @param appActivity 活动
     * @return 结果
     */
    public boolean updateActivityById(AppActivity appActivity);

    /**
     * 添加活动日志
     *
     * @param activityLog 活动
     * @return 结果
     */
    public boolean insertActivityLog(AppActivityLog activityLog);

    public boolean insertActivityFile(ActivityFileVo activityFileVo);
}

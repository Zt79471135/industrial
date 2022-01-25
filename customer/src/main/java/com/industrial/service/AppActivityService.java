package com.industrial.service;
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
    public List<AppActivity> selectAppActivityList(AppActivity appActivity);

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
     * 根据活动ID删除活动
     * @param activityId
     * @return
     */
    boolean deleteActivityById(Integer activityId);
}

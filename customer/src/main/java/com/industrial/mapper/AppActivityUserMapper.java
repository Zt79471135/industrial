package com.industrial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.domin.AppActivityUser;

/**
 * @author chenjh
 * @date 2022年01月14日 16:52
 */
public interface AppActivityUserMapper extends BaseMapper<AppActivityUser> {
    /**
     * 删除活动与参与人员
     *
     * @param activityId 活动与参与人员主键
     * @return 结果
     */
    public int deleteActivityUserById(Long activityId);
}
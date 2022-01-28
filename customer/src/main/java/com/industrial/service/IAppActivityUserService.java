package com.industrial.service;

import com.industrial.common.vo.ActivityVo;
import com.industrial.domin.AppActivity;
import com.industrial.domin.AppActivityUser;
import java.util.List;
import com.industrial.mapper.AppActivityUserMapper;

/**
 * 描述:
 * 日期：2022-01-28
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
public interface IAppActivityUserService
{

    /**
     * 删除活动与参与人员信息
     *
     * @param activityId 活动与参与人员主键
     * @return 结果
     */
    public int deleteActivityUserById(Long activityId);
}

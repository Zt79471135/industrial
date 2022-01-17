package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.dto.ActivityDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.ActivityVo;
import com.industrial.entity.AppActivity;
import com.industrial.entity.AppActivityUser;
import com.industrial.mapper.AppActivityMapper;
import com.industrial.mapper.AppActivityUserMapper;
import com.industrial.service.AppActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2022年01月14日 11:50
 */
@Service
public class AppActivityServiceImpl implements AppActivityService {
    @Resource
    private AppActivityMapper activityMapper;
    @Resource
    private AppActivityUserMapper activityUserMapper;

    @Override
    public ActivityDto selectActivityById(Integer activityId) {
        AppActivity appActivity = activityMapper.selectById(activityId);
        if (appActivity != null) {
            QueryWrapper<AppActivityUser> qw = new QueryWrapper<>();
            qw.lambda().eq(AppActivityUser::getActivityId, activityId);
            List<AppActivityUser> activityUserList = activityUserMapper.selectList(qw);
            ActivityDto activityDto = new ActivityDto();
            BeanUtils.copyProperties(appActivity, activityDto);

            return activityDto;
        } else {
            throw new ServiceException("活动详情为空");
        }
    }

    @Override
    public boolean insertActivity(ActivityVo activityVo) {
        AppActivity activity = new AppActivity();
        List<Integer> integerList = new ArrayList<>();
        BeanUtils.copyProperties(activityVo, activity);
        if (activityMapper.insert(activity) == 1) {
            List<Integer> userIdList = activityVo.getUserIdList();
            integerList = userIdList.stream().map(userId -> {
                AppActivityUser activityUser = new AppActivityUser();
                return activityUserMapper.insert(activityUser);
            }).collect(Collectors.toList());
            return integerList.size() == userIdList.size();
        } else {
            throw new ServiceException();
        }
    }

    @Override
    public boolean deleteActivityById(Integer activityId) {
        return activityMapper.deleteById(activityId) == 1;
    }
}

package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.dto.ActivityDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.ActivityVo;
import com.industrial.domin.AppActivity;
import com.industrial.domin.AppActivityUser;
import com.industrial.mapper.AppActivityMapper;
import com.industrial.mapper.AppActivityUserMapper;
import com.industrial.service.AppActivityService;
import com.industrial.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Resource
    private SysUserMapper sysUserMapper;


    /**
     * 查询活动列表
     *
     * @param appActivity 活动
     * @return 活动
     */
    @Override
    public List<AppActivity> selectAppActivityList(AppActivity appActivity)
    {
        return activityMapper.selectAppActivityList(appActivity);
    }

    /**
     * 查询活动
     *
     * @param activityId 活动主键
     * @return 活动
     */
    @Override
    public ActivityDto selectActivityById(Integer activityId) {
        AppActivity appActivity = activityMapper.selectById(activityId);
        if (appActivity != null) {
            SysUser user = sysUserMapper.selectUserById(Long.valueOf(appActivity.getHeadUser()));
            QueryWrapper<AppActivityUser> qw = new QueryWrapper<>();
            qw.lambda().eq(AppActivityUser::getActivityId, activityId);
            List<AppActivityUser> activityUserList = activityUserMapper.selectList(qw);
            List<SysUser> userList = activityUserList.stream().map(appActivityUser ->
                    {
                        SysUser sysUser = sysUserMapper.selectUserById(Long.valueOf(appActivityUser.getUserId()));

                        return sysUser;
                    }
            ).collect(Collectors.toList());
            ActivityDto activityDto = new ActivityDto();
            BeanUtils.copyProperties(appActivity, activityDto);
            activityDto.setUserList(userList);
            activityDto.setUser(user);
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

package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.vo.FollowVo;
import com.industrial.domin.*;
import com.industrial.mapper.*;
import com.industrial.service.IAppFollowService;
import com.industrial.system.mapper.SysDeptMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2022年01月24日 16:06
 */
@Service
public class AppFollowServiceImpl implements IAppFollowService {
    @Resource
    private AppFollowMapper followMapper;
    @Resource
    private AppOrderMapper orderMapper;
    @Resource
    private AppUserNoticeMapper userNoticeMapper;
    @Resource
    private SysDeptMapper deptMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public boolean insertFollow(FollowVo follow) {
        AppFollow appFollow = new AppFollow();
        BeanUtils.copyProperties(follow, appFollow);
        long aheadTime = follow.getFollowTime().getTime() - follow.getAheadTime();
        Date date = new Date(aheadTime);
        appFollow.setReminderTime(date);
        return followMapper.insert(appFollow) == 1;
    }

    @Override
    public void executeList() {
        List<AppFollow> followList = followMapper.selectList(new QueryWrapper<>());
        List<AppFollow> follows = new ArrayList<AppFollow>();
        for (AppFollow follow : followList) {
            Date reminderTime = follow.getReminderTime();
            Date now = new Date();
            long time = reminderTime.getTime();
            long nowTime = now.getTime();
            if (time - nowTime < 10000) {
                follows.add(follow);
                follow.setNotified((byte)1);
                followMapper.updateById(follow);
            }
        }
        List<Integer> integerList = follows.stream().map(follow -> {
            AppOrder order = orderMapper.selectById(follow.getFollowOrder());
            String orderNo = order.getOrderNo();
            String msg = "你有" + orderNo + "订单需要处理";
            AppUserNotice userNotice = new AppUserNotice();
            userNotice.setUid(follow.getFollowUser());
            userNotice.setMsg(msg);
            return userNoticeMapper.insert(userNotice);
        }).collect(Collectors.toList());
    }

    @Override
    public List<User> selectStaff(SysUser user) {
        Long deptId = user.getDeptId();
        SysDept dept = deptMapper.selectDeptById(deptId);
        String leader = dept.getLeader();
        long uid = Long.parseLong(leader);
        if (uid==user.getUserId()){
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw.lambda().eq(User::getDeptId,deptId);
            List<User> userList = userMapper.selectList(qw);
            return userList;
        }else {
           return null;
        }
    }
}

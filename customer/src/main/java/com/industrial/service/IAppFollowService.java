package com.industrial.service;

import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.dto.UserDto;
import com.industrial.common.vo.FollowVo;
import com.industrial.domin.User;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月24日 16:05
 */
public interface IAppFollowService {
    /**
     * 创建跟进任务
     *
     * @param follow
     * @return
     */
    boolean insertFollow(FollowVo follow,int type);

    /**
     * 跟进任务通知
     */
    void executeList();

    /**
     * 查询可选择的跟进人员名单
     *
     * @return
     */
    List<User> selectStaff(SysUser user);

    /**
     * 查询所有协作人员
     * @return
     */
    List<UserDto> selectTeam();

}

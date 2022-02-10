package com.industrial.controller;

import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.domin.User;
import com.industrial.service.IAppFollowService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhu
 * @date 2022年01月24日 16:11
 */
@RestController
@RequestMapping("follow")
public class AppFollowController extends BaseController {
    public static final int CLIENT_FOLLOW = 1;
    public static final int CONTACTS_FOLLOW = 2;
    public static final int ORDER_FOLLOW = 3;
    public static final int COST_FOLLOW = 4;



    @Resource
    private IAppFollowService followService;

    /**
     * 定时任务,查看数据库的时间
     */
    @PostMapping("/execute")
    public void execute() {
        followService.executeList();
    }
    @GetMapping("staff")
    public ResponseResult<Object> staff() {
        SysUser user = getLoginUser().getUser();
        List<User> userList = followService.selectStaff(user);
        if (userList != null) {
            return ResponseResult.success(userList);
        }else {
            List<SysUser> lists = new ArrayList<>();
            lists.add(user);
            return ResponseResult.success(lists);
        }
    }
}

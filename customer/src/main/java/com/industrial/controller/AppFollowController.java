package com.industrial.controller;

import com.industrial.common.core.domain.ResponseResult;
import com.industrial.service.IAppFollowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhu
 * @date 2022年01月24日 16:11
 */
@RestController
@RequestMapping("follow")
public class AppFollowController {
    @Resource
    private IAppFollowService followService;

    @PostMapping("/execute")
    public void execute() {
        followService.executeList();
    }
}

package com.industrial.service.impl;

import com.industrial.common.vo.ActivityVo;
import com.industrial.domin.AppActivity;
import com.industrial.domin.AppActivityUser;
import java.util.List;
import com.industrial.common.utils.DateUtils;
import com.industrial.service.IAppActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppActivityUserMapper;

/**
 * 描述:
 * 日期：2022-01-28
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
@Service
public class AppActivityUserServiceImpl implements IAppActivityUserService {

    @Autowired
    private AppActivityUserMapper appActivityUserMapper;

    /**
     * 删除活动与参与人员信息
     *
     * @param id 活动与参与人员主键
     * @return 结果
     */
    @Override
    public int deleteActivityUserById(Long id)
    {
        return appActivityUserMapper.deleteActivityUserById(id);
    }
}

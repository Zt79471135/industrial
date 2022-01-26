package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.vo.CheckVo;
import com.industrial.domin.AppCheckMainConfig;
import com.industrial.mapper.AppCheckMainConfigMapper;
import com.industrial.mapper.AppCheckMapper;
import com.industrial.mapper.AppSubCheckConfigMapper;
import com.industrial.service.AppCheckService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhu
 * @date 2022年01月26日 9:25
 */
@Service
public class AppCheckServiceImpl implements AppCheckService {
    @Resource
    private AppCheckMapper checkMapper;
    @Resource
    private AppCheckMainConfigMapper checkMainConfigMapper;
    @Resource
    private AppSubCheckConfigMapper checkConfigMapper;
    /**
     * 审批开启的状态
     */
    public static final Byte OPEN_STATUS = 0;

    /**
     * 审核
     *
     * @param checkVo
     * @return
     */
    @Override
    public boolean updateStatus(CheckVo checkVo) {
        QueryWrapper<AppCheckMainConfig> qw = new QueryWrapper<>();
        int type = checkVo.getType();
        qw.lambda().eq(AppCheckMainConfig::getCheckType, (byte) type);
        qw.lambda().eq(AppCheckMainConfig::getCheckStatus, OPEN_STATUS);
        AppCheckMainConfig checkMainConfig = checkMainConfigMapper.selectOne(qw);
        if (checkMainConfig == null) {
            return true;
        }else {
            /**
             * 根据类型查询审核层级
             */

        }
        return false;
    }
}

package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import com.industrial.domin.AppOrderLog;
import com.industrial.mapper.AppOrderLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppCheckMapper;
import com.industrial.domin.AppCheck;
import com.industrial.service.IAppCheckService;

/**
 * systemService业务层处理
 * 
 * @author lishenkang
 * @date 2022-01-26
 */
@Service
public class AppCheckServiceImpl implements IAppCheckService 
{
    @Autowired
    private AppCheckMapper appCheckMapper;
    @Autowired
    private AppOrderLogMapper orderLogMapper;

    /**
     * 查询system
     * 
     * @param id system主键
     * @return system
     */
    @Override
    public AppCheck selectAppCheckById(Long id)
    {
        return appCheckMapper.selectAppCheckById(id);
    }

    /**
     * 查询system列表
     * 
     * @param appCheck system
     * @return system
     */
    @Override
    public List<AppCheck> selectAppCheckList(AppCheck appCheck)
    {
        return appCheckMapper.selectAppCheckList(appCheck);
    }

    /**
     * 新增system
     * 
     * @param appCheck system
     * @return 结果
     */
    @Override
    public int insertAppCheck(AppCheck appCheck)
    {
        appCheck.setCreateTime(DateUtils.getNowDate());
        return appCheckMapper.insertAppCheck(appCheck);
    }

    /**
     * 修改system
     * 
     * @param appCheck system
     * @return 结果
     */
    @Override
    public int updateAppCheck(AppCheck appCheck)
    {
        appCheck.setUpdateTime(DateUtils.getNowDate());
        return appCheckMapper.updateAppCheck(appCheck);
    }

    /**
     * 批量删除system
     * 
     * @param ids 需要删除的system主键
     * @return 结果
     */
    @Override
    public int deleteAppCheckByIds(Long[] ids)
    {
        return appCheckMapper.deleteAppCheckByIds(ids);
    }

    /**
     * 删除system信息
     * 
     * @param id system主键
     * @return 结果
     */
    @Override
    public int deleteAppCheckById(Long id)
    {
        return appCheckMapper.deleteAppCheckById(id);
    }

    public AppCheck selectAppCheckByOrderNo(String orderNo) {
        return appCheckMapper.selectAppCheckByOrderNo(orderNo);
    }
}

package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppSubCheckConfigMapper;
import com.industrial.domin.AppSubCheckConfig;
import com.industrial.service.IAppSubCheckConfigService;

import javax.annotation.Resource;

/**
 * 审核设置子Service业务层处理
 * 
 * @author li.shenkang
 * @date 2022-01-24
 */
@Service
public class AppSubCheckConfigServiceImpl implements IAppSubCheckConfigService 
{
    @Resource
    private AppSubCheckConfigMapper appSubCheckConfigMapper;

    /**
     * 查询审核设置子
     * 
     * @param id 审核设置子主键
     * @return 审核设置子
     */
    @Override
    public AppSubCheckConfig selectAppSubCheckConfigById(Long id)
    {
        return appSubCheckConfigMapper.selectAppSubCheckConfigById(id);
    }

    /**
     * 查询审核设置子列表
     * 
     * @param appSubCheckConfig 审核设置子
     * @return 审核设置子
     */
    @Override
    public List<AppSubCheckConfig> selectAppSubCheckConfigList(AppSubCheckConfig appSubCheckConfig)
    {
        return appSubCheckConfigMapper.selectAppSubCheckConfigList(appSubCheckConfig);
    }

    /**
     * 新增审核设置子
     * 
     * @param appSubCheckConfig 审核设置子
     * @return 结果
     */
    @Override
    public int insertAppSubCheckConfig(AppSubCheckConfig appSubCheckConfig)
    {
        return appSubCheckConfigMapper.insertAppSubCheckConfig(appSubCheckConfig);
    }

    /**
     * 修改审核设置子
     * 
     * @param appSubCheckConfig 审核设置子
     * @return 结果
     */
    @Override
    public int updateAppSubCheckConfig(AppSubCheckConfig appSubCheckConfig)
    {
        appSubCheckConfig.setUpdateTime(DateUtils.getNowDate());
        return appSubCheckConfigMapper.updateAppSubCheckConfig(appSubCheckConfig);
    }

    /**
     * 批量删除审核设置子
     * 
     * @param ids 需要删除的审核设置子主键
     * @return 结果
     */
    @Override
    public int deleteAppSubCheckConfigByIds(Long[] ids)
    {
        return appSubCheckConfigMapper.deleteAppSubCheckConfigByIds(ids);
    }

    /**
     * 删除审核设置子信息
     * 
     * @param id 审核设置子主键
     * @return 结果
     */
    @Override
    public int deleteAppSubCheckConfigById(Long id)
    {
        return appSubCheckConfigMapper.deleteAppSubCheckConfigById(id);
    }
}

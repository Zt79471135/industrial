package com.industrial.service;

import java.util.List;

import com.industrial.common.dto.CheckConfigDto;
import com.industrial.domin.AppCheckMainConfig;

/**
 * 审核设置主Service接口
 * 
 * @author lishenkang
 * @date 2022-01-24
 */
public interface IAppCheckMainConfigService
{
    /**
     * 查询审核设置主
     * 
     * @param id 审核设置主主键
     * @return 审核设置主
     */
    public AppCheckMainConfig selectAppCheckMainConfigById(Long id);

    /**
     * 查询审核设置主列表
     * 
     * @param appCheckmainconfig 审核设置主
     * @return 审核设置主集合
     */
    public List<AppCheckMainConfig> selectAppCheckMainConfigList(AppCheckMainConfig appCheckmainconfig);

    /**
     * 新增审核设置主
     * 
     * @param appCheckmainconfig 审核设置主
     * @return 结果
     */
    public int insertAppCheckMainConfig(AppCheckMainConfig appCheckmainconfig);

    /**
     * 修改审核设置主
     * 
     * @param appCheckmainconfig 审核设置主
     * @return 结果
     */
    public int updateAppCheckMainConfig(AppCheckMainConfig appCheckmainconfig);

    /**
     * 批量删除审核设置主
     * 
     * @param ids 需要删除的审核设置主主键集合
     * @return 结果
     */
    public int deleteAppCheckMainConfigByIds(Long[] ids);

    /**
     * 删除审核设置主信息
     * 
     * @param id 审核设置主主键
     * @return 结果
     */
    public int deleteAppCheckMainConfigById(Long id);

    int updateCheckConfigDto(CheckConfigDto checkConfigDto);
}

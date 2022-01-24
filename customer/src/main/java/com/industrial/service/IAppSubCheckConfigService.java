package com.industrial.service;

import java.util.List;
import com.industrial.domin.AppSubCheckConfig;

/**
 * 审核设置子Service接口
 * 
 * @author li.shenkang
 * @date 2022-01-24
 */
public interface IAppSubCheckConfigService 
{
    /**
     * 查询审核设置子
     * 
     * @param id 审核设置子主键
     * @return 审核设置子
     */
    public AppSubCheckConfig selectAppSubCheckConfigById(Long id);

    /**
     * 查询审核设置子列表
     * 
     * @param appSubCheckConfig 审核设置子
     * @return 审核设置子集合
     */
    public List<AppSubCheckConfig> selectAppSubCheckConfigList(AppSubCheckConfig appSubCheckConfig);

    /**
     * 新增审核设置子
     * 
     * @param appSubCheckConfig 审核设置子
     * @return 结果
     */
    public int insertAppSubCheckConfig(AppSubCheckConfig appSubCheckConfig);

    /**
     * 修改审核设置子
     * 
     * @param appSubCheckConfig 审核设置子
     * @return 结果
     */
    public int updateAppSubCheckConfig(AppSubCheckConfig appSubCheckConfig);

    /**
     * 批量删除审核设置子
     * 
     * @param ids 需要删除的审核设置子主键集合
     * @return 结果
     */
    public int deleteAppSubCheckConfigByIds(Long[] ids);

    /**
     * 删除审核设置子信息
     * 
     * @param id 审核设置子主键
     * @return 结果
     */
    public int deleteAppSubCheckConfigById(Long id);
}

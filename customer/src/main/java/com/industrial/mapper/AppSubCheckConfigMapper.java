package com.industrial.mapper;

import java.util.List;
import com.industrial.domin.AppSubCheckConfig;

/**
 * 审核设置子Mapper接口
 * 
 * @author li.shenkang
 * @date 2022-01-24
 */
public interface AppSubCheckConfigMapper 
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
     * 删除审核设置子
     * 
     * @param id 审核设置子主键
     * @return 结果
     */
    public int deleteAppSubCheckConfigById(Long id);

    /**
     * 批量删除审核设置子
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppSubCheckConfigByIds(Long[] ids);

    public int deleteAppSubCheckConfigByConfigId(Long id);
}

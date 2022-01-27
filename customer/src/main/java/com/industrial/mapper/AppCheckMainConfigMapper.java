package com.industrial.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.common.dto.CheckDto;
import com.industrial.domin.AppCheckMainConfig;
import com.industrial.domin.AppCheckUser;

/**
 * 审核设置主Mapper接口
 * 
 * @author lishenkang
 * @date 2022-01-24
 */
public interface AppCheckMainConfigMapper extends BaseMapper<AppCheckUser>
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
     * 删除审核设置主
     * 
     * @param id 审核设置主主键
     * @return 结果
     */
    public int deleteAppCheckMainConfigById(Long id);

    /**
     * 批量删除审核设置主
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppCheckMainConfigByIds(Long[] ids);

    public List<CheckDto> selectAppCheckMainSubList(CheckDto dto);
}

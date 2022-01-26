package com.industrial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.domin.AppSubCheckConfig;import java.util.List;

/**
 * @author zhu
 * @date 2022年01月26日 10:58
 */
public interface AppSubCheckConfigMapper extends BaseMapper<AppSubCheckConfig> {
    /**
     * 查询审核设置子
     *
     * @param id 审核设置子主键
     * @return 审核设置子
     */
    AppSubCheckConfig selectAppSubCheckConfigById(Long id);

    /**
     * 查询审核设置子列表
     *
     * @param appSubCheckConfig 审核设置子
     * @return 审核设置子集合
     */
    List<AppSubCheckConfig> selectAppSubCheckConfigList(AppSubCheckConfig appSubCheckConfig);

    /**
     * 新增审核设置子
     *
     * @param appSubCheckConfig 审核设置子
     * @return 结果
     */
    int insertAppSubCheckConfig(AppSubCheckConfig appSubCheckConfig);

    /**
     * 修改审核设置子
     *
     * @param appSubCheckConfig 审核设置子
     * @return 结果
     */
    int updateAppSubCheckConfig(AppSubCheckConfig appSubCheckConfig);

    /**
     * 删除审核设置子
     *
     * @param id 审核设置子主键
     * @return 结果
     */
    int deleteAppSubCheckConfigById(Long id);

    /**
     * 批量删除审核设置子
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteAppSubCheckConfigByIds(Long[] ids);

    int deleteAppSubCheckConfigByConfigId(Long id);
}
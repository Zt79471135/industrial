package com.industrial.service;

import java.util.List;
import com.industrial.domin.AppCheck;

/**
 * systemService接口
 * 
 * @author lishenkang
 * @date 2022-01-26
 */
public interface IAppCheckService 
{
    /**
     * 查询system
     * 
     * @param id system主键
     * @return system
     */
    public AppCheck selectAppCheckById(Long id);

    /**
     * 查询system列表
     * 
     * @param appCheck system
     * @return system集合
     */
    public List<AppCheck> selectAppCheckList(AppCheck appCheck);

    /**
     * 新增system
     * 
     * @param appCheck system
     * @return 结果
     */
    public int insertAppCheck(AppCheck appCheck);

    /**
     * 修改system
     * 
     * @param appCheck system
     * @return 结果
     */
    public int updateAppCheck(AppCheck appCheck);

    /**
     * 批量删除system
     * 
     * @param ids 需要删除的system主键集合
     * @return 结果
     */
    public int deleteAppCheckByIds(Long[] ids);

    /**
     * 删除system信息
     * 
     * @param id system主键
     * @return 结果
     */
    public int deleteAppCheckById(Long id);
}

package com.industrial.mapper;

import java.util.List;
import com.industrial.domin.AppUserSalesman;

/**
 * 业务员信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-01-20
 */
public interface AppUserSalesmanMapper 
{
    /**
     * 查询业务员信息
     * 
     * @param id 业务员信息主键
     * @return 业务员信息
     */
    public AppUserSalesman selectAppUserSalesmanById(Long id);

    /**
     * 查询业务员信息列表
     * 
     * @param appUserSalesman 业务员信息
     * @return 业务员信息集合
     */
    public List<AppUserSalesman> selectAppUserSalesmanList(AppUserSalesman appUserSalesman);

    /**
     * 新增业务员信息
     * 
     * @param appUserSalesman 业务员信息
     * @return 结果
     */
    public int insertAppUserSalesman(AppUserSalesman appUserSalesman);

    /**
     * 修改业务员信息
     * 
     * @param appUserSalesman 业务员信息
     * @return 结果
     */
    public int updateAppUserSalesman(AppUserSalesman appUserSalesman);

    /**
     * 删除业务员信息
     * 
     * @param id 业务员信息主键
     * @return 结果
     */
    public int deleteAppUserSalesmanById(Long id);

    /**
     * 批量删除业务员信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppUserSalesmanByIds(Long[] ids);

    List<AppUserSalesman> selectAppUserSalesmanByUserId(Long id);

    void deleteAppUserSalesmanByUserId(Long id);
}

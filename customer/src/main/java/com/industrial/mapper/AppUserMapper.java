package com.industrial.mapper;

import java.util.List;
import com.industrial.domin.AppUser;

/**
 * 客户管理Mapper接口
 * 
 * @author lsk
 * @date 2022-01-18
 */
public interface AppUserMapper 
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    public AppUser selectAppUserById(Long id);

    /**
     * 查询客户管理列表
     * 
     * @param appUser 客户管理
     * @return 客户管理集合
     */
    public List<AppUser> selectAppUserList(AppUser appUser);

    /**
     * 新增客户管理
     * 
     * @param appUser 客户管理
     * @return 结果
     */
    public int insertAppUser(AppUser appUser);

    /**
     * 修改客户管理
     * 
     * @param appUser 客户管理
     * @return 结果
     */
    public int updateAppUser(AppUser appUser);

    /**
     * 删除客户管理
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteAppUserById(Long id);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppUserByIds(Long[] ids);
}

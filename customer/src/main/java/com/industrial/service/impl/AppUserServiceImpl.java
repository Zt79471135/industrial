package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import com.industrial.domin.AppUser;
import com.industrial.mapper.AppUserMapper;
import com.industrial.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户管理Service业务层处理
 * 
 * @author lsk
 * @date 2022-01-18
 */
@Service
public class AppUserServiceImpl implements IAppUserService
{
    @Autowired
    private AppUserMapper appUserMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public AppUser selectAppUserById(Long id)
    {
        return appUserMapper.selectAppUserById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param appUser 客户管理
     * @return 客户管理
     */
    @Override
    public List<AppUser> selectAppUserList(AppUser appUser)
    {
        return appUserMapper.selectAppUserList(appUser);
    }

    /**
     * 新增客户管理
     * 
     * @param appUser 客户管理
     * @return 结果
     */
    @Override
    public int insertAppUser(AppUser appUser)
    {
        return appUserMapper.insertAppUser(appUser);
    }

    /**
     * 修改客户管理
     * 
     * @param appUser 客户管理
     * @return 结果
     */
    @Override
    public int updateAppUser(AppUser appUser)
    {
        appUser.setUpdateTime(DateUtils.getNowDate());
        return appUserMapper.updateAppUser(appUser);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteAppUserByIds(Long[] ids)
    {
        return appUserMapper.deleteAppUserByIds(ids);
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteAppUserById(Long id)
    {
        return appUserMapper.deleteAppUserById(id);
    }
}

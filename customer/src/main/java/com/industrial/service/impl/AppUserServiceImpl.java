package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import com.industrial.common.utils.uuid.UUID;
import com.industrial.domin.AppUser;
import com.industrial.domin.AppUserAddress;
import com.industrial.domin.AppUserSalesman;
import com.industrial.mapper.AppUserAddressMapper;
import com.industrial.mapper.AppUserMapper;
import com.industrial.mapper.AppUserSalesmanMapper;
import com.industrial.service.IAppUserService;
import io.lettuce.core.api.async.RedisTransactionalAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
    @Autowired
    private AppUserAddressMapper appUserAddressMapper;
    @Autowired
    private AppUserSalesmanMapper appUserSalesmanMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addOrEditAppUserAll(AppUser appUser, List<AppUserAddress> customList, List<AppUserSalesman> saleManList) {
        try{
            appUser.setClientCode("C-"+UUID.randomUUID().toString());
            for (AppUserAddress item:customList) {
                if(item.getIsdefault()==0){
                    appUser.setNickname(item.getName());
                    appUser.setPhoneNum(item.getMobile());
                    break;
                }
            }
            if(appUser.getId()!=null&&appUser.getId()>0){
                if(appUserMapper.updateAppUser(appUser)<=0){ throw new Exception("appUserMapper插入失败");}
            }else{
                if(appUserMapper.insertAppUser(appUser)<=0){ throw new Exception("appUserMapper插入失败");}
            }
            appUserAddressMapper.deleteAppUserAddressByUserId(appUser.getId());
            appUserSalesmanMapper.deleteAppUserSalesmanByUserId(appUser.getId());
            for (AppUserAddress item:customList) {
                item.setUid("CT-"+UUID.randomUUID().toString());
                item.setUserId(appUser.getId());
                if(appUserAddressMapper.insertAppUserAddress(item)<=0){ throw new Exception("appUserAddressMapper插入失败");}
            }
            for (AppUserSalesman item:saleManList) {
                item.setSaleCode("Sale-"+UUID.randomUUID().toString());
                item.setUserId(appUser.getId());
                if(appUserSalesmanMapper.insertAppUserSalesman(item)<=0){ throw new Exception("appUserSalesmanMapper插入失败");}
            }
            return 1;
        }catch (Exception ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }

    }
}

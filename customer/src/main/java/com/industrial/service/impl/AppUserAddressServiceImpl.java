package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppUserAddressMapper;
import com.industrial.domin.AppUserAddress;
import com.industrial.service.IAppUserAddressService;

/**
 * 客户收货地址,用于客户联系人信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-20
 */
@Service
public class AppUserAddressServiceImpl implements IAppUserAddressService 
{
    @Autowired
    private AppUserAddressMapper appUserAddressMapper;

    /**
     * 查询客户收货地址,用于客户联系人信息
     * 
     * @param id 客户收货地址,用于客户联系人信息主键
     * @return 客户收货地址,用于客户联系人信息
     */
    @Override
    public AppUserAddress selectAppUserAddressById(Long id)
    {
        return appUserAddressMapper.selectAppUserAddressById(id);
    }

    /**
     * 查询客户收货地址,用于客户联系人信息列表
     * 
     * @param appUserAddress 客户收货地址,用于客户联系人信息
     * @return 客户收货地址,用于客户联系人信息
     */
    @Override
    public List<AppUserAddress> selectAppUserAddressList(AppUserAddress appUserAddress)
    {
        return appUserAddressMapper.selectAppUserAddressList(appUserAddress);
    }

    /**
     * 新增客户收货地址,用于客户联系人信息
     * 
     * @param appUserAddress 客户收货地址,用于客户联系人信息
     * @return 结果
     */
    @Override
    public int insertAppUserAddress(AppUserAddress appUserAddress)
    {
        return appUserAddressMapper.insertAppUserAddress(appUserAddress);
    }

    /**
     * 修改客户收货地址,用于客户联系人信息
     * 
     * @param appUserAddress 客户收货地址,用于客户联系人信息
     * @return 结果
     */
    @Override
    public int updateAppUserAddress(AppUserAddress appUserAddress)
    {
        appUserAddress.setUpdateTime(DateUtils.getNowDate());
        return appUserAddressMapper.updateAppUserAddress(appUserAddress);
    }

    /**
     * 批量删除客户收货地址,用于客户联系人信息
     * 
     * @param ids 需要删除的客户收货地址,用于客户联系人信息主键
     * @return 结果
     */
    @Override
    public int deleteAppUserAddressByIds(Long[] ids)
    {
        return appUserAddressMapper.deleteAppUserAddressByIds(ids);
    }

    /**
     * 删除客户收货地址,用于客户联系人信息信息
     * 
     * @param id 客户收货地址,用于客户联系人信息主键
     * @return 结果
     */
    @Override
    public int deleteAppUserAddressById(Long id)
    {
        return appUserAddressMapper.deleteAppUserAddressById(id);
    }

    @Override
    public List<AppUserAddress> selectAppUserAddressByUserId(Long id) {
        return appUserAddressMapper.selectAppUserAddressByUserId(id);
    }
}

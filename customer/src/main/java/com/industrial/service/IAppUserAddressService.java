package com.industrial.service;

import java.util.List;
import com.industrial.domin.AppUserAddress;

/**
 * 客户收货地址,用于客户联系人信息Service接口
 * 
 * @author ruoyi
 * @date 2022-01-20
 */
public interface IAppUserAddressService 
{
    /**
     * 查询客户收货地址,用于客户联系人信息
     * 
     * @param id 客户收货地址,用于客户联系人信息主键
     * @return 客户收货地址,用于客户联系人信息
     */
    public AppUserAddress selectAppUserAddressById(Long id);

    /**
     * 查询客户收货地址,用于客户联系人信息列表
     * 
     * @param appUserAddress 客户收货地址,用于客户联系人信息
     * @return 客户收货地址,用于客户联系人信息集合
     */
    public List<AppUserAddress> selectAppUserAddressList(AppUserAddress appUserAddress);

    /**
     * 新增客户收货地址,用于客户联系人信息
     * 
     * @param appUserAddress 客户收货地址,用于客户联系人信息
     * @return 结果
     */
    public int insertAppUserAddress(AppUserAddress appUserAddress);

    /**
     * 修改客户收货地址,用于客户联系人信息
     * 
     * @param appUserAddress 客户收货地址,用于客户联系人信息
     * @return 结果
     */
    public int updateAppUserAddress(AppUserAddress appUserAddress);

    /**
     * 批量删除客户收货地址,用于客户联系人信息
     * 
     * @param ids 需要删除的客户收货地址,用于客户联系人信息主键集合
     * @return 结果
     */
    public int deleteAppUserAddressByIds(Long[] ids);

    /**
     * 删除客户收货地址,用于客户联系人信息信息
     * 
     * @param id 客户收货地址,用于客户联系人信息主键
     * @return 结果
     */
    public int deleteAppUserAddressById(Long id);

    List<AppUserAddress> selectAppUserAddressByUserId(Long id);
}

package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppUserSalesmanMapper;
import com.industrial.domin.AppUserSalesman;
import com.industrial.service.IAppUserSalesmanService;

/**
 * 业务员信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-01-20
 */
@Service
public class AppUserSalesmanServiceImpl implements IAppUserSalesmanService 
{
    @Autowired
    private AppUserSalesmanMapper appUserSalesmanMapper;

    /**
     * 查询业务员信息
     * 
     * @param id 业务员信息主键
     * @return 业务员信息
     */
    @Override
    public AppUserSalesman selectAppUserSalesmanById(Long id)
    {
        return appUserSalesmanMapper.selectAppUserSalesmanById(id);
    }

    /**
     * 查询业务员信息列表
     * 
     * @param appUserSalesman 业务员信息
     * @return 业务员信息
     */
    @Override
    public List<AppUserSalesman> selectAppUserSalesmanList(AppUserSalesman appUserSalesman)
    {
        return appUserSalesmanMapper.selectAppUserSalesmanList(appUserSalesman);
    }

    /**
     * 新增业务员信息
     * 
     * @param appUserSalesman 业务员信息
     * @return 结果
     */
    @Override
    public int insertAppUserSalesman(AppUserSalesman appUserSalesman)
    {
        return appUserSalesmanMapper.insertAppUserSalesman(appUserSalesman);
    }

    /**
     * 修改业务员信息
     * 
     * @param appUserSalesman 业务员信息
     * @return 结果
     */
    @Override
    public int updateAppUserSalesman(AppUserSalesman appUserSalesman)
    {
        appUserSalesman.setUpdateTime(DateUtils.getNowDate());
        return appUserSalesmanMapper.updateAppUserSalesman(appUserSalesman);
    }

    /**
     * 批量删除业务员信息
     * 
     * @param ids 需要删除的业务员信息主键
     * @return 结果
     */
    @Override
    public int deleteAppUserSalesmanByIds(Long[] ids)
    {
        return appUserSalesmanMapper.deleteAppUserSalesmanByIds(ids);
    }

    /**
     * 删除业务员信息信息
     * 
     * @param id 业务员信息主键
     * @return 结果
     */
    @Override
    public int deleteAppUserSalesmanById(Long id)
    {
        return appUserSalesmanMapper.deleteAppUserSalesmanById(id);
    }

    @Override
    public List<AppUserSalesman> selectAppUserSalesmanByUserId(Long id) {
        return appUserSalesmanMapper.selectAppUserSalesmanByUserId(id);
    }
}

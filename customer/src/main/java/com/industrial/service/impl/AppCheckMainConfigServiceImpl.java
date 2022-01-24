package com.industrial.service.impl;

import java.util.List;

import com.industrial.common.dto.CheckConfigDto;
import com.industrial.common.utils.DateUtils;
import com.industrial.common.utils.uuid.UUID;
import com.industrial.domin.AppSubCheckConfig;
import com.industrial.domin.AppUserAddress;
import com.industrial.domin.AppUserSalesman;
import com.industrial.mapper.AppSubCheckConfigMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppCheckMainConfigMapper;
import com.industrial.domin.AppCheckMainConfig;
import com.industrial.service.IAppCheckMainConfigService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 审核设置主Service业务层处理
 * 
 * @author lishenkang
 * @date 2022-01-24
 */
@Service
public class AppCheckMainConfigServiceImpl implements IAppCheckMainConfigService
{
    @Autowired
    private AppCheckMainConfigMapper appCheckMainConfigMapper;
    @Autowired
    private AppSubCheckConfigMapper appSubCheckConfigMapper;

    /**
     * 查询审核设置主
     * 
     * @param id 审核设置主主键
     * @return 审核设置主
     */
    @Override
    public AppCheckMainConfig selectAppCheckMainConfigById(Long id)
    {
        return appCheckMainConfigMapper.selectAppCheckMainConfigById(id);
    }

    /**
     * 查询审核设置主列表
     * 
     * @param appCheckmainconfig 审核设置主
     * @return 审核设置主
     */
    @Override
    public List<AppCheckMainConfig> selectAppCheckMainConfigList(AppCheckMainConfig appCheckmainconfig)
    {
        return appCheckMainConfigMapper.selectAppCheckMainConfigList(appCheckmainconfig);
    }

    /**
     * 新增审核设置主
     * 
     * @param appCheckmainconfig 审核设置主
     * @return 结果
     */
    @Override
    public int insertAppCheckMainConfig(AppCheckMainConfig appCheckmainconfig)
    {
        return appCheckMainConfigMapper.insertAppCheckMainConfig(appCheckmainconfig);
    }

    /**
     * 修改审核设置主
     * 
     * @param appCheckmainconfig 审核设置主
     * @return 结果
     */
    @Override
    public int updateAppCheckMainConfig(AppCheckMainConfig appCheckmainconfig)
    {
        appCheckmainconfig.setUpdateTime(DateUtils.getNowDate());
        return appCheckMainConfigMapper.updateAppCheckMainConfig(appCheckmainconfig);
    }

    /**
     * 批量删除审核设置主
     * 
     * @param ids 需要删除的审核设置主主键
     * @return 结果
     */
    @Override
    public int deleteAppCheckMainConfigByIds(Long[] ids)
    {
        return appCheckMainConfigMapper.deleteAppCheckMainConfigByIds(ids);
    }

    /**
     * 删除审核设置主信息
     * 
     * @param id 审核设置主主键
     * @return 结果
     */
    @Override
    public int deleteAppCheckMainConfigById(Long id)
    {
        return appCheckMainConfigMapper.deleteAppCheckMainConfigById(id);
    }

    @Override
    @Transactional
    public int updateCheckConfigDto(CheckConfigDto checkConfigDto) {
        try{
            AppCheckMainConfig model=new AppCheckMainConfig();
            BeanUtils.copyProperties(checkConfigDto,model);
            if(appCheckMainConfigMapper.updateAppCheckMainConfig(model)<=0){ throw new Exception("appCheckMainConfigMapper插入失败");}
            if(appSubCheckConfigMapper.deleteAppSubCheckConfigByConfigId(model.getId())<=0){ throw new Exception("aappSubCheckConfigMapper删除失败");}
            for (AppSubCheckConfig item:checkConfigDto.subList) {
                if(appSubCheckConfigMapper.insertAppSubCheckConfig(item)<=0){ throw new Exception("appSubCheckConfigMapper插入失败");}
            }
            return 1;
        }catch (Exception ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }
}

package com.industrial.service.impl;

import java.util.Date;
import java.util.List;

import com.industrial.common.dto.CheckConfigDto;
import com.industrial.common.utils.DateUtils;
import com.industrial.common.utils.uuid.UUID;
import com.industrial.domin.*;
import com.industrial.mapper.AppCheckUserMapper;
import com.industrial.mapper.AppSubCheckConfigMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppCheckMainConfigMapper;
import com.industrial.service.IAppCheckMainConfigService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;

/**
 * 审核设置主Service业务层处理
 * 
 * @author lishenkang
 * @date 2022-01-24
 */
@Service
public class AppCheckMainConfigServiceImpl implements IAppCheckMainConfigService
{
    @Resource
    private AppCheckMainConfigMapper appCheckMainConfigMapper;
    @Resource
    private AppSubCheckConfigMapper appSubCheckConfigMapper;
    @Resource
    private AppCheckUserMapper appCheckUserMapper;

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
            appCheckUserMapper.deleteByMainId(model.getId());
            appSubCheckConfigMapper.deleteAppSubCheckConfigByConfigId(model.getId());
            for (AppSubCheckConfig item:checkConfigDto.subList) {
                item.setAddTime(new Date());
                item.setUpdateTime(new Date());
                if(appSubCheckConfigMapper.insertAppSubCheckConfig(item)<=0){ throw new Exception("appSubCheckConfigMapper插入失败");}
                for (String str:item.getAdminList().split(",")) {
                    if(!str.isEmpty()){
                        AppCheckUser temp=new AppCheckUser();
                        temp.setCheckId(item.getId().intValue());
                        temp.setMainId(model.getId().intValue());
                        temp.setUserId(Integer.parseInt(str));
                        if(appCheckUserMapper.insert(temp)<=0){ throw new Exception("appCheckUserMapper插入失败");}
                    }
                }
            }
            return 1;
        }catch (Exception ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            System.out.println(ex);
            return 0;
        }
    }

}

package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import com.industrial.domin.AppOrderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppOrderLogMapper;
import com.industrial.service.IAppOrderLogService;

import javax.annotation.Resource;

/**
 * logService业务层处理
 * 
 * @author lishenkang
 * @date 2022-01-26
 */
@Service
public class AppOrderLogServiceImpl implements IAppOrderLogService 
{
    @Resource
    private AppOrderLogMapper appOrderLogMapper;

    /**
     * 查询log
     * 
     * @param id log主键
     * @return log
     */
    @Override
    public AppOrderLog selectAppOrderLogById(Long id)
    {
        return appOrderLogMapper.selectAppOrderLogById(id);
    }

    /**
     * 查询log列表
     * 
     * @param appOrderLog log
     * @return log
     */
    @Override
    public List<AppOrderLog> selectAppOrderLogList(AppOrderLog appOrderLog)
    {
        return appOrderLogMapper.selectAppOrderLogList(appOrderLog);
    }

    /**
     * 新增log
     * 
     * @param appOrderLog log
     * @return 结果
     */
    @Override
    public int insertAppOrderLog(AppOrderLog appOrderLog)
    {
        appOrderLog.setCreateTime(DateUtils.getNowDate());
        return appOrderLogMapper.insertAppOrderLog(appOrderLog);
    }

    /**
     * 修改log
     * 
     * @param appOrderLog log
     * @return 结果
     */
    @Override
    public int updateAppOrderLog(AppOrderLog appOrderLog)
    {
        appOrderLog.setUpdateTime(DateUtils.getNowDate());
        return appOrderLogMapper.updateAppOrderLog(appOrderLog);
    }

    /**
     * 批量删除log
     * 
     * @param ids 需要删除的log主键
     * @return 结果
     */
    @Override
    public int deleteAppOrderLogByIds(Long[] ids)
    {
        return appOrderLogMapper.deleteAppOrderLogByIds(ids);
    }

    /**
     * 删除log信息
     * 
     * @param id log主键
     * @return 结果
     */
    @Override
    public int deleteAppOrderLogById(Long id)
    {
        return appOrderLogMapper.deleteAppOrderLogById(id);
    }
}

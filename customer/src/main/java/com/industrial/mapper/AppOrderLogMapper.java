package com.industrial.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.domin.AppOrderLog;
import org.apache.ibatis.annotations.Param;

/**
 * logMapper接口
 * 
 * @author lishenkang
 * @date 2022-01-26
 */
public interface AppOrderLogMapper extends BaseMapper<AppOrderLog>
{
    /**
     * 查询log
     * 
     * @param id log主键
     * @return log
     */
    public AppOrderLog selectAppOrderLogById(Long id);

    /**
     * 查询log列表
     * 
     * @param appOrderLog log
     * @return log集合
     */
    public List<AppOrderLog> selectAppOrderLogList(AppOrderLog appOrderLog);

    /**
     * 新增log
     * 
     * @param appOrderLog log
     * @return 结果
     */
    public int insertAppOrderLog(AppOrderLog appOrderLog);

    /**
     * 修改log
     * 
     * @param appOrderLog log
     * @return 结果
     */
    public int updateAppOrderLog(AppOrderLog appOrderLog);

    /**
     * 删除log
     * 
     * @param id log主键
     * @return 结果
     */
    public int deleteAppOrderLogById(Long id);

    /**
     * 批量删除log
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppOrderLogByIds(Long[] ids);

}

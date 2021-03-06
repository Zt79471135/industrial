package com.industrial.service;

import java.util.List;

import com.industrial.common.core.domain.TreeSelect;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.dto.CategoryDto;
import com.industrial.common.vo.updateTypeVo;
import com.industrial.common.vo.UpdateDeletedVo;
import com.industrial.domin.AppProductCategory;

/**
 * 商品分类Service接口
 * 
 * @author chenjh
 * @date 2022-01-19
 */
public interface IAppProductCategoryService 
{
    /**
     * 查询商品分类
     * 
     * @param id 商品分类主键
     * @return 商品分类
     */
    public AppProductCategory selectAppProductCategoryById(Long id);

    /**
     * 查询商品分类列表
     * 
     * @param appProductCategory 分类名
     * @return 商品分类集合
     */
    public List<AppProductCategory> selectAppProductCategoryList(AppProductCategory appProductCategory);

    /**
     * 新增商品分类
     * 
     * @param appProductCategory 商品分类
     * @return 结果
     */
    public int insertAppProductCategory(AppProductCategory appProductCategory);

    /**
     * 修改商品分类
     * 
     * @param appProductCategory 商品分类
     * @return 结果
     */
    public int updateAppProductCategory(AppProductCategory appProductCategory);

    /**
     * 根据ID更新所属分类
     *
     * @param typeVo
     * @return 结果
     */
     int updateType(updateTypeVo typeVo);

    /**
     * 根据ID更新启用状态
     *
     * @param deletedVo
     * @return 结果
     */
     int updateDeleted(UpdateDeletedVo deletedVo);

    /**
     * 修改启用状态
     *
     * @param category
     * @return 结果
     */
   int changeStatus(AppProductCategory category);

    /**
     * 导入用户数据
     *
     * @param list 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importData(List<AppProductCategory> list, Boolean isUpdateSupport, String operName);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的商品分类主键集合
     * @return 结果
     */
    public int deleteAppProductCategoryByIds(Long[] ids);

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteAppProductCategoryById(Long id);


}

package com.industrial.service;

import java.util.List;

import com.industrial.common.dto.CategoryDto;
import com.industrial.entity.AppProductCategory;

/**
 * 商品分类Service接口
 * 
 * @author chenjh
 * @date 2022-01-17
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
     * @param categoryName 分类名 ,categoryCode 分类编号
     * @return 商品分类集合
     */
    public List<CategoryDto> selectAppProductCategoryList(String categoryName, String categoryCode);

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

package com.industrial.service.impl;

import java.util.List;
import com.industrial.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppProductCategoryMapper;
import com.industrial.entity.AppProductCategory;
import com.industrial.service.IAppProductCategoryService;

/**
 * 商品分类Service业务层处理
 * 
 * @author chenjh
 * @date 2022-01-17
 */
@Service
public class AppProductCategoryServiceImpl implements IAppProductCategoryService 
{
    @Autowired
    private AppProductCategoryMapper appProductCategoryMapper;

    /**
     * 查询商品分类
     * 
     * @param id 商品分类主键
     * @return 商品分类
     */
    @Override
    public AppProductCategory selectAppProductCategoryById(Long id)
    {
        return appProductCategoryMapper.selectAppProductCategoryById(id);
    }

    /**
     * 查询商品分类列表
     * 
     * @param appProductCategory 商品分类
     * @return 商品分类
     */
    @Override
    public List<AppProductCategory> selectAppProductCategoryList(AppProductCategory appProductCategory)
    {
        return appProductCategoryMapper.selectAppProductCategoryList(appProductCategory);
    }

    /**
     * 新增商品分类
     * 
     * @param appProductCategory 商品分类
     * @return 结果
     */
    @Override
    public int insertAppProductCategory(AppProductCategory appProductCategory)
    {
        return appProductCategoryMapper.insertAppProductCategory(appProductCategory);
    }

    /**
     * 修改商品分类
     * 
     * @param appProductCategory 商品分类
     * @return 结果
     */
    @Override
    public int updateAppProductCategory(AppProductCategory appProductCategory)
    {
        appProductCategory.setUpdateTime(DateUtils.getNowDate());
        return appProductCategoryMapper.updateAppProductCategory(appProductCategory);
    }

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的商品分类主键
     * @return 结果
     */
    @Override
    public int deleteAppProductCategoryByIds(Long[] ids)
    {
        return appProductCategoryMapper.deleteAppProductCategoryByIds(ids);
    }

    /**
     * 删除商品分类信息
     * 
     * @param id 商品分类主键
     * @return 结果
     */
    @Override
    public int deleteAppProductCategoryById(Long id)
    {
        return appProductCategoryMapper.deleteAppProductCategoryById(id);
    }
}

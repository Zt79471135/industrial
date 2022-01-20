package com.industrial.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.industrial.common.dto.CategoryDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.utils.DateUtils;
import com.industrial.entity.AppProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppProductCategoryMapper;
import com.industrial.entity.AppProductCategory;
import com.industrial.service.IAppProductCategoryService;

import javax.annotation.Resource;

/**
 * 商品分类Service业务层处理
 * 
 * @author chenjh
 * @date 2022-01-19
 */
@Service
public class AppProductCategoryServiceImpl implements IAppProductCategoryService 
{
    @Resource
    private AppProductCategoryMapper appProductCategoryMapper;

    private static CategoryDto apply(AppProductCategory category) {
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto);
        return categoryDto;
    }

    /**
     * 查询商品分类
     * 
     * @param id 商品分类主键
     * @return 商品分类
     */
    @Override
    public AppProductCategory selectAppProductCategoryById(Long id)
    {
        //return appProductCategoryMapper.selectById(id);
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
        //return appProductCategoryMapper.insert(appProductCategory);
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
        //return appProductCategoryMapper.updateById(appProductCategory);
    }

    /**
     * 根据ID更新所属分类
     *
     * @param ids,productType
     * @return 结果
     */
    @Override
    public int updateTypeById(Integer productType,Long[] ids)
    {
        //return appProductCategoryMapper.updateTypeById(productType,ids);
        int ret = 0;
        for (Long categoryId : ids) {
            ret = appProductCategoryMapper.updateTypeById(productType,categoryId);
        }
        return ret;
    }

    /**
     * 根据ID更新启用状态
     *
     * @param ids,deleted
     * @return 结果
     */
    @Override
    public int updateDeletedById(Integer deleted,Long[] ids)
    {
        //return appProductCategoryMapper.updateDeletedById(deleted,ids);
        int ret = 0;
        for (Long categoryId : ids) {
            ret = appProductCategoryMapper.updateDeletedById(deleted,categoryId);
        }
        return ret;


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
//        int ret = 0;
//        for (Long Id : ids) {
//            ret = appProductCategoryMapper.deleteById(Id);
//        }
//        return ret;
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
        //return appProductCategoryMapper.deleteById(id);
    }

}

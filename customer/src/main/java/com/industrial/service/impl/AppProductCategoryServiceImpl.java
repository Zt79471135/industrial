package com.industrial.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.industrial.common.dto.CategoryDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppProductCategoryMapper;
import com.industrial.domin.AppProductCategory;
import com.industrial.service.IAppProductCategoryService;

import javax.annotation.Resource;

/**
 * 商品分类Service业务层处理
 *
 * @author chenjh
 * @date 2022-01-19
 */
@Service
public class AppProductCategoryServiceImpl implements IAppProductCategoryService {
    @Resource
    private AppProductCategoryMapper productCategoryMapper;

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
    public AppProductCategory selectAppProductCategoryById(Long id) {
        return productCategoryMapper.selectById(id);
    }

    /**
     * 查询商品分类列表
     *
     * @param categoryName
     * @param categoryCode
     * @return 商品分类
     */
    @Override
    public List<CategoryDto> selectAppProductCategoryList(String categoryName, String categoryCode) {
        QueryWrapper<AppProductCategory> qw = new QueryWrapper<>();
        if (!"".equals(categoryName)) {
            qw.lambda().eq(AppProductCategory::getCategoryName, categoryName);
        }
        if (!"".equals(categoryCode)) {
            qw.lambda().like(AppProductCategory::getCategoryCode, categoryCode);
        }
        List<AppProductCategory> categoryList = productCategoryMapper.selectList(qw);
        return categoryList.stream().map(AppProductCategoryServiceImpl::apply
        ).collect(Collectors.toList());
    }

    /**
     * 新增商品分类
     *
     * @param appProductCategory 商品分类
     * @return 结果
     */
    @Override
    public int insertAppProductCategory(AppProductCategory appProductCategory) {
        return productCategoryMapper.insert(appProductCategory);
    }

    /**
     * 修改商品分类
     *
     * @param appProductCategory 商品分类
     * @return 结果
     */
    @Override
    public int updateAppProductCategory(AppProductCategory appProductCategory) {
        appProductCategory.setUpdateTime(DateUtils.getNowDate());
        return productCategoryMapper.updateById(appProductCategory);
    }

    /**
     * 批量删除商品分类
     *
     * @param ids 需要删除的商品分类主键
     * @return 结果
     */
    @Override
    public int deleteAppProductCategoryByIds(Long[] ids) {
        int ret = 0;
        for (Long Id : ids) {
            ret = productCategoryMapper.deleteById(Id);
        }
        return ret;
    }

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类主键
     * @return 结果
     */
    @Override
    public int deleteAppProductCategoryById(Long id) {
        return productCategoryMapper.deleteById(id);
    }
}

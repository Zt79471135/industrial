package com.industrial.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.industrial.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.industrial.common.core.domain.TreeSelect;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.dto.CategoryDto;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.utils.DateUtils;
import com.industrial.common.utils.SecurityUtils;
import com.industrial.common.utils.StringUtils;
import com.industrial.common.utils.bean.BeanValidators;
import com.industrial.common.vo.updateTypeVo;
import com.industrial.common.vo.UpdateDeletedVo;
import com.industrial.domin.AppProductCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.industrial.mapper.AppProductCategoryMapper;
import com.industrial.service.IAppProductCategoryService;

import javax.annotation.Resource;
import javax.validation.Validator;

/**
 * 商品分类Service业务层处理
 * 
 * @author chenjh
 * @date 2022-01-19
 */
@Service
public class AppProductCategoryServiceImpl implements IAppProductCategoryService 
{
    private static final Logger log = LoggerFactory.getLogger(AppProductCategoryServiceImpl.class);
    @Autowired
    protected Validator validator;

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
     * @param typeVo
     * @return 结果
     */
    @Override
    public int updateType(updateTypeVo typeVo)
    {
        //return appProductCategoryMapper.updateTypeById(productType,ids);
        Long[] ids = typeVo.getIds();
        Integer productType = typeVo.getProductType();
        int ret = 0;
        for (Long categoryId : ids) {
            ret = appProductCategoryMapper.updateTypeById(productType,categoryId);
        }
        return ret;
    }

    /**
     * 根据ID更新启用状态
     *
     * @param deletedVo
     * @return 结果
     */
    @Override
    public int updateDeleted(UpdateDeletedVo deletedVo)
    {
        //return appProductCategoryMapper.updateDeletedById(deleted,ids);
        Long[] ids = deletedVo.getIds();
        Integer enabled = deletedVo.getEnabled();
        int ret = 0;
        for (Long categoryId : ids) {
            ret = appProductCategoryMapper.updateEnabled(enabled,categoryId);
        }
        return ret;
    }

    /**
     * 修改启用状态
     *
     * @param category
     * @return 结果
     */
    @Override
    public int changeStatus(AppProductCategory category)
    {
        Integer deleted = (int)category.getDeleted();
        Long categoryId = (long)category.getId();
        return appProductCategoryMapper.updateEnabled(deleted,categoryId);
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
        //return appProductCategoryMapper.deleteById(id);
    }

    /**
     * 导入用户数据
     *
     * @param list 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importData(List<AppProductCategory> list, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (AppProductCategory category : list)
        {
            try
            {
                // 验证是否存在这个名称
                AppProductCategory u = appProductCategoryMapper.selectDataByName(category.getCategoryName());
                if (StringUtils.isNull(u))
                {
                    BeanValidators.validateWithException(validator, category);

                    //category.setCreateBy(operName);
                    appProductCategoryMapper.insertAppProductCategory(category);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、分类名称 " + category.getCategoryName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, category);
                   //category.setUpdateBy(operName);
                    appProductCategoryMapper.updateAppProductCategory(category);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、分类名称 " + category.getCategoryName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、分类名称 " + category.getCategoryName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、分类名称 " + category.getCategoryName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}


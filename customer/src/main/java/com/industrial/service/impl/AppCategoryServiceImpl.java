package com.industrial.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.industrial.common.constant.UserConstants;
import com.industrial.common.core.text.Convert;
import com.industrial.domin.TreeSelect;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.common.utils.StringUtils;
import com.industrial.common.utils.bean.BeanValidators;
import com.industrial.common.vo.UpdateDeletedVo;
import com.industrial.common.vo.updateTypeVo;
import com.industrial.domin.AppCategory;
import com.industrial.mapper.AppCategoryMapper;
import com.industrial.service.IAppCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.validation.Validator;



/**
 * 描述:
 * 日期：2022-01-24
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
@Service
public class AppCategoryServiceImpl implements IAppCategoryService
{
    private static final Logger log = LoggerFactory.getLogger(AppCategoryServiceImpl.class);
    @Autowired
    protected Validator validator;

    @Autowired
    private AppCategoryMapper appCategoryMapper;

    /**
     * 查询商品分类
     *
     * @param id 商品分类主键
     * @return 商品分类
     */
    @Override
    public AppCategory selectAppCategoryById(Long id)
    {
        return appCategoryMapper.selectAppCategoryById(id);
    }

    /**
     * 查询商品分类列表
     *
     * @param appCategory 商品分类
     * @return 商品分类
     */
    @Override
    public List<AppCategory> selectAppCategoryList(AppCategory appCategory)
    {
        return appCategoryMapper.selectAppCategoryList(appCategory);
    }

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long deptId)
    {
        int result = appCategoryMapper.hasChildByDeptId(deptId);
        return result > 0;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDataExist(Long deptId)
    {
        int result = appCategoryMapper.checkDataExist(deptId);
        return result > 0;
    }

    /**
     * 新增商品分类
     *
     * @param appCategory 商品分类
     * @return 结果
     */
    @Override
    public int insertAppCategory(AppCategory appCategory)
    {
        AppCategory info = appCategoryMapper.selectAppCategoryById(appCategory.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getEnabled()))
        {
            throw new ServiceException("商品分类停用，不允许新增");
        }
        appCategory.setAncestors(info.getAncestors() + "," + appCategory.getParentId());
        return appCategoryMapper.insertAppCategory(appCategory);
    }

    /**
     * 修改商品分类
     *
     * @param appCategory 商品分类
     * @return 结果
     */
    @Override
    public int updateAppCategory(AppCategory appCategory)
    {
        AppCategory newParentDept = appCategoryMapper.selectAppCategoryById(appCategory.getParentId());
        AppCategory oldDept = appCategoryMapper.selectAppCategoryById(appCategory.getId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getId();
            String oldAncestors = oldDept.getAncestors();
            appCategory.setAncestors(newAncestors);
            updateDeptChildren(appCategory.getId(), newAncestors, oldAncestors);
        }
        int result = appCategoryMapper.updateAppCategory(appCategory);
        if (UserConstants.DEPT_NORMAL.equals(appCategory.getEnabled()) && StringUtils.isNotEmpty(appCategory.getAncestors())
                && !StringUtils.equals("0", appCategory.getAncestors()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatusNormal(appCategory);
        }
        return result;
        //appCategory.setUpdateTime(DateUtils.getNowDate());
        //return appCategoryMapper.updateAppCategory(appCategory);
    }

    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatusNormal(AppCategory dept)
    {
        String ancestors = dept.getAncestors();
        Long[] deptIds = Convert.toLongArray(ancestors);
        appCategoryMapper.updateDeptStatusNormal(deptIds);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<AppCategory> children = appCategoryMapper.selectChildrenDeptById(deptId);
        for (AppCategory child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            appCategoryMapper.updateDeptChildren(children);
        }
    }

    /**
     * 批量删除商品分类
     *
     * @param ids 需要删除的商品分类主键
     * @return 结果
     */
    @Override
    public int deleteAppCategoryByIds(Long[] ids)
    {
        return appCategoryMapper.deleteAppCategoryByIds(ids);
    }

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类主键
     * @return 结果
     */
    @Override
    public int deleteAppCategory(Long id)
    {
        return appCategoryMapper.deleteAppCategoryById(id);
    }

    /**
     * 修改启用状态
     *
     * @param category
     * @return 结果
     */
    @Override
    public int changeStatus(AppCategory category)
    {
        Integer enabled = (int)category.getEnabled();
        Long categoryId = (long)category.getId();
        return appCategoryMapper.updateEnabled(enabled,categoryId);
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
    public String importData(List<AppCategory> list, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        for (AppCategory category : list)
        {
            try
            {
                // 验证是否存在这个名称
                AppCategory u = appCategoryMapper.selectDataByName(category.getCategoryName());
                if (StringUtils.isNull(u))
                {
                    BeanValidators.validateWithException(validator, category);

                    //category.setCreateBy(operName);
                    appCategoryMapper.insertAppCategory(category);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、分类名称 " + category.getCategoryName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, category);
                    //category.setUpdateBy(operName);
                    appCategoryMapper.updateAppCategory(category);
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

    /**
     * 根据ID更新所属分类
     *
     * @param typeVo
     * @return 结果
     */
    @Override
    public int updateType(updateTypeVo typeVo)
    {
        Long[] ids = typeVo.getIds();
        Integer productType = typeVo.getProductType();
        int ret = 0;
        for (Long categoryId : ids) {
            ret = appCategoryMapper.updateType(productType,categoryId);
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
        Long[] ids = deletedVo.getIds();
        Integer enabled = deletedVo.getEnabled();
        int ret = 0;
        for (Long categoryId : ids) {
            ret = appCategoryMapper.updateEnabled(enabled,categoryId);
        }
        return ret;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<AppCategory> buildDeptTree(List<AppCategory> depts)
    {
        List<AppCategory> returnList = new ArrayList<AppCategory>();
        List<Long> tempList = new ArrayList<Long>();
        for (AppCategory dept : depts)
        {
            tempList.add(dept.getId());
        }
        for (Iterator<AppCategory> iterator = depts.iterator(); iterator.hasNext();)
        {
            AppCategory dept = (AppCategory) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<AppCategory> depts)
    {
        List<AppCategory> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<AppCategory> list, AppCategory t)
    {
        // 得到子节点列表
        List<AppCategory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (AppCategory tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<AppCategory> getChildList(List<AppCategory> list, AppCategory t)
    {
        List<AppCategory> tlist = new ArrayList<AppCategory>();
        Iterator<AppCategory> it = list.iterator();
        while (it.hasNext())
        {
            AppCategory n = (AppCategory) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<AppCategory> list, AppCategory t)
    {
        return getChildList(list, t).size() > 0;
    }

}
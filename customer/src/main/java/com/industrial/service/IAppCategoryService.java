package com.industrial.service;

import java.util.List;

import com.industrial.domin.TreeSelect;
import com.industrial.common.vo.UpdateDeletedVo;
import com.industrial.common.vo.updateTypeVo;
import com.industrial.domin.AppCategory;

/**
 * 描述:
 * 日期：2022-01-24
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
public interface IAppCategoryService
{
    /**
     * 查询商品分类
     *
     * @param id 商品分类主键
     * @return 商品分类
     */
    public AppCategory selectAppCategoryById(Long id);

    /**
     * 查询商品分类列表
     *
     * @param appCategory 商品分类
     * @return 商品分类集合
     */
    public List<AppCategory> selectAppCategoryList(AppCategory appCategory);

    /**
     * 是否存在子节点
     *
     * @param deptId ID
     * @return 结果
     */
    public boolean hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在商品
     *
     * @param deptId ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDataExist(Long deptId);

    /**
     * 新增商品分类
     *
     * @param appCategory 商品分类
     * @return 结果
     */
    public int insertAppCategory(AppCategory appCategory);

    /**
     * 修改商品分类
     *
     * @param appCategory 商品分类
     * @return 结果
     */
    public int updateAppCategory(AppCategory appCategory);

    /**
     * 批量删除商品分类
     *
     * @param ids 需要删除的商品分类主键集合
     * @return 结果
     */
    public int deleteAppCategoryByIds(Long[] ids);

    /**
     * 删除商品分类信息
     *
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteAppCategory(Long id);

    /**
     * 修改启用状态
     *
     * @param category
     * @return 结果
     */
    int changeStatus(AppCategory category);

    /**
     * 导入数据
     *
     * @param list 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importData(List<AppCategory> list, Boolean isUpdateSupport, String operName);

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
     * 构建前端所需要树结构
     *
     * @param depts 分类列表
     * @return 树结构列表
     */
    public List<AppCategory> buildDeptTree(List<AppCategory> depts);

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 分类列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildDeptTreeSelect(List<AppCategory> depts);

    /**
     * 校验名称是否唯一
     *
     * @param dept 分类信息
     * @return 结果
     */
    public String checkDeptNameUnique(AppCategory dept);

    /**
     * 根据ID查询所有子分类（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenDeptById(Long deptId);

}
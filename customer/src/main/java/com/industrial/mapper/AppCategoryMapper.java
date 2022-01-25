package com.industrial.mapper;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.domin.AppCategory;
import org.apache.ibatis.annotations.Param;

/**
 * 描述:
 * 日期：2022-01-24
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
public interface AppCategoryMapper
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
     * 根据ID查询所有子分类
     *
     * @param deptId 部门ID
     * @return 部门列表
     */
    public List<AppCategory> selectChildrenDeptById(Long deptId);

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在商品
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDataExist(Long deptId);

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
     * 修改商品分类正常状态
     *
     * @param deptIds 部门ID组
     */
    public void updateDeptStatusNormal(Long[] deptIds);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<AppCategory> depts);

    /**
     * 删除商品分类
     *
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteAppCategoryById(Long id);

    /**
     * 批量删除商品分类
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppCategoryByIds(Long[] ids);

    /**
     * 根据ID更新启用状态
     * @param categoryId
     * @param enabled
     * @return
     */
    int updateEnabled(@Param("enabled") Integer enabled,@Param("categoryId") Long categoryId);

    /**
     * 根据ID更新所属分类
     * @param categoryId
     * @param productType
     * @return
     */
    int updateType( @Param("productType") Integer productType,@Param("categoryId") Long categoryId);

    /**
     * 通过名称查询数据
     *
     * @param keyName 名称
     * @return 用户对象信息
     */
    public AppCategory selectDataByName(String keyName);
}
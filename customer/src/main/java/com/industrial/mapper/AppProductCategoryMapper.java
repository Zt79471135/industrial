package com.industrial.mapper;

import java.util.List;
import com.industrial.entity.AppProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
/**
 * 商品分类Mapper接口
 * 
 * @author chenjh
 * @date 2022-01-17
 */
public interface AppProductCategoryMapper 
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
     * @param appProductCategory 商品分类
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
     * 删除商品分类
     * 
     * @param id 商品分类主键
     * @return 结果
     */
    public int deleteAppProductCategoryById(Long id);

    /**
     * 批量删除商品分类
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAppProductCategoryByIds(Long[] ids);
}

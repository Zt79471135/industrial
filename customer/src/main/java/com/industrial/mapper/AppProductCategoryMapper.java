package com.industrial.mapper;
import java.util.List;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.common.dto.CategoryDto;
import com.industrial.entity.AppProductCategory;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenjh
 * @date 2022年01月19日 10:22
 */
public interface AppProductCategoryMapper extends BaseMapper<AppProductCategory>
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
         * 根据ID更新所属分类
         * @param categoryId
         * @param productType
         * @return
         */
        int updateTypeById( @Param("productType") Integer productType,@Param("categoryId") Long categoryId);

        /**
         * 根据ID更新启用状态
         * @param categoryId
         * @param deleted
         * @return
         */
        int updateDeletedById(@Param("deleted") Integer deleted,@Param("categoryId") Long categoryId);

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
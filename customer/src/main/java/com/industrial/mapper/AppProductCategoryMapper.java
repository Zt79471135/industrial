package com.industrial.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.domin.AppProductCategory;

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

}
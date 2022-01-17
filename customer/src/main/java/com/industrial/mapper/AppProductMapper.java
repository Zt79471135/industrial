package com.industrial.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.industrial.entity.AppProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月13日 16:39
 */
public interface AppProductMapper extends BaseMapper<AppProduct> {

    /**
     * 根据商品ID更新商品数量
     * @param productId
     * @param stock
     * @return
     */
    int updateStockByProductId(@Param("productId") Integer productId, @Param("stock") Integer stock);
    /**
     * 根据关键字查找商品
     * @param keyword
     * @return
     */
    List<AppProduct> selectProductByKeyword(@Param("keyword") String keyword);
    /**
     * 根据订单ID查询商品详情
     * @param orderId
     * @return
     */
    List<AppProduct> selectProductByOrderId(@Param("orderId") Integer orderId);
}
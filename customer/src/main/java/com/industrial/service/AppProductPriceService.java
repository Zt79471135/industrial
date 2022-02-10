package com.industrial.service;

import com.industrial.common.dto.ProductPriceDto;
import com.industrial.domin.AppProductPrice;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月26日 14:56
 */
public interface AppProductPriceService {
    /**
     * 查询商品报价
     * @return
     */
    List<ProductPriceDto> selectList(List<Integer> productPriceId);


    /**
     * 批量修改商品报价
     * @param productPriceList
     * @return
     */
    boolean update(List<AppProductPrice> productPriceList);

    /**
     * 根据单个活动ID查询商品报价
     * @param activityId
     * @return
     */
    List<ProductPriceDto> selectListByActivityId(List<Integer> activityId);
}

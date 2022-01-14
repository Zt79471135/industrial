package com.industrial.system.service;

import com.industrial.common.core.domain.dto.ProductDto;
import com.industrial.system.domain.vo.ProductVo;

import java.util.List;

/**
 * @author zhu
 * @date 2021年12月24日 9:18
 */
public interface AppProductService {
    /**
     * 商品详情
     * 通过商品ID获得商品
     * @param productId
     * @return
     */
    ProductDto selectProductById(Integer productId);

    /**
     * 上架商品
     * @param productVo
     * @return
     */
    boolean insert(ProductVo productVo);

    /**
     * 下架商品
     * @param productId
     * @return
     */
    boolean deleteProductById(Integer productId);

    /**
     * 商品修改
     * @param productVo
     * @param status
     * @return
     */
    boolean update(ProductVo productVo,Byte status);

    /**
     * 通过关键字查找商品
     * @param keyword
     * @return
     */
    List<ProductDto> selectProductByKeyword(String keyword);

    /**
     * 查看待审核商品
     * @param status
     * @return
     */
    List<ProductDto> selectList(Byte status);
}

package com.industrial.service;

import com.industrial.common.dto.ProductDto;
import com.industrial.common.vo.CheckVo;
import com.industrial.common.vo.ProductVo;

import java.util.List;

/**
 * @author zhu
 * @date 2021年12月24日 9:18
 */
public interface AppProductService {
    /**
     * 根据商品更改商品状态
     * @param status
     * @param productId
     * @return
     */
    boolean changeStatus(int status, Integer productId);
    /**
     * 商品详情
     * 通过商品ID获得商品
     * @param productId
     * @return
     */
    ProductDto selectProductById(Integer productId);

    /**
     * 根据商品状态检索商品
     * @param status
     * @return
     */
    List<ProductDto> selectProductByStatus(int status);

    /**
     * 商品上架
     * @param productVo
     * @return
     */
    boolean insert(ProductVo productVo);

    /**
     * 移除商品
     * @param productId
     * @return
     */
    boolean remove(Integer productId);

    /**
     * 更新商品
     * @param productVo
     * @return
     */
    boolean update(ProductVo productVo);

    /**
     * 根据分类查询
     * @param categoryId
     * @param productName
     * @return
     */
    List<ProductDto> selectProductByCategoryId(Integer categoryId,String productName,int status);


}

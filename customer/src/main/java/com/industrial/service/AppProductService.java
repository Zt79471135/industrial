package com.industrial.service;

import com.industrial.common.dto.ProductDto;
import com.industrial.common.pojo.ProductExcel;
import com.industrial.common.vo.CheckVo;
import com.industrial.common.vo.ProductVo;
import com.industrial.domin.AppProduct;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 商品下架
     * @param ids
     * @param soldOutStatus
     * @return
     */
    boolean soldOut(List<Integer> ids, byte soldOutStatus);
    /**
     * 根据分类查询
     * @param categoryId
     * @param productName
     * @param status
     * @return
     */
    List<ProductDto> selectProductByCategoryId(Integer categoryId,String productName,int status);

    /**
     * 根据id上架商品
     * @param ids
     * @param status
     * @return
     */
    boolean putaway(List<Integer> ids,byte status);

    /**
     * 通过商品信息查询商品
     * @param productVo
     * @return
     */
    List<ProductExcel> selectProductExcelList(ProductVo productVo);
    /**
     * 导入用户数据
     *
     * @param productList 数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    String importData(List<AppProduct> productList, Boolean isUpdateSupport, String operName);

    /**
     * 商品启用与禁用
     * @param productId
     * @param enable
     * @return
     */
    boolean changeEnabled(Integer productId,int enable);
}

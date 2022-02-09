package com.industrial.common.dto;


import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhu
 * @date 2022年02月09日 13:56
 */
@Data
public class ProductPriceDto {
    /**
     * 商品报价表ID
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品编号
     */
    private String number;
    /**
     * 商品价格
     */
    private String price;
    /**
     * 销售单价
     */
    private BigDecimal sellingPrice;

    /**
     * 数量
     */
    private Integer amount;
    /**
     * 折扣
     */
    private Integer discount;
    /**
     * 总价
     */
    private BigDecimal totalPrices;
    /**
     * 规格
     */
    private String specifica;
    /**
     * 礼品
     */
    private String gift;
    /**
     * 备注
     */
    private String remark;

}

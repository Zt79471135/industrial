package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年01月26日 14:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_product_price")
public class AppProductPrice {
    /**
     * 商品报价表ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 商品表ID
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 销售单价
     */
    @TableField(value = "selling_price")
    private BigDecimal sellingPrice;

    /**
     * 数量
     */
    @TableField(value = "amount")
    private Integer amount;

    /**
     * 折扣
     */
    @TableField(value = "discount")
    private Integer discount;

    /**
     * 总价
     */
    @TableField(value = "total_prices")
    private BigDecimal totalPrices;

    /**
     * 礼品
     */
    @TableField(value = "gift")
    private String gift;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_SELLING_PRICE = "selling_price";

    public static final String COL_AMOUNT = "amount";

    public static final String COL_DISCOUNT = "discount";

    public static final String COL_TOTAL_PRICES = "total_prices";

    public static final String COL_GIFT = "gift";

    public static final String COL_REMARK = "remark";
}
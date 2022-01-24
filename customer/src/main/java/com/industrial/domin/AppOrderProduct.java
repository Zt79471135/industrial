package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年01月20日 15:04
 */
/**
    * 订单与商品关联表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_order_product")
public class AppOrderProduct {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 外键用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 联合主键,订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 联合主键,商品ID
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 商品数量
     */
    @TableField(value = "`count`")
    private Integer count;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 0表示删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_COUNT = "count";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}
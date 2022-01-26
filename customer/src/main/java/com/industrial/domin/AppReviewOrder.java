package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年01月26日 14:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_review_order")
public class AppReviewOrder {
    /**
     * 评论与订单关联表主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 评论表ID
     */
    @TableField(value = "review_id")
    private Integer reviewId;

    /**
     * 订单表ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

    public static final String COL_ID = "id";

    public static final String COL_REVIEW_ID = "review_id";

    public static final String COL_ORDER_ID = "order_id";
}
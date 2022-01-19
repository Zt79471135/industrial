package com.industrial.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年01月19日 9:44
 */
/**
    * 订单表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_order")
public class AppOrder {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 外键,用户,ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 指定的项目经理ID
     */
    @TableField(value = "user_manager")
    private Integer userManager;

    /**
     * 指定的工程师ID
     */
    @TableField(value = "user_engineer")
    private Integer userEngineer;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 订单总额
     */
    @TableField(value = "total_price")
    private BigDecimal totalPrice;

    /**
     * 订单状态（1、未提交，2：审批中，3：已通过，4：已撤消，5：被否决，6：被驳回）
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 订单快照图片
     */
    @TableField(value = "snap_img")
    private String snapImg;

    /**
     * 订单快照名称
     */
    @TableField(value = "snap_nam")
    private String snapNam;

    /**
     * 总数量
     */
    @TableField(value = "total_count")
    private Integer totalCount;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 订单其他信息快照
     */
    @TableField(value = "snap_items")
    private String snapItems;

    /**
     * 地址快照
     */
    @TableField(value = "snap_address")
    private String snapAddress;

    /**
     * 订单微信支付的预订单ID
     */
    @TableField(value = "prepay_id")
    private String prepayId;

    /**
     * 0为删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_MANAGER = "user_manager";

    public static final String COL_USER_ENGINEER = "user_engineer";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_TOTAL_PRICE = "total_price";

    public static final String COL_STATUS = "status";

    public static final String COL_SNAP_IMG = "snap_img";

    public static final String COL_SNAP_NAM = "snap_nam";

    public static final String COL_TOTAL_COUNT = "total_count";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_SNAP_ITEMS = "snap_items";

    public static final String COL_SNAP_ADDRESS = "snap_address";

    public static final String COL_PREPAY_ID = "prepay_id";

    public static final String COL_DELETED = "deleted";
}
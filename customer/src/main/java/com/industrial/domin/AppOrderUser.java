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
 * @date 2022年01月20日 14:52
 */
/**
    * 活动与参与人员表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_order_user")
public class AppOrderUser {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 订单ID
     */
    @TableField(value = "order_id")
    private Integer orderId;

    /**
     * 通知人员ID（关联sys_user表user_id)
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 标志（0：禁用，1：启用)
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}
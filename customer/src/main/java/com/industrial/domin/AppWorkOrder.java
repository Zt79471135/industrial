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
 * @date 2022年02月09日 16:16
 */

/**
 * 工单主表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_work_order")
public class AppWorkOrder {
    /**
     * 工单主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 工单编号
     */
    @TableField(value = "`number`")
    private String number;

    /**
     * 工单标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 工单类型
     */
    @TableField(value = "`type`")
    private Integer type;

    /**
     * 发起人
     */
    @TableField(value = "start_user")
    private Integer startUser;

    /**
     * 处理人员
     */
    @TableField(value = "handling_user")
    private Integer handlingUser;

    /**
     * 发起时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 工单状态
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 紧急程度
     */
    @TableField(value = "extent")
    private Byte extent;

    /**
     * 0表删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_NUMBER = "number";

    public static final String COL_TITLE = "title";

    public static final String COL_TYPE = "type";

    public static final String COL_START_USER = "start_user";

    public static final String COL_HANDLING_USER = "handling_user";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";

    public static final String COL_EXTENT = "extent";

    public static final String COL_DELETED = "deleted";
}
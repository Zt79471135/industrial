package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年01月28日 9:56
 */
/**
    * 订单动态表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_order_log")
public class AppOrderLog extends BaseEntity {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer userId;

    /**
     * 显示备注
     */
    @TableField(value = "remark")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String remark;

    /**
     * 系统备注
     */
    @TableField(value = "sys_remark")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sysRemark;

    /**
     * 订单编号
     */
    @TableField(value = "order_no")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderNo;

    /**
     * 审核层级 其他为0
     */
    @TableField(value = "`level`")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer level;

    /**
     * 审核状态 其他为0
     */
    @TableField(value = "`status`")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createTime;

    /**
     * 更新时间
     */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_REMARK = "remark";

    public static final String COL_SYS_REMARK = "sys_remark";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_LEVEL = "level";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
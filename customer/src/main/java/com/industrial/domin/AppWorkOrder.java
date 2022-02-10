package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.industrial.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年02月10日 10:04
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
    @Excel(name = "工单编号")
    private String number;

    /**
     * 关联ID
     */
    @TableField(value = "relate_id")
    private String relateId;

    /**
     * 工单标题
     */
    @TableField(value = "title")
    @Excel(name = "工单标题")
    private String title;

    /**
     * 工单内容
     */
    @TableField(value = "content")
    @Excel(name = "工单内容")
    private String content;

    /**
     * 工单类型
     */
    @TableField(value = "`type`")
    @Excel(name = "工单类型")
    private Integer type;

    /**
     * 发起人
     */
    @TableField(value = "start_user")
    private Integer startUser;

    @Excel(name = "发起人")
    public String startUserName;

    /**
     * 处理人员
     */
    @TableField(value = "handling_user")
    @Excel(name = "处理人员")
    private Integer handlingUser;

    @Excel(name = "处理人员")
    private String handlingUserName;

    @TableField(value = "cc_user")
    @Excel(name = "抄送人员")
    private String ccUser;

    /**
     * 发起时间
     */
    @TableField(value = "create_time")
    @Excel(name = "发起时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @Excel(name = "更新时间")
    private Date updateTime;

    /**
     * 工单状态
     */
    @TableField(value = "`status`")
    @Excel(name = "工单状态")
    private Byte status;

    /**
     * 紧急程度
     */
    @TableField(value = "extent")
    @Excel(name = "紧急程度")
    private Byte extent;

    /**
     * 0表删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public String ids;

    public Integer menuType;
    public Integer hours;


    public static final String COL_ID = "id";

    public static final String COL_NUMBER = "number";

    public static final String COL_RELATE_ID = "relate_id";

    public static final String COL_TITLE = "title";

    public static final String COL_TYPE = "type";

    public static final String COL_START_USER = "start_user";

    public static final String COL_HANDLING_USER = "handling_user";

    public static final String COL_CC_USER = "cc_user";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";

    public static final String COL_EXTENT = "extent";

    public static final String COL_DELETED = "deleted";
}
package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.entity.SysDictData;
import com.industrial.domin.AppActivityUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 活动表
 * @author
 * @date 2022年01月24日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_activity")
public class AppActivity {
    public static final String COL_DELETE_TIME = "delete_time";
    /**
     * 主键ID
     */
    @Excel(name = "活动编号", cellType = Excel.ColumnType.NUMERIC, prompt = "活动编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专题名称
     */
    @Excel(name = "活动名称")
    @TableField(value = "activity_name")
    private String activityName;

    /**
     * 名称描述
     */
    @Excel(name = "活动描述")
    @TableField(value = "description")
    private String description;

    /** 活动地址 */
    @Excel(name = "活动地址")
    @TableField(value = "activity_address")
    private String activityAddress;

    /**
     * 活动类型
     */
    @Excel(name = "活动类型")
    @TableField(value = "activity_type")
    private Integer activityType;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 开始时间
     */
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @TableField(value = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 活动状态
     */
    @Excel(name = "活动状态", readConverterExp = "0=正常,1=停用")
    @TableField(value = "activity_status")
    private Integer activityStatus;

    /**
     * 预计成本
     */
    @Excel(name = "预计成本")
    @TableField(value = "expected_cost")
    private BigDecimal expectedCost;

    /**
     * 预计收入
     */
    @Excel(name = "预计收入")
    @TableField(value = "expected_income")
    private BigDecimal expectedIncome;

    /**
     * 负责人员
     */
    @TableField(value = "head_user")
    private Integer headUser;

    /**
     * 主体图外键
     */
    @TableField(value = "topic_img_id")
    private Integer topicImgId;

    /**
     * 专题列表页,头图
     */
    @TableField(value = "head_img_id")
    private Integer headImgId;

    /** 附件参数 */
    @Excel(name = "附件参数")
    @TableField(value = "picture_param")
    private String pictureParam;

    /** 总结人员 */
    @Excel(name = "总结人员")
    @TableField(value = "conclusion_person")
    private Long conclusionPerson;

    /** 总结时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "总结时间", width = 30, dateFormat = "yyyy-MM-dd")
    @TableField(value = "conclusion_time")
    private Date conclusionTime;

    /** 实际成本 */
    @Excel(name = "实际成本")
    @TableField(value = "actual_cost")
    private BigDecimal actualCost;

    /** 实际收入 */
    @Excel(name = "实际收入")
    @TableField(value = "real_income")
    private BigDecimal realIncome;

    /** 实际成本率 */
    @Excel(name = "实际成本率")
    @TableField(value = "cost_rate")
    private String costRate;

    /** 实际回报率 */
    @Excel(name = "实际回报率")
    @TableField(value = "return_rate")
    private String returnRate;

    /** 总结描述 */
    @Excel(name = "总结描述")
    @TableField(value = "summary_desc")
    private String summaryDesc;

    /** 总结附件 */
    @Excel(name = "总结附件")
    @TableField(value = "attach_param")
    private String attachParam;

    /** 订单终至作废原因 */
    @Excel(name = "订单终至作废原因")
    @TableField(value = "invalid_reason")
    private String invalidReason;

    /**
     * 更新时间
     */
    @Excel(name = "更新时间", type = Excel.Type.EXPORT)
    @TableField(value = "update_time")
    private Date updateTime;


    /** 标志（0：启用，1：禁用) */
    @TableField(value = "enabled")
    @Excel(name = "启用状态", readConverterExp = "0=启用，1=禁用)")
    private Integer enabled;

    /**
     * 删除标志（0代表存在 ，1代表删除）
     */
    @TableField(value = "deleted")
    @Excel(name = "标志", readConverterExp = "0=存在，1=删除")
    private Byte deleted;

    //订单与参与用户
    public AppActivityUser activityUser;

    public static final String COL_ID = "id";

    public static final String COL_ACTIVITY_NAME = "activity_name";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_ACTIVITY_TYPE = "activity_type";

    public static final String COL_BEGIN_TIME = "begin_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_ACTIVITY_STATUS = "activity_status";

    public static final String COL_EXPECTED_COST = "expected_cost";

    public static final String COL_EXPECTED_INCOME = "expected_income";

    public static final String COL_HEAD_USER = "head_user";

    public static final String COL_TOPIC_IMG_ID = "topic_img_id";

    public static final String COL_HEAD_IMG_ID = "head_img_id";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}
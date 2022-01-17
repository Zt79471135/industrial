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
 * 活动表
 * @author zhu
 * @date 2022年01月14日 16:47
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
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专题名称
     */
    @TableField(value = "activity_name")
    private String activityName;

    /**
     * 名称描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 活动类型
     */
    @TableField(value = "activity_type")
    private Integer activityType;

    /**
     * 开始时间
     */
    @TableField(value = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 活动状态
     */
    @TableField(value = "activity_status")
    private Integer activityStatus;

    /**
     * 预计成本
     */
    @TableField(value = "expected_cost")
    private BigDecimal expectedCost;

    /**
     * 预计收入
     */
    @TableField(value = "expected_income")
    private BigDecimal expectedIncome;

    /**
     * 负责人员
     */
    @TableField(value = "head_user")
    private String headUser;

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

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 0表删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

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
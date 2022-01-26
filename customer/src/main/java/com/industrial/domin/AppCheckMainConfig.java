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
 * @date 2022年01月26日 9:59
 */

/**
 * 审核设置主表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_check_main_config")
public class AppCheckMainConfig {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 审批类型
     */
    @TableField(value = "check_type")
    private Byte checkType;

    /**
     * 审批状态
     */
    @TableField(value = "check_status")
    private Byte checkStatus;

    /**
     * 添加时间
     */
    @TableField(value = "add_time")
    private Date addTime;

    /**
     * 更行时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_CHECK_TYPE = "check_type";

    public static final String COL_CHECK_STATUS = "check_status";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
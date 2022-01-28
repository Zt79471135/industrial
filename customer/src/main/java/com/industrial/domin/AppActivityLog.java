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
 * @date 2022年01月28日 15:27
 */

/**
 * 活动日志表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_activity_log")
public class AppActivityLog {
    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 日志内容
     */
    @TableField(value = "log_contents")
    private String logContents;

    /**
     * 创建人
     */
    @TableField(value = "create_id")
    private Integer createId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_ACTIVITY_ID = "activity_id";

    public static final String COL_LOG_CONTENTS = "log_contents";

    public static final String COL_CREATE_ID = "create_id";

    public static final String COL_CREATE_TIME = "create_time";
}
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
 * 活动与参与人员表
 * @author zhu
 * @date 2022年01月14日 16:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_activity_user")
public class AppActivityUser {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 参与人员ID（关联sys_user表user_id)
     */
    @TableField(value = "user_id")
    private Integer userId;

    /** 参与人员名称 */
    @TableField(value = "user_name")
    private String userName;

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

    public static final String COL_ACTIVITY_ID = "activity_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETED = "deleted";
}
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
 * @date 2022年01月24日 17:16
 */

/**
 * 订单跟进记录表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_follow")
public class AppFollow {
    /**
     * 跟进任务表ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 跟进订单
     */
    @TableField(value = "follow_order")
    private Integer followOrder;

    /**
     * 跟进人
     */
    @TableField(value = "follow_user")
    private Integer followUser;

    /**
     * 下次跟进时间
     */
    @TableField(value = "follow_time")
    private Date followTime;

    /**
     * 1,系统消息,2,邮件通知,3,短信通知
     */
    @TableField(value = "reminder_type")
    private Byte reminderType;

    /**
     * 提醒时间
     */
    @TableField(value = "reminder_time")
    private Date reminderTime;

    /**
     * 0表初始,1表标记,2表通知
     */
    @TableField(value = "notified")
    private Byte notified;

    /**
     * 0表删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_FOLLOW_ORDER = "follow_order";

    public static final String COL_FOLLOW_USER = "follow_user";

    public static final String COL_FOLLOW_TIME = "follow_time";

    public static final String COL_REMINDER_TYPE = "reminder_type";

    public static final String COL_REMINDER_TIME = "reminder_time";

    public static final String COL_NOTIFIED = "notified";

    public static final String COL_DELETED = "deleted";
}
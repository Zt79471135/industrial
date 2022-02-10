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
 * 订单跟进记录表
 * @author zhu
 * @date 2022年02月10日 10:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_follow")
public class AppFollow {
    public static final String COL_FOLLOW_ORDER = "follow_order";
    /**
     * 跟进任务表ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 跟进id
     */
    @TableField(value = "follow_id")
    private Integer followId;

    /**
     * 1.客户,2.联系人,3.订单,4.费用
     */
    @TableField(value = "follow_type")
    private Integer followType;

    /**
     * 跟进人
     */
    @TableField(value = "follow_user")
    private Integer followUser;

    /**
     * 跟进方式
     */
    @TableField(value = "follow_way")
    private String followWay;

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

    public static final String COL_FOLLOW_ID = "follow_id";

    public static final String COL_FOLLOW_TYPE = "follow_type";

    public static final String COL_FOLLOW_USER = "follow_user";

    public static final String COL_FOLLOW_WAY = "follow_way";

    public static final String COL_FOLLOW_TIME = "follow_time";

    public static final String COL_REMINDER_TYPE = "reminder_type";

    public static final String COL_REMINDER_TIME = "reminder_time";

    public static final String COL_NOTIFIED = "notified";

    public static final String COL_DELETED = "deleted";
}
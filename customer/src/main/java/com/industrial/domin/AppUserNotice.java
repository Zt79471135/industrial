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
 * @date 2022年01月24日 16:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_user_notice")
public class AppUserNotice {
    /**
     * 消息通知表ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 被通知用户
     */
    @TableField(value = "`uid`")
    private Integer uid;

    /**
     * 通知的消息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 0表删除
     */
    @TableField(value = "deleted")
    private Boolean deleted;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_MSG = "msg";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_DELETED = "deleted";
}
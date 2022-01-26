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
 * @date 2022年01月26日 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_review")
public class AppReview {
    /**
     * 评论表ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 评论人ID
     */
    @TableField(value = "`uid`")
    private Integer uid;

    /**
     * 评论信息
     */
    @TableField(value = "msg")
    private String msg;

    /**
     * 评论记录
     */
    @TableField(value = "record")
    private String record;

    /**
     * 0表删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    public static final String COL_ID = "id";

    public static final String COL_UID = "uid";

    public static final String COL_MSG = "msg";

    public static final String COL_RECORD = "record";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_TIME = "create_time";
}
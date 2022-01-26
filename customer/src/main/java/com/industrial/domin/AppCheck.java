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
 * @date 2022年01月26日 9:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_check")
public class AppCheck {
    /**
     * 审核表主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 审核人ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 待审核ID
     */
    @TableField(value = "audit_id")
    private Integer auditId;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 0表删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_AUDIT_ID = "audit_id";

    public static final String COL_STATUS = "status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_DELETED = "deleted";
}
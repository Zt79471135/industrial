package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年02月10日 16:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_follow_user")
public class AppFollowUser {
    /**
     * 跟进表与协作表关联表主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 跟进表主键
     */
    @TableField(value = "follow_id")
    private Integer followId;

    /**
     * 协作人员主键
     */
    @TableField(value = "user_id")
    private Integer userId;

    public static final String COL_ID = "id";

    public static final String COL_FOLLOW_ID = "follow_id";

    public static final String COL_USER_ID = "user_id";
}
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
 * @date 2022年01月26日 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_check_user")
public class AppCheckUser {
    /**
     * 关联表ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 审核从表ID
     */
    @TableField(value = "check_id")
    private Integer checkId;

    /**
     * 审核人员ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "main_id")
    private Integer mainId;

    public static final String COL_ID = "id";

    public static final String COL_CHECK_ID = "check_id";

    public static final String COL_MAIN_ID = "main_id";

    public static final String COL_USER_ID = "user_id";
}
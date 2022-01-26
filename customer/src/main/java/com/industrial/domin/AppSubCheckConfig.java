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
 * @date 2022年01月26日 10:58
 */

/**
 * 审核设置子表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_sub_check_config")
public class AppSubCheckConfig {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 主审批配置表id
     */
    @TableField(value = "config_id")
    private Integer configId;

    /**
     * 审批类型
     */
    @TableField(value = "ctype")
    private Byte ctype;

    /**
     * 第几层审批
     */
    @TableField(value = "clevel")
    private Byte clevel;

    /**
     * 人员列表
     */
    @TableField(value = "admin_list")
    private String adminList;

    /**
     * 添加时间
     */
    @TableField(value = "add_time")
    private Date addTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_CONFIG_ID = "config_id";

    public static final String COL_CTYPE = "ctype";

    public static final String COL_CLEVEL = "clevel";

    public static final String COL_ADMIN_LIST = "admin_list";

    public static final String COL_ADD_TIME = "add_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
package com.industrial.entity;

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
 * @date 2022年01月19日 16:30
 */
/**
    * 业务员信息表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_user_salesman")
public class AppUserSalesman {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 外键，客户ID 关联app_user表ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 姓名
     */
    @TableField(value = "sale_name")
    private String saleName;

    /**
     * 编号
     */
    @TableField(value = "sale_code")
    private String saleCode;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除时间
     */
    @TableField(value = "delete_time")
    private Date deleteTime;

    /**
     * 0表示删除,1表示启用
     */
    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_SALE_NAME = "sale_name";

    public static final String COL_SALE_CODE = "sale_code";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_ADDRESS = "address";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETE_TIME = "delete_time";

    public static final String COL_DELETED = "deleted";
}
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
 * @date 2022年01月24日 13:44
 */
/**
    * 用户信息表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User {
    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 部门ID
     */
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 用户账号
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 用户类型（00系统用户）
     */
    @TableField(value = "user_type")
    private String userType;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码
     */
    @TableField(value = "phonenumber")
    private String phonenumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 头像地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableField(value = "del_flag")
    private String delFlag;

    /**
     * 最后登录IP
     */
    @TableField(value = "login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @TableField(value = "login_date")
    private Date loginDate;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_DEPT_ID = "dept_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_NICK_NAME = "nick_name";

    public static final String COL_USER_TYPE = "user_type";

    public static final String COL_EMAIL = "email";

    public static final String COL_PHONENUMBER = "phonenumber";

    public static final String COL_SEX = "sex";

    public static final String COL_AVATAR = "avatar";

    public static final String COL_PASSWORD = "password";

    public static final String COL_STATUS = "status";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_LOGIN_IP = "login_ip";

    public static final String COL_LOGIN_DATE = "login_date";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_REMARK = "remark";
}
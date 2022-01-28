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
 * @date 2022年01月27日 17:22
 */

/**
 * 上传附件表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_image_file")
public class AppImageFile {
    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 文件名
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 原文件名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 文件路径
     */
    @TableField(value = "file_path")
    private String filePath;

    /**
     * 创建人
     */
    @TableField(value = "create_userid")
    private Integer createUserid;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 文件类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_FILE_NAME = "file_name";

    public static final String COL_NAME = "name";

    public static final String COL_FILE_PATH = "file_path";

    public static final String COL_CREATE_USERID = "create_userid";

    public static final String COL_STATUS = "status";

    public static final String COL_TYPE = "type";

    public static final String COL_UPDATE_TIME = "update_time";
}
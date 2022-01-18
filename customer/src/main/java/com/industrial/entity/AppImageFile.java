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
 * 图片文件表
 * @author zhu
 * @date 2022年01月17日 17:38
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
     * 图片文件名
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 图片文件路径
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
    @TableField(value = "`Status`")
    private Byte status;

    /**
     * 文件类型
     */
    @TableField(value = "`Type`")
    private String type;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_FILE_NAME = "file_name";

    public static final String COL_FILE_PATH = "file_path";

    public static final String COL_CREATE_USERID = "create_userid";

    public static final String COL_STATUS = "Status";

    public static final String COL_TYPE = "Type";

    public static final String COL_UPDATE_TIME = "update_time";
}
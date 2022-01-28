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
 * @date 2022年01月28日 15:22
 */
/**
    * 活动附件表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_activity_file")
public class AppActivityFile {
    /**
     * 活动文件关联表ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 文件ID
     */
    @TableField(value = "file_id")
    private Integer fileId;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "deleted")
    private Byte deleted;

    public static final String COL_ID = "id";

    public static final String COL_FILE_ID = "file_id";

    public static final String COL_ACTIVITY_ID = "activity_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_DELETED = "deleted";
}
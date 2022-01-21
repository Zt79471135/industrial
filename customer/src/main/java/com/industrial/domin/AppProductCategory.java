package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import com.industrial.common.annotation.Excel;
import com.industrial.common.annotation.Excels;
import com.industrial.common.core.domain.BaseEntity;
import com.industrial.common.core.domain.entity.SysDictData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品分类
 * @author chenjh
 * @date 2022年01月19日  10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_product_category")
public class AppProductCategory  {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Excel(name = "分类序号", cellType = Excel.ColumnType.NUMERIC, prompt = "分类编号")
    private Integer id;

    /**
     * 分类名称
     */
    @TableField(value = "category_name")
    @Excel(name = "分类名称")
    private String categoryName;

    /**
     * 所属分类
     */
    @TableField(value = "product_type")
    private Integer productType;

    /**
     * 分类编号
     */
    @TableField(value = "category_code")
    @Excel(name = "分类编号")
    private String categoryCode;

    /**
     * 助记码
     */
    @TableField(value = "mnemonic_code")
    private String mnemonicCode;

    /**
     * 外键,关联image表（关联app_image_file表ID）
     */
    @TableField(value = "topic_img_id")
    private Integer topicImgId;

    /**
     * 描述
     */
    @TableField(value = "description")
    @Excel(name = "描述")
    private String description;

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
     * 标志（1：禁用，0：启用)
     */
    @TableField(value = "deleted")
    @Excel(name = "启用状态", readConverterExp = "0=启用,1=禁用")
    private Byte deleted;

//    @Excels({
//            @Excel(name = "所属分类", targetAttr = "dictLabel", type = Excel.Type.EXPORT)
//    })
    //private SysDictData dictData;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_PRODUCT_TYPE = "product_type";

    public static final String COL_CATEGORY_CODE = "category_code";

    public static final String COL_MNEMONIC_CODE = "mnemonic_code";

    public static final String COL_TOPIC_IMG_ID = "topic_img_id";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_DELETE_TIME = "delete_time";

    public static final String COL_DELETED = "deleted";
}
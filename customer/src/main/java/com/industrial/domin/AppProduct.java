package com.industrial.domin;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.industrial.common.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhu
 * @date 2022年01月13日 16:39
 */

/**
 * 商品表
 * @author 79471
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_product")
public class AppProduct {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 商品编号
     */
    @Excel(name = "商品编号", cellType = Excel.ColumnType.NUMERIC, prompt = "商品编号")
    @TableField(value = "`number`")
    private String number;
    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    @TableField(value = "`name`")
    private String name;

    /**
     * 商品类别
     */
    @Excel(name = "商品类别")
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 库存量
     */
    @Excel(name = "库存量")
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 单价
     */
    @Excel(name = "单价")
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 售后联系人
     */
    @Excel(name = "售后联系人")
    @TableField(value = "contacts")
    private String contacts;

    /**
     * 售后服务电话
     */
    @Excel(name = "售后服务电话")
    @TableField(value = "telephone")
    private String telephone;

    /**
     * 单位id
     */
    @Excel(name = "单位id", type = Excel.Type.IMPORT)
    @TableField(value = "unit_id")
    private String unitId;

    /**
     * 最低价
     */
    @Excel(name = "最低价")
    @TableField(value = "floor_price")
    private BigDecimal floorPrice;

    /**
     * 0删除1待审核2修改一3审核4修改二
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 主图ID
     */
    @Excel(name = "主图ID", type = Excel.Type.IMPORT)
    @TableField(value = "main_img_url")
    private String mainImgUrl;

    /**
     * 摘要
     */
    @Excel(name = "摘要")
    @TableField(value = "summary")
    private String summary;

    /**
     * 图片外键
     */
    @Excel(name = "图片外键", type = Excel.Type.IMPORT)
    @TableField(value = "img_id")
    private Integer imgId;

    /**
     * 0为删除
     */
    @TableField(value = "deleted")
    private Byte deleted;

    /**
     * 创建用户ID
     */
    @Excel(name = "创建用户ID", type = Excel.Type.IMPORT)
    @TableField(value = "create_user_id")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    public static final String COL_ID = "id";

    public static final String COL_NUMBER = "number";

    public static final String COL_NAME = "name";

    public static final String COL_CATEGORY_ID = "category_id";

    public static final String COL_STOCK = "stock";

    public static final String COL_PRICE = "price";

    public static final String COL_CONTACTS = "contacts";

    public static final String COL_TELEPHONE = "telephone";

    public static final String COL_UNIT_ID = "unit_id";

    public static final String COL_FLOOR_PRICE = "floor_price";

    public static final String COL_STATUS = "status";

    public static final String COL_MAIN_IMG_URL = "main_img_url";

    public static final String COL_SUMMARY = "summary";

    public static final String COL_IMG_ID = "img_id";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_USER_ID = "create_user_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}
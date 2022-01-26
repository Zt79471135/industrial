package com.industrial.domin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhu
 * @date 2022年01月26日 16:42
 */

/**
 * 商品表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "app_product")
public class AppProduct {
    public static final String COL_PICTUREPARAM = "PictureParam";
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 商品编号
     */
    @TableField(value = "`number`")
    private String number;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 商品类别
     */
    @TableField(value = "category_id")
    private Integer categoryId;

    /**
     * 库存量
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 单价
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 售后联系人
     */
    @TableField(value = "contacts")
    private String contacts;

    /**
     * 售后服务电话
     */
    @TableField(value = "telephone")
    private String telephone;

    /**
     * 单位编号
     */
    @TableField(value = "unit_id")
    private String unitId;

    /**
     * 对内价格
     */
    @TableField(value = "floor_price")
    private BigDecimal floorPrice;

    /**
     * 规格
     */
    @TableField(value = "specifica")
    private String specifica;

    /**
     * 维保期
     */
    @TableField(value = "maintenance")
    private String maintenance;

    /**
     * 状态：1保存商品，2待审核商品，3上架,4下架
     */
    @TableField(value = "`status`")
    private Byte status;

    /**
     * 图片参数（数组）
     */
    @TableField(value = "picture_param")
    private String pictureParam;

    /**
     * 图片外键（关联app_image_file表ID）
     */
    @TableField(value = "img_id")
    private Integer imgId;

    /**
     * 主图URL
     */
    @TableField(value = "main_img_url")
    private String mainImgUrl;

    /**
     * 商品详情
     */
    @TableField(value = "summary")
    private String summary;

    /**
     * 创建用户ID
     */
    @TableField(value = "create_user_id")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 启用状态（0：启用，1：禁用)
     */
    @TableField(value = "enabled")
    private Byte enabled;

    /**
     * 删除标志（0代表存在 ，1代表删除）
     */
    @TableField(value = "deleted")
    private Byte deleted;

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

    public static final String COL_SPECIFICA = "specifica";

    public static final String COL_MAINTENANCE = "maintenance";

    public static final String COL_STATUS = "status";

    public static final String COL_PICTURE_PARAM = "picture_param";

    public static final String COL_IMG_ID = "img_id";

    public static final String COL_MAIN_IMG_URL = "main_img_url";

    public static final String COL_SUMMARY = "summary";

    public static final String COL_CREATE_USER_ID = "create_user_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_DELETED = "deleted";
}
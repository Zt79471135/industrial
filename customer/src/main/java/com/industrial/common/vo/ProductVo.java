package com.industrial.common.vo;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhu
 * @date 2022年01月14日 9:57
 */
@Data
public class ProductVo{
    /**
     * 主键
     */
    private Integer id;
    /**
     * 商品编号
     */
    private String number;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品类别
     */
    private Integer categoryId;
    /**
     * 库存量
     */
    private Integer stock;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 售后联系人
     */
    private String contacts;
    /**
     * 售后服务电话
     */
    private String telephone;
    /**
     * 单位编号
     */
    private String unitId;
    /**
     * 对内价格
     */
    private BigDecimal floorPrice;
    /**
     * 规格
     */
    private String specifica;
    /**
     * 维保期
     */
    private String maintenance;
    /**
     * 1待审核2失败3上架
     */
    private Byte status;
    /**
     * 图片参数（数组）
     */
    private String pictureParam;
    /**
     * 图片外键（关联app_image_file表ID）
     */
    private List<Integer> imgIds;
    /**
     * 主图URL
     */
    private String mainImgUrl;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 标志（0：禁用，1：启用)
     */
    private Byte deleted;
    /**
     * 创建用户ID
     */
    private Integer createUserId;

}

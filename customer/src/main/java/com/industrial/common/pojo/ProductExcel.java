package com.industrial.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.industrial.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhu
 * @date 2022年01月24日 10:52
 */
@Data
public class ProductExcel {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @Excel(name = "分类序号", cellType = Excel.ColumnType.NUMERIC, prompt = "分类编号")
    private Integer id;
    /**
     * 商品编号
     */
    @Excel(name = "商品编号")
    private String number;
    /**
     * 商品名称
     */
    @Excel(name = "商品名称")
    private String name;
    /**
     * 商品类别
     */
    @Excel(name = "商品类别")
    private String categoryName;
    /**
     * 库存量
     */
    @Excel(name = "库存量")
    private Integer stock;
    /**
     * 单价
     */
    @Excel(name = "单价")
    private String price;
    /**
     * 售后联系人
     */
    @Excel(name = "售后联系人")
    private String contactsUserName;
    /**
     * 售后服务电话
     */
    @Excel(name = "售后服务电话")
    private String telephone;
    /**
     * 单位编号
     */
    @Excel(name = "单位编号")
    private String unitId;
    /**
     * 对内价格
     */
    @Excel(name = "对内价格")
    private String floorPrice;
    /**
     * 规格
     */
    @Excel(name = "规格")
    private String specifica;
    /**
     * 维保期
     */
    @Excel(name = "维保期")
    private String maintenance;
    /**
     * 1待审核2失败3上架
     */
    @Excel(name = "状态")
    private String status;
    /**
     * 摘要
     */
    @Excel(name = "摘要")
    private String summary;
    /**
     * 创建用户ID
     */
    @Excel(name = "创建用户")
    private String userName;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private String createTime;
    /**
     * 更新时间
     */
    @Excel(name = "更新时间")
    private String updateTime;
}

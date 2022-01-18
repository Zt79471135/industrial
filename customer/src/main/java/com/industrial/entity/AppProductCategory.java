package com.industrial.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;

/**
 * 商品分类对象 app_product_category
 * 
 * @author chenjh
 * @date 2022-01-17
 */
public class AppProductCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String name;

    /** 所属分类 */
    @Excel(name = "所属分类")
    private Long productType;

    /** 分类编号 */
    @Excel(name = "分类编号")
    private String categoryCode;

    /** 助记码 */
    @Excel(name = "助记码")
    private String mnemonicCode;

    /** 外键,关联image表（关联app_image_file表ID） */
    @Excel(name = "外键,关联image表", readConverterExp = "关=联app_image_file表ID")
    private Long topicImgId;

    /** 描述 */
    @Excel(name = "描述")
    private String description;
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;

    /** 标志（0：禁用，1：启用) */
    @Excel(name = "标志", readConverterExp = "标志（0：禁用，1：启用)")
    private Integer deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setProductType(Long productType) 
    {
        this.productType = productType;
    }

    public Long getProductType() 
    {
        return productType;
    }
    public void setCategoryCode(String categoryCode) 
    {
        this.categoryCode = categoryCode;
    }

    public String getCategoryCode() 
    {
        return categoryCode;
    }
    public void setMnemonicCode(String mnemonicCode) 
    {
        this.mnemonicCode = mnemonicCode;
    }

    public String getMnemonicCode() 
    {
        return mnemonicCode;
    }
    public void setTopicImgId(Long topicImgId) 
    {
        this.topicImgId = topicImgId;
    }

    public Long getTopicImgId() 
    {
        return topicImgId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setDeleteTime(Date deleteTime) 
    {
        this.deleteTime = deleteTime;
    }

    public Date getDeleteTime() 
    {
        return deleteTime;
    }
    public void setDeleted(Integer deleted) 
    {
        this.deleted = deleted;
    }

    public Integer getDeleted() 
    {
        return deleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("productType", getProductType())
            .append("categoryCode", getCategoryCode())
            .append("mnemonicCode", getMnemonicCode())
            .append("topicImgId", getTopicImgId())
            .append("description", getDescription())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .append("deleted", getDeleted())
            .toString();
    }
}

package com.industrial.domin;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.annotation.Excels;
import com.industrial.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.industrial.common.core.domain.BaseEntity;
import com.industrial.common.core.domain.entity.SysDictData;

/**
 * 商品分类
 * @author chenjh
 * @date 2022年01月19日  10:22
 */
public class AppProductCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "序号", cellType = Excel.ColumnType.NUMERIC, prompt = "编号")
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 所属分类 */
    //@Excel(name = "所属分类")
    private Long productType;

    /** 分类编号 */
    @Excel(name = "分类编号")
    private String categoryCode;

    /** 助记码 */
    @Excel(name = "助记码")
    private String mnemonicCode;

    /** 外键,关联image表（关联app_image_file表ID） */
    private Long topicImgId;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deleteTime;

    /** 启用状态（0：启用，1：禁用) */
    @Excel(name = "启用状态", readConverterExp = "0=启用,1=禁用")
    private Integer enabled;

    /** 标志（0：禁用，1：启用) */
    private Integer deleted;

    /** 字典对象 */
    @Excels({
            @Excel(name = "所属分类", targetAttr = "dictLabel", type = Excel.Type.EXPORT)
    })
    private SysDictData dictData;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
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
    public void setEnabled(Integer enabled)
    {
        this.enabled = enabled;
    }

    public Integer getEnabled()
    {
        return enabled;
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
                .append("categoryName", getCategoryName())
                .append("productType", getProductType())
                .append("categoryCode", getCategoryCode())
                .append("mnemonicCode", getMnemonicCode())
                .append("topicImgId", getTopicImgId())
                .append("description", getDescription())
                .append("updateTime", getUpdateTime())
                .append("deleteTime", getDeleteTime())
                .append("enabled", getEnabled())
                .append("deleted", getDeleted())
                .toString();
    }
}
package com.industrial.domin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.annotation.Excels;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.industrial.common.core.domain.BaseEntity;

/**
 * 描述:
 * 日期：2022-01-24
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */

public class AppCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "序号", cellType = Excel.ColumnType.NUMERIC, prompt = "编号")
    private Long id;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String categoryName;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 父分类id */
    @Excel(name = "上级分类id")
    private Long parentId;

    /** 分类编号 */
    @Excel(name = "分类编号")
    private String categoryCode;

    /** 助记码 */
    @Excel(name = "助记码")
    private String mnemonicCode;

    /** 启用状态（0：启用，1：禁用) */
    @Excel(name = "启用状态", readConverterExp = "0=启用,1=禁用")
    private Integer enabled;

    /** 外键,关联image表 */
    private Long topicImgId;

    /** 祖级列表 */
    private String ancestors;

    /** 删除标志（0：删除，1：正常) */
    //@Excel(name = "删除标志", readConverterExp = "删除标志（0：删除，1：正常)")
    private Integer deleted;

    /** 删除时间 */
    private Long deleteTime;

    /** 父分类名称 */
    private String parentName;

    /** 子分类 */
    private List<AppCategory> children = new ArrayList<AppCategory>();

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public List<AppCategory> getChildren()
    {
        return children;
    }

    public void setChildren(List<AppCategory> children)
    {
        this.children = children;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setTopicImgId(Long topicImgId)
    {
        this.topicImgId = topicImgId;
    }

    public Long getTopicImgId()
    {
        return topicImgId;
    }
    public void setAncestors(String ancestors)
    {
        this.ancestors = ancestors;
    }

    public String getAncestors()
    {
        return ancestors;
    }
    public void setDeleteTime(Long deleteTime)
    {
        this.deleteTime = deleteTime;
    }

    public Long getDeleteTime()
    {
        return deleteTime;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
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
    public void setDeleted(Integer deleted)
    {
        this.deleted = deleted;
    }

    public Integer getDeleted()
    {
        return deleted;
    }
    public void setEnabled(Integer enabled)
    {
        this.enabled = enabled;
    }

    public Integer getEnabled()
    {
        return enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("parentId", getParentId())
                .append("topicImgId", getTopicImgId())
                .append("ancestors", getAncestors())
                .append("deleteTime", getDeleteTime())
                .append("categoryName", getCategoryName())
                .append("description", getDescription())
                .append("categoryCode", getCategoryCode())
                .append("updateTime", getUpdateTime())
                .append("mnemonicCode", getMnemonicCode())
                .append("deleted", getDeleted())
                .append("enabled", getEnabled())
                .toString();
    }
}
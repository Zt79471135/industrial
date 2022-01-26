package com.industrial.domin;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审核设置子对象 app_subcheckconfig
 *
 * @author li.shenkang
 * @date 2022-01-24
 */
public class AppSubCheckConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long configId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer ctype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer clevel;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String adminList;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date addTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setConfigId(Long configId)
    {
        this.configId = configId;
    }

    public Long getConfigId()
    {
        return configId;
    }
    public void setCtype(Integer ctype)
    {
        this.ctype = ctype;
    }

    public Integer getCtype()
    {
        return ctype;
    }
    public void setClevel(Integer clevel)
    {
        this.clevel = clevel;
    }

    public Integer getClevel()
    {
        return clevel;
    }
    public void setAdminList(String adminList)
    {
        this.adminList = adminList;
    }

    public String getAdminList()
    {
        return adminList;
    }
    public void setAddTime(Date addTime)
    {
        this.addTime = addTime;
    }

    public Date getAddTime()
    {
        return addTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("configId", getConfigId())
                .append("ctype", getCtype())
                .append("clevel", getClevel())
                .append("adminList", getAdminList())
                .append("addTime", getAddTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}

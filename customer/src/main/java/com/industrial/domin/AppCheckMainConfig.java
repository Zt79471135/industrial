package com.industrial.domin;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审核设置主对象 app_checkmainconfig
 * 
 * @author lishenkang
 * @date 2022-01-24
 */
public class AppCheckMainConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer checkType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer checkStatus;

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
    public void setCheckType(Integer checkType) 
    {
        this.checkType = checkType;
    }

    public Integer getCheckType() 
    {
        return checkType;
    }
    public void setCheckStatus(Integer checkStatus) 
    {
        this.checkStatus = checkStatus;
    }

    public Integer getCheckStatus() 
    {
        return checkStatus;
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
            .append("checkType", getCheckType())
            .append("checkStatus", getCheckStatus())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

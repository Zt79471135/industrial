package com.industrial.domin;

import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * system对象 app_check
 * 
 * @author lishenkang
 * @date 2022-01-26
 */
public class AppCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核表主键 */
    private Long id;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private String userId;

    /** 待审核ID */
    @Excel(name = "待审核ID")
    private Long auditId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long auditLevel;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String orderId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 0表删除 */
    @Excel(name = "0表删除")
    private Integer deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setAuditId(Long auditId) 
    {
        this.auditId = auditId;
    }

    public Long getAuditId() 
    {
        return auditId;
    }
    public void setAuditLevel(Long auditLevel) 
    {
        this.auditLevel = auditLevel;
    }

    public Long getAuditLevel() 
    {
        return auditLevel;
    }
    public void setOrderId(String orderId) 
    {
        this.orderId = orderId;
    }

    public String getOrderId() 
    {
        return orderId;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("auditId", getAuditId())
            .append("auditLevel", getAuditLevel())
            .append("orderId", getOrderId())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("deleted", getDeleted())
            .toString();
    }
}

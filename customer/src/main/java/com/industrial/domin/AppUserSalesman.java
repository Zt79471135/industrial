package com.industrial.domin;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 业务员信息对象 app_user_salesman
 * 
 * @author ruoyi
 * @date 2022-01-20
 */
public class AppUserSalesman extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 外键，客户ID 关联app_user表ID */
    @Excel(name = "外键，客户ID 关联app_user表ID")
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String saleName;

    /** 编号 */
    @Excel(name = "编号")
    private String saleCode;

    /** 性别 */
    @Excel(name = "性别")
    private Integer sex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 电话 */
    @Excel(name = "手机号")
    private String phone;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;

    /** 0表示删除,1表示启用 */
    @Excel(name = "0表示删除,1表示启用")
    private Integer deleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setSaleName(String saleName) 
    {
        this.saleName = saleName;
    }

    public String getSaleName() 
    {
        return saleName;
    }
    public void setSaleCode(String saleCode) 
    {
        this.saleCode = saleCode;
    }

    public String getSaleCode() 
    {
        return saleCode;
    }
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public Integer getSex()
    {
        return sex;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("saleName", getSaleName())
            .append("saleCode", getSaleCode())
            .append("sex", getSex())
            .append("mobile", getMobile())
            .append("phone", getPhone())
            .append("address", getAddress())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .append("deleted", getDeleted())
            .toString();
    }
}

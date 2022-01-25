package com.industrial.domin;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户收货地址,用于客户联系人信息对象 app_user_address
 * 
 * @author ruoyi
 * @date 2022-01-20
 */
public class AppUserAddress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 外键，客户ID 关联app_user表ID */
    @Excel(name = "外键，客户ID 关联app_user表ID")
    private Long userId;

    /** 收货人姓名 */
    @Excel(name = "收货人姓名")
    private String name;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private String uid;

    /** 性别 */
    @Excel(name = "用户编号")
    private Integer sex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    @Excel(name = "电话")
    private String phone;

    /** 地址类型 */
    @Excel(name = "地址类型")
    private Long addrType;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String address;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String detail;

    /** 删除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deleteTime;
    private String addrTypeName;
    private String sexName;

    @Excel(name = "默认选择")
    private Integer isdefault;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUid(String uid) 
    {
        this.uid = uid;
    }

    public String getUid() 
    {
        return uid;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setSex(Integer sex){ this.sex = sex; }

    public Integer getSex()
    {
        return sex;
    }
    public void setAddrType(Long addrType) 
    {
        this.addrType = addrType;
    }

    public Long getAddrType() 
    {
        return addrType;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }

    public String getDetail() 
    {
        return detail;
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

    public String getAddrTypeName() {
        return addrTypeName;
    }

    public void setAddrTypeName(String addrTypeName) {
        this.addrTypeName = addrTypeName;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("uid", getUid())
            .append("sex", getSex())
            .append("mobile", getMobile())
            .append("phone", getPhone())
            .append("addrType", getAddrType())
            .append("address", getAddress())
            .append("detail", getDetail())
            .append("updateTime", getUpdateTime())
            .append("deleteTime", getDeleteTime())
            .append("deleted", getDeleted())
            .append("addrTypeName", getAddrTypeName())
            .append("sexName", getSexName())
            .append("isdefault", getIsdefault())
            .toString();
    }

    public Integer getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(Integer isdefault) {
        this.isdefault = isdefault;
    }
}

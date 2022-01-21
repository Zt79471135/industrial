package com.industrial.domin;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.industrial.common.annotation.Excel;
import com.industrial.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户管理对象 app_user
 * 
 * @author lsk
 * @date 2022-01-18
 */
public class AppUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** openID */
    @Excel(name = "openID")
    private String openid;

    /** 客户名称 */
    @Excel(name = "客户名称")
    private String company;

    /** 客户编码 */
    @Excel(name = "客户编码")
    private String clientCode;

    /** 简称 */
    @Excel(name = "简称")
    private String brief;

    /** 注册人 */
    @Excel(name = "注册人")
    private String registrant;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registratTime;

    /** 联系人 */
    @Excel(name = "联系人")
    private String nickname;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String mobile;

    /** 客户类型 */
    @Excel(name = "客户类型")
    private Integer custType;

    /** 公司类型 */
    @Excel(name = "公司类型")
    private Integer companyType;

    /** 发票抬头 */
    @Excel(name = "发票抬头")
    private String invoiceUp;

    /** 税号 */
    @Excel(name = "税号")
    private String creditCode;

    /** 开户银行 */
    @Excel(name = "开户银行")
    private String bank;

    /** 银行账号 */
    @Excel(name = "银行账号")
    private String bankAccount;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNum;

    /** 单位地址 */
    @Excel(name = "单位地址")
    private String address;

    /** 扩展备注 */
    @Excel(name = "扩展备注")
    private String extend;

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
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setClientCode(String clientCode) 
    {
        this.clientCode = clientCode;
    }

    public String getClientCode() 
    {
        return clientCode;
    }
    public void setBrief(String brief) 
    {
        this.brief = brief;
    }

    public String getBrief() 
    {
        return brief;
    }
    public void setRegistrant(String registrant) 
    {
        this.registrant = registrant;
    }

    public String getRegistrant() 
    {
        return registrant;
    }
    public void setRegistratTime(Date registratTime) 
    {
        this.registratTime = registratTime;
    }

    public Date getRegistratTime() 
    {
        return registratTime;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }
    public void setCustType(Integer custType)
    {
        this.custType = custType;
    }

    public Integer getCustType()
    {
        return custType;
    }
    public void setCompanyType(Integer companyType)
    {
        this.companyType = companyType;
    }

    public Integer getCompanyType()
    {
        return companyType;
    }
    public void setInvoiceUp(String invoiceUp) 
    {
        this.invoiceUp = invoiceUp;
    }

    public String getInvoiceUp() 
    {
        return invoiceUp;
    }
    public void setCreditCode(String creditCode) 
    {
        this.creditCode = creditCode;
    }

    public String getCreditCode() 
    {
        return creditCode;
    }
    public void setBank(String bank) 
    {
        this.bank = bank;
    }

    public String getBank() 
    {
        return bank;
    }
    public void setBankAccount(String bankAccount) 
    {
        this.bankAccount = bankAccount;
    }

    public String getBankAccount() 
    {
        return bankAccount;
    }
    public void setPhoneNum(String phoneNum) 
    {
        this.phoneNum = phoneNum;
    }

    public String getPhoneNum() 
    {
        return phoneNum;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setExtend(String extend) 
    {
        this.extend = extend;
    }

    public String getExtend() 
    {
        return extend;
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
            .append("openid", getOpenid())
            .append("company", getCompany())
            .append("clientCode", getClientCode())
            .append("brief", getBrief())
            .append("registrant", getRegistrant())
            .append("registratTime", getRegistratTime())
            .append("nickname", getNickname())
            .append("mobile", getMobile())
            .append("custType", getCustType())
            .append("companyType", getCompanyType())
            .append("invoiceUp", getInvoiceUp())
            .append("creditCode", getCreditCode())
            .append("bank", getBank())
            .append("bankAccount", getBankAccount())
            .append("phoneNum", getPhoneNum())
            .append("address", getAddress())
            .append("updateTime", getUpdateTime())
            .append("extend", getExtend())
            .append("deleted", getDeleted())
            .toString();
    }
}

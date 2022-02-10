package com.industrial.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.industrial.common.core.domain.entity.SysDictData;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.utils.DateUtils;
import com.industrial.common.utils.SecurityUtils;
import com.industrial.common.utils.StringUtils;
import com.industrial.common.utils.bean.BeanValidators;
import com.industrial.common.utils.uuid.UUID;
import com.industrial.domin.AppUser;
import com.industrial.domin.AppUserAddress;
import com.industrial.domin.AppUserSalesman;
import com.industrial.mapper.AppUserAddressMapper;
import com.industrial.mapper.AppUserMapper;
import com.industrial.mapper.AppUserSalesmanMapper;
import com.industrial.service.IAppUserService;
import com.industrial.system.mapper.SysDictDataMapper;
import com.industrial.system.service.impl.SysUserServiceImpl;
import io.lettuce.core.api.async.RedisTransactionalAsyncCommands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 客户管理Service业务层处理
 * 
 * @author lsk
 * @date 2022-01-18
 */
@Service
public class AppUserServiceImpl implements IAppUserService
{
    private static final Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private AppUserAddressMapper appUserAddressMapper;
    @Autowired
    private AppUserSalesmanMapper appUserSalesmanMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public AppUser selectAppUserById(Long id)
    {
        return appUserMapper.selectAppUserById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param appUser 客户管理
     * @return 客户管理
     */
    @Override
    public List<AppUser> selectAppUserList(AppUser appUser)
    {
        return appUserMapper.selectAppUserList(appUser);
    }

    /**
     * 新增客户管理
     * 
     * @param appUser 客户管理
     * @return 结果
     */
    @Override
    public int insertAppUser(AppUser appUser)
    {
        return appUserMapper.insertAppUser(appUser);
    }

    /**
     * 修改客户管理
     * 
     * @param appUser 客户管理
     * @return 结果
     */
    @Override
    public int updateAppUser(AppUser appUser)
    {
        appUser.setUpdateTime(DateUtils.getNowDate());
        return appUserMapper.updateAppUser(appUser);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteAppUserByIds(Long[] ids)
    {
        return appUserMapper.deleteAppUserByIds(ids);
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteAppUserById(Long id)
    {
        return appUserMapper.deleteAppUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addOrEditAppUserAll(AppUser appUser, List<AppUserAddress> customList, List<AppUserSalesman> saleManList) {
        try{
            for (AppUserAddress item:customList) {
                if(item.getIsdefault()==0){
                    appUser.setNickname(item.getName());
                    appUser.setPhoneNum(item.getMobile());
                    break;
                }
            }
            if(appUser.getId()!=null&&appUser.getId()>0){
                if(appUserMapper.updateAppUser(appUser)<=0){ throw new Exception("appUserMapper插入失败");}
            }else{
                appUser.setClientCode("C" + DateUtils.dateTimeNow());
                if(appUserMapper.insertAppUser(appUser)<=0){ throw new Exception("appUserMapper插入失败");}
            }
            appUserAddressMapper.deleteAppUserAddressByUserId(appUser.getId());
            appUserSalesmanMapper.deleteAppUserSalesmanByUserId(appUser.getId());
            for (AppUserAddress item:customList) {
                item.setUid("CT-"+UUID.randomUUID().toString());
                item.setUserId(appUser.getId());
                if(appUserAddressMapper.insertAppUserAddress(item)<=0){ throw new Exception("appUserAddressMapper插入失败");}
            }
            for (AppUserSalesman item:saleManList) {
                item.setSaleCode("Sale-"+UUID.randomUUID().toString());
                item.setUserId(appUser.getId());
                if(appUserSalesmanMapper.insertAppUserSalesman(item)<=0){ throw new Exception("appUserSalesmanMapper插入失败");}
            }
            return 1;
        }catch (Exception ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }

    }

    @Override
    public List<AppUser> selectAppUserListForExcel(AppUser appUser) {
        return appUserMapper.selectAppUserListForExcel(appUser);
    }

    @Override
    public String importUser(List<AppUser> userList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new ServiceException("导入客户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<AppUser> allList=appUserMapper.selectAppUserList(null);
        List<SysDictData> custList=sysDictDataMapper.selectDictDataByType("dict_cust_type");
        List<SysDictData> companyList=sysDictDataMapper.selectDictDataByType("dict_company_type");
        for (AppUser user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                List<AppUser> u=allList.stream().filter(item->item.getCompany()==user.getCompany()).collect(Collectors.toList());
                List<SysDictData> tempCustType=custList.stream().filter(item->item.getDictLabel()==user.getCustTypeName()).collect(Collectors.toList());
                List<SysDictData> tempCompanyType=companyList.stream().filter(item->item.getDictLabel()==user.getCompanyTypeName()).collect(Collectors.toList());
                user.setCustType(Integer.getInteger(!tempCustType.isEmpty()&&tempCustType.size()>0?tempCustType.get(0).getDictValue():"0"));
                user.setCompanyType(Integer.getInteger(!tempCompanyType.isEmpty()&&tempCompanyType.size()>0?tempCompanyType.get(0).getDictValue():"0"));
                if (StringUtils.isNull(u)||u.size()<=0)
                {
//                    BeanValidators.validateWithException(validator, user);
//                    user.setPassword(SecurityUtils.encryptPassword(password));
//                    user.setCreateBy(operName);
                    this.insertAppUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getCompany() + " 导入成功");
                }
                else if (true)
                {
//                    BeanValidators.validateWithException(validator, user);
//                    user.setUpdateBy(operName);
                    this.updateAppUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getCompany() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getCompany() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户" + user.getCompany() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}

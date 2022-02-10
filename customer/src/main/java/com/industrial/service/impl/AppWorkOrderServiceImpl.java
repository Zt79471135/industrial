package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.core.domain.entity.SysDictData;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.core.domain.model.LoginUser;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.utils.DateUtils;
import com.industrial.common.utils.StringUtils;
import com.industrial.common.utils.uuid.UUID;
import com.industrial.domin.*;
import com.industrial.mapper.AppOrderLogMapper;
import com.industrial.mapper.AppOrderMapper;
import com.industrial.mapper.AppWorkOrderMapper;
import com.industrial.service.AppWorkOrderService;
import com.industrial.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2022年01月26日 11:45
 */
@Service
public class AppWorkOrderServiceImpl implements AppWorkOrderService {
    private static final Logger log = LoggerFactory.getLogger(AppWorkOrderServiceImpl.class);
    @Resource
    private AppWorkOrderMapper workOrderMapper;
    @Resource
    private AppOrderLogMapper orderLogMapper;
    @Resource
    private SysUserMapper sysUserMapper;


    @Override
    public boolean payout(Integer workorderId, Integer uid) {
        AppWorkOrder workOrder = new AppWorkOrder();
        workOrder.setHandlingUser(uid);
        return workOrderMapper.updateById(workOrder) == 1;
    }


    @Override
    public List<AppWorkOrder> selectAppWorkOrderList(AppWorkOrder appWorkOrder) {

        return workOrderMapper.selectAppWorkOrderList(appWorkOrder);
    }

    @Override
    public String importUser(List<AppWorkOrder> workList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(workList) || workList.size() == 0) {
            throw new ServiceException("导入客户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<AppWorkOrder> allList = workOrderMapper.selectAppWorkOrderList(null);
//        List<SysDictData> custList=sysDictDataMapper.selectDictDataByType("dict_cust_type");
//        List<SysDictData> companyList=sysDictDataMapper.selectDictDataByType("dict_company_type");
        for (AppWorkOrder work : workList) {
            try {
                // 验证是否存在这个用户
                List<AppWorkOrder> u = allList.stream().filter(item -> item.getNumber() == work.getNumber()).collect(Collectors.toList());
//                List<SysDictData> tempCustType=custList.stream().filter(item->item.getDictLabel()==user.getCustTypeName()).collect(Collectors.toList());
//                List<SysDictData> tempCompanyType=companyList.stream().filter(item->item.getDictLabel()==user.getCompanyTypeName()).collect(Collectors.toList());
//                work.setCustType(Integer.getInteger(!tempCustType.isEmpty()&&tempCustType.size()>0?tempCustType.get(0).getDictValue():"0"));
//                user.setCompanyType(Integer.getInteger(!tempCompanyType.isEmpty()&&tempCompanyType.size()>0?tempCompanyType.get(0).getDictValue():"0"));
                if (StringUtils.isNull(u) || u.size() <= 0) {
//                    BeanValidators.validateWithException(validator, user);
//                    user.setPassword(SecurityUtils.encryptPassword(password));
//                    user.setCreateBy(operName);
                    workOrderMapper.insert(work);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工单编码 " + work.getNumber() + " 导入成功");
                } else if (true) {
                    workOrderMapper.updateById(work);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、工单编码 " + work.getNumber() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、工单编码 " + work.getNumber() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、工单编码" + work.getNumber() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public int deleteAppWorkOrderByIds(Long[] ids) {
        return workOrderMapper.deleteAppWorkOrderByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addOrEditAppWorkOrder(AppWorkOrder appWorkOrder) {
        try {
            if (appWorkOrder.getId() != null && appWorkOrder.getId() > 0) {
                if (workOrderMapper.updateById(appWorkOrder) <= 0) {
                    throw new Exception("workOrderMapper插入失败");
                }
            } else {
                appWorkOrder.setNumber("GD" + DateUtils.dateTimeNow());
                if (workOrderMapper.insert(appWorkOrder) <= 0) {
                    throw new Exception("workOrderMapper插入失败");
                }
            }
            return 1;
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String turnWorkOrder(AppWorkOrder appWorkOrder, LoginUser user) {
        StringBuilder msg = new StringBuilder();
        try {
            for (String id : appWorkOrder.getIds().split(",")
            ) {
                AppWorkOrder model = workOrderMapper.selectById(id);
                SysUser man = sysUserMapper.selectUserById(appWorkOrder.getHandlingUser().longValue());
                if (model == null) {
                    msg.append("||工单:" + id + "|找不到对应工单");
                    continue;
                }
                if (man == null) {
                    msg.append("||工单:" + model.getNumber() + "|找不到要分配的人员");
                    continue;
                }
                if (workOrderMapper.turnWorkOrder(appWorkOrder) <= 0) {
                    throw new Exception("workOrderMapper插入失败");
                }
                //订单动态
                AppOrderLog log = new AppOrderLog();
                log.setOrderNo(model.getRelateId());
                log.setStatus(0);
                log.setLevel(0);
                log.setUserId(user.getUserId().intValue());
                log.setRemark(user.getUser().getNickName() + ":分配工单给" + man.getNickName());
                log.setCreateTime(new Date());
                log.setUpdateTime(new Date());
                if (orderLogMapper.insertAppOrderLog(log) <= 0) {
                    throw new Exception("订单动态orderLogMapper插入失败");
                }
            }
            return msg.toString();
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return msg.toString();
        }
    }
    @Override
    public List<AppWorkOrder> show() {
        QueryWrapper<AppWorkOrder> qw = new QueryWrapper<>();
        return workOrderMapper.selectList(qw);
    }

    @Override
    public boolean delete(Integer workorderId) {
       return workOrderMapper.deleteById(workorderId)==1;
    }
}

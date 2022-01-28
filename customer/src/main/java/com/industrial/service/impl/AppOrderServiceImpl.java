package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.core.domain.model.LoginUser;
import com.industrial.common.dto.CheckDto;
import com.industrial.common.dto.OrderDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.OrderVo;
import com.industrial.common.vo.ShiftVo;
import com.industrial.domin.*;
import com.industrial.mapper.*;
import com.industrial.service.AppOrderService;
import com.industrial.system.mapper.SysDeptMapper;
import com.industrial.system.mapper.SysUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2022年01月19日 9:45
 */
@Service
public class AppOrderServiceImpl implements AppOrderService {
    @Resource
    private AppOrderMapper orderMapper;
    @Resource
    private AppOrderUserMapper orderUserMapper;
    @Resource
    private AppOrderProductMapper orderProductMapper;
    @Resource
    private AppReviewMapper reviewMapper;
    @Resource
    private AppReviewOrderMapper reviewOrderMapper;
    @Resource
    private AppCheckMapper appCheckMapper;
    @Resource
    private AppCheckMainConfigMapper mainConfigMapper;
    @Resource
    private AppOrderLogMapper orderLogMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private AppCheckUserMapper appCheckUserMapper;
    @Resource
    private SysDeptMapper deptMapper;
    @Override
    public OrderDto selectById(Integer orderId) {
        AppOrder order = orderMapper.selectById(orderId);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order, orderDto);
        return orderDto;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public boolean insert(OrderVo orderVo) {
        AppOrder order = new AppOrder();
        BeanUtils.copyProperties(orderVo, order);
        List<Integer> integerList = new ArrayList<>();
        if (orderMapper.insert(order) == 1) {
            /*
              添加商品报价表与订单关联表
             */
            AppOrderProduct orderProduct = new AppOrderProduct();
            orderProduct.setOrderId(order.getId());
            List<Integer> productIdList = orderVo.getProductIdList();
            integerList = productIdList.stream().map(userId -> {
                orderProduct.setUserId(userId);
                return orderProductMapper.insert(orderProduct);
            }).collect(Collectors.toList());
            if (integerList.size() == productIdList.size()) {
                /*
                  添加订单与通知人员关联表
                 */
                AppOrderUser orderUser = new AppOrderUser();
                orderUser.setOrderId(order.getId());
                List<Integer> userIdList = orderVo.getSysUserIdList();
                integerList = userIdList.stream().map(userId -> {
                    orderUser.setUserId(userId);
                    return orderUserMapper.insert(orderUser);
                }).collect(Collectors.toList());
                return integerList.size() == userIdList.size();
            } else {
                throw new ServiceException();
            }
        } else {
            throw new ServiceException();
        }
    }

    /**
     * 通过订单ID修改订单状态
     *
     * @param orderId
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Integer orderId, int status) {
        AppOrder order = new AppOrder();
        order.setId(orderId);
        order.setStatus((byte) status);
        return orderMapper.updateById(order) > 0;
    }

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public boolean updateAffiliate(ShiftVo shiftVo) {
        AppOrder order = new AppOrder();
        order.setId(shiftVo.getObjId());
        order.setAttribuPerson(shiftVo.getUid());
        if (orderMapper.updateById(order) == 1) {
            String record = "订单转交给" + shiftVo.getUname();
            AppReview review = new AppReview();
            review.setMsg(shiftVo.getMsg());
            review.setUid(shiftVo.getFromId());
            review.setRecord(record);
            if (reviewMapper.insert(review) == 1) {
                AppReviewOrder reviewOrder = new AppReviewOrder();
                reviewOrder.setOrderId(order.getId());
                reviewOrder.setReviewId(review.getId());
                if (reviewOrderMapper.insert(reviewOrder) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addTeam(List<Integer> ids, Integer orderId) {
        AppOrderUser orderUser = new AppOrderUser();
        orderUser.setOrderId(orderId);
        List<Integer> integerList = ids.stream().map(userId -> {
            orderUser.setUserId(userId);
            return orderUserMapper.insert(orderUser);
        }).collect(Collectors.toList());
        return ids.size() == integerList.size();
    }

    @Override
    public boolean rejectOrder(String msg, Integer orderId, int status, LoginUser user) {
        if (updateStatus(orderId, status)){
            AppReview review = new AppReview();
            review.setId(Math.toIntExact(user.getUserId()));
            review.setMsg(msg);
            reviewMapper.insert(review);
            AppOrderLog log = new AppOrderLog();
        };
        return false;
    }

    @Override
    public boolean remove(Integer orderId) {
        return false;
    }

    /**
     * 订单审核  关联审核设置，订单动态，审核 返回为空字符串为成功
     *
     * @param userId  用户id
     * @param orderNo 订单编号
     * @return
     */
    @Transactional(rollbackFor = ServiceException.class)
    public String checkOrder(long userId, java.lang.String orderNo) {
        StringBuilder msg = new StringBuilder();
        try {
            AppCheck model = appCheckMapper.selectAppCheckByOrderNo(orderNo);
            //region 查询配置信息
            CheckDto param = new CheckDto();
            param.setId(Long.valueOf(1));
            List<CheckDto> config = mainConfigMapper.selectAppCheckMainSubList(param);
            if (config == null || config.size() <= 0) {
                msg.append("找不到审核的配置信息");
                return msg.toString();
            }
            SysUser curUser = sysUserMapper.selectUserById(userId);
            if (curUser == null) {
                msg.append("找不到对应用户");
                return msg.toString();
            }
            if (config.stream().findFirst().orElse(null).getCheckStatus() == 1) {
                //region 审核配置关闭，插入信息默认审核通过
                if (model != null && model.getId() >= 0) {
                    model.setStatus(1);
                    model.setUpdateTime(new Date());
                    appCheckMapper.updateAppCheck(model);

                } else {
                    model = new AppCheck();
                    model.setOrderId(orderNo);
                    model.setUserId(String.valueOf(userId) + ",");
                    model.setDeleted(0);
                    model.setStatus(1);
                    model.setAuditId(Long.valueOf(1));
                    model.setAuditLevel(Long.valueOf(1));
                    model.setUpdateTime(new Date());
                    model.setCreateTime(new Date());
                    appCheckMapper.insertAppCheck(model);
                }
                //region 订单动态
                AppOrderLog log = new AppOrderLog();
                log.setOrderNo(orderNo);
                log.setStatus(1);
                log.setLevel(0);
                log.setUserId(Math.toIntExact(userId));
                log.setStatus(1);
                log.setRemark(curUser.getNickName() + ":审核配置关闭，自动审核通过。");
                log.setCreateTime(new Date());
                log.setUpdateTime(new Date());
                orderLogMapper.insertAppOrderLog(log);
                //endregion
                return msg.toString();
                //endregion

            }
            //endregion
            if (model == null) {
//            msg.append("找不到该订单的审核信息");
                //region 审核数据 插入 审核id写死为1 层极写死为1 model重新赋值
                CheckDto oneClass = config.stream().filter(item -> item.clevel == 1).collect(Collectors.toList()).stream().findFirst().orElse(null);
                if (oneClass == null) {
                    msg.append("找不到层级1的审核的配置信息");
                    return msg.toString();
                }
                model = new AppCheck();
                model.setOrderId(orderNo);
                model.setUserId(String.valueOf(userId) + ",");
                model.setDeleted(0);
                model.setStatus(2);
                model.setAuditId(Long.valueOf(1));
                model.setAuditLevel(Long.valueOf(1));
                model.setUpdateTime(new Date());
                model.setCreateTime(new Date());
                appCheckMapper.insertAppCheck(model);
                model = appCheckMapper.selectAppCheckByOrderNo(orderNo);
                //endregion
            }
            if (model.getStatus() == 1) {
                msg.append("订单审核已完成");
                return msg.toString();
            }
            if (model.getStatus() == 0) {
                msg.append("订单审核已关闭");
                return msg.toString();
            }
            //正常检测操作
            int level = model.getAuditLevel().intValue();
            int auditId = model.getAuditId().intValue();
            CheckDto cur = config.stream().filter(item -> item.clevel == level && item.getId() == auditId).collect(Collectors.toList()).stream().findFirst().orElse(null);
            if (cur == null) {
                msg.append("找不到该层的审核配置信息 层级=" + level + "|id=" + auditId);
                return msg.toString();
            }
            //1.负责人主管
            //2.多人中一人
            //3.多人会签
            //4.上级审核人
            //region 权限判断
            boolean curFlag = false;
            if (cur.getCheckType() == 1) {
                List<SysUser> upUserList = sysUserMapper.selectUpUserList((curUser.getDeptId() + "," + curUser.getDept().getAncestors()).split(","));
                if (upUserList.stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList()).size() > 0) {
                    curFlag = true;
                }
            } else if (cur.getCheckType() == 2) {
                QueryWrapper<AppCheckUser> qw = new QueryWrapper<>();
                qw.lambda().eq(com.industrial.domin.AppCheckUser::getCheckId, cur.cid);
                List<AppCheckUser> upUserList = appCheckUserMapper.selectList(qw);
                if (upUserList.stream().filter(x -> x.getUserId() == userId).collect(Collectors.toList()).size() > 0) {
                    curFlag = true;
                }
            } else if (cur.getCheckType() == 3) {
                QueryWrapper<AppCheckUser> qw = new QueryWrapper<>();
                qw.lambda().eq(com.industrial.domin.AppCheckUser::getCheckId, cur.cid);
                List<AppCheckUser> upUserList = appCheckUserMapper.selectList(qw);
                List<AppOrderLog> hadUserList = orderLogMapper.selectOrderLogListForCheck(cur.clevel, orderNo);
                if (upUserList.size() == hadUserList.size()) {
                    curFlag = true;
                }
            } else if (cur.getCheckType() == 4) {
                SysDept dept = deptMapper.selectDeptById(curUser.getDept().getParentId());
                if (dept.getLeader() == String.valueOf(userId)) {
                    curFlag = true;
                }
            }
            //endregion
            //region 是否完成该审核 是否有下一级
            if (curFlag) {
                CheckDto nextConfig = config.stream().filter(item -> item.clevel == (level + 1) && item.getId() == auditId).collect(Collectors.toList()).stream().findFirst().orElse(null);
                if (nextConfig == null) {
                    model.setStatus(1);
                    model.setUpdateTime(new Date());
                    appCheckMapper.updateAppCheck(model);
                    //region 订单动态
                    AppOrderLog log = new AppOrderLog();
                    log.setOrderNo(orderNo);
                    log.setStatus(1);
                    log.setLevel(cur.clevel);
                    log.setUserId(Math.toIntExact(userId));
                    log.setRemark(curUser.getNickName() + ":审核通过。开始生成工单");
                    log.setCreateTime(new Date());
                    log.setUpdateTime(new Date());
                    orderLogMapper.insertAppOrderLog(log);
                    //endregion
                    // 这里缺工单生成
                } else {
                    model.setAuditLevel(Long.valueOf(nextConfig.clevel));
                    model.setUpdateTime(new Date());
                    appCheckMapper.updateAppCheck(model);
                    //region 订单动态
                    AppOrderLog log = new AppOrderLog();
                    log.setOrderNo(orderNo);
                    log.setStatus(1);
                    log.setLevel(cur.clevel);
                    log.setUserId(Math.toIntExact(userId));
                    log.setRemark(curUser.getNickName() + ":审核通过。进入第" + nextConfig.clevel + "级审核");
                    log.setCreateTime(new Date());
                    log.setUpdateTime(new Date());
                    orderLogMapper.insertAppOrderLog(log);
                    //endregion
                }
            }
            //endregion
            return msg.toString();

        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return msg.toString();
        }
    }

}

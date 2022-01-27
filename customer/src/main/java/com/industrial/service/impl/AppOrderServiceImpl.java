package com.industrial.service.impl;

import com.industrial.common.annotation.Log;
import com.industrial.common.dto.CheckDto;
import com.industrial.common.dto.OrderDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.OrderVo;
import com.industrial.domin.*;
import com.industrial.mapper.*;
import com.industrial.service.AppOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AppCheckMapper appCheckMapper;
    @Resource
    private AppCheckMainConfigMapper mainConfigMapper;
    @Resource
    private AppOrderLogMapper orderLogMapper;

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
              添加商品与订单关联表
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
     * @param orderId
     * @param id
     * @return
     */
    @Override
    public boolean updateStatus(Integer orderId, int id) {
        AppOrder order = new AppOrder();
        order.setId(orderId);
        order.setStatus((byte)id);
        return orderMapper.updateById(order)==1;
    }


    @Transactional(rollbackFor = ServiceException.class)
    public java.lang.String checkOrder(long userId, java.lang.String orderNo) {
        StringBuilder msg=new StringBuilder();
        try{
            AppCheck model=appCheckMapper.selectAppCheckByOrderNo(orderNo);
            //region 查询配置信息
            CheckDto param=new CheckDto();
            param.setId(Long.valueOf(1));
            List<CheckDto> config=mainConfigMapper.selectAppCheckMainSubList(param);
            if(config==null||config.size()<=0){
                msg.append("找不到审核的配置信息");
                return msg.toString();
            }
            // 审核配置关闭，插入信息默认审核通过
            if(config.stream().findFirst().orElse(null).getCheckStatus()==1){
                if(model!=null&&model.getId()>=0){
                    model.setStatus(1);
                    model.setUpdateTime(new Date());
                    appCheckMapper.updateAppCheck(model);
                    AppOrderLog log =new AppOrderLog();
                    log.setOrderNo(orderNo);
                    log.setStatus(1);
                    orderLogMapper.insertAppOrderLog(log);
                }else{
                    model=new AppCheck();
                    model.setOrderId(orderNo);
                    model.setUserId(String.valueOf(userId)+",");
                    model.setDeleted(0);
                    model.setStatus(1);
                    model.setAuditId(Long.valueOf(1));
                    model.setAuditLevel(Long.valueOf(1));
                    model.setUpdateTime(new Date());
                    model.setCreateTime(new Date());
                    appCheckMapper.insertAppCheck(model);
                }
            }
            //endregion
            if(model==null){
//            msg.append("找不到该订单的审核信息");
                //region 审核数据 插入 审核id写死为1 层极写死为1
                CheckDto oneClass=config!=null?config.stream().filter(item->item.clevel==1).collect(Collectors.toList()).stream().findFirst().orElse(null):null;
                if(oneClass==null){
                    msg.append("找不到层级1的审核的配置信息");
                    return msg.toString();
                }
                model=new AppCheck();
                model.setOrderId(orderNo);
                model.setUserId(String.valueOf(userId)+",");
                model.setDeleted(0);
                model.setStatus(2);
                model.setAuditId(Long.valueOf(1));
                model.setAuditLevel(Long.valueOf(1));
                model.setUpdateTime(new Date());
                model.setCreateTime(new Date());
                appCheckMapper.insertAppCheck(model);
                //endregion
            }
            if(model.getStatus()==1){
                msg.append("订单审核已完成");
                return  msg.toString();
            }
            if(model.getStatus()==0){
                msg.append("订单审核已关闭");
                return  msg.toString();
            }
            //正常检测操作

            return  msg.toString();

        }catch (Exception ex){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return  msg.toString();
        }



    }


}

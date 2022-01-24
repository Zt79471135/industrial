package com.industrial.service.impl;

import com.industrial.common.dto.OrderDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.FollowVo;
import com.industrial.common.vo.OrderVo;
import com.industrial.domin.*;
import com.industrial.mapper.AppFollowMapper;
import com.industrial.mapper.AppOrderMapper;
import com.industrial.mapper.AppOrderProductMapper;
import com.industrial.mapper.AppOrderUserMapper;
import com.industrial.service.AppOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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
    private AppFollowMapper followMapper;

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

    @Override
    public boolean insertFollow(FollowVo follow) {
        AppFollow appFollow = new AppFollow();
        BeanUtils.copyProperties(follow, appFollow);
        long aheadTime = follow.getFollowTime().getTime() - follow.getAheadTime();
        Date date = new Date(aheadTime);
        appFollow.setReminderTime(date);
        return followMapper.insert(appFollow) == 1;
    }
}

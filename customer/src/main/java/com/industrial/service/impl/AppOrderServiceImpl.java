package com.industrial.service.impl;

import com.industrial.common.dto.OrderDto;
import com.industrial.common.vo.OrderVo;
import com.industrial.entity.AppOrder;
import com.industrial.mapper.AppOrderMapper;
import com.industrial.service.AppOrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhu
 * @date 2022年01月19日 9:45
 */
@Service
public class AppOrderServiceImpl implements AppOrderService {
    @Resource
    private AppOrderMapper orderMapper;
    @Override
    public OrderDto selectById(Integer orderId) {
        AppOrder order = orderMapper.selectById(orderId);
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(order,orderDto);
        return orderDto;
    }

    @Override
    public boolean insert(OrderVo orderVo) {
        AppOrder order = new AppOrder();
        BeanUtils.copyProperties(orderVo,order);
        return orderMapper.insert(order) == 1;
    }
}

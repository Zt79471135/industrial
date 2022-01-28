package com.industrial.service.impl;

import com.industrial.common.dto.OrderDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.FollowVo;
import com.industrial.common.vo.OrderVo;
import com.industrial.common.vo.ShiftVo;
import com.industrial.domin.*;
import com.industrial.mapper.*;
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
    private AppReviewMapper reviewMapper;
    @Resource
    private AppReviewOrderMapper reviewOrderMapper;

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
        return orderMapper.updateById(order) == 1;
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

    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public boolean checkOrder(long userId,long orderId) {
        return  false;

    }


}

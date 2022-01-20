package com.industrial.service;

import com.industrial.common.dto.OrderDto;
import com.industrial.common.vo.OrderVo;

/**
 * @author zhu
 * @date 2022年01月19日 9:44
 */

public interface AppOrderService {
    /**
     *根据ID查询订单详情
     * @param orderId
     * @return
     */
    OrderDto selectById(Integer orderId);

    /**
     * 订单添加
     * @param orderVo
     * @return
     */
    boolean insert(OrderVo orderVo);
}

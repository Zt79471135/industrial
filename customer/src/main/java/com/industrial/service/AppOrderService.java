package com.industrial.service;

import com.industrial.common.core.domain.model.LoginUser;
import com.industrial.common.dto.OrderDto;
import com.industrial.common.vo.OrderVo;
import com.industrial.common.vo.ShiftVo;

import java.util.List;

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
    /**
     * 更改订单状态
     *
     * @param orderId
     * @param id
     * @return
     */
    boolean updateStatus(Integer orderId, int id);
    /**
     * 订单转交
     * @param shiftVo
     * @return
     */
    boolean updateAffiliate(ShiftVo shiftVo);
    /**
     * 添加协作人员
     * @param ids
     * @param orderId
     * @return
     */
    boolean addTeam(List<Integer> ids, Integer orderId);
    /**
     * 驳回审核
     * @param msg
     * @param orderId
     * @param status
     * @param user
     * @return
     */
    boolean rejectOrder(String msg, Integer orderId, int status, LoginUser user);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    boolean remove(Integer orderId);
}

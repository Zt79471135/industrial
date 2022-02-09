package com.industrial.service;

import com.industrial.domin.AppCheckMainConfig;
import com.industrial.domin.AppWorkOrder;

import java.util.List;

/**
 * @author zhu
 * @date 2022年01月26日 11:45
 */
public interface AppWorkOrderService {
    /**
     * 工单派发
     * @param workorderId
     * @param uid
     * @return
     */
    boolean payout(Integer workorderId, Integer uid);

    /**
     * 工单展示
     */
    void show();

    List<AppWorkOrder> selectAppWorkOrderList(AppWorkOrder appWorkOrder);
}

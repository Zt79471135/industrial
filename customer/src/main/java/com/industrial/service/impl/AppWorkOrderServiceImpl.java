package com.industrial.service.impl;

import com.industrial.service.AppWorkOrderService;
import org.springframework.stereotype.Service;

/**
 * @author zhu
 * @date 2022年01月26日 11:45
 */
@Service
public class AppWorkOrderServiceImpl implements AppWorkOrderService {
    @Override
    public boolean payout(Integer workorderId, Integer uid) {

        return false;
    }

    @Override
    public void show() {

    }
}

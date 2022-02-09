package com.industrial.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.domin.AppActivity;
import com.industrial.domin.AppCheckMainConfig;
import com.industrial.domin.AppWorkOrder;
import com.industrial.mapper.AppOrderMapper;
import com.industrial.mapper.AppWorkOrderMapper;
import com.industrial.service.AppWorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhu
 * @date 2022年01月26日 11:45
 */
@Service
public class AppWorkOrderServiceImpl implements AppWorkOrderService {
    @Resource
    private AppWorkOrderMapper workOrderMapper;


    @Override
    public boolean payout(Integer workorderId, Integer uid) {

        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public List<AppWorkOrder> selectAppWorkOrderList(AppWorkOrder appWorkOrder) {
        QueryWrapper<AppWorkOrder> qw = new QueryWrapper<>();

        //状态
        if (appWorkOrder.getStatus() != 0) {
            qw.lambda().eq(com.industrial.domin.AppWorkOrder::getStatus, appWorkOrder.getStatus());
        }
        //类型
        if (appWorkOrder.getType() != 0) {
            qw.lambda().eq(com.industrial.domin.AppWorkOrder::getType, appWorkOrder.getType());
        }
        //紧急程度
        if (appWorkOrder.getExtent() != 0) {
            qw.lambda().eq(com.industrial.domin.AppWorkOrder::getExtent, appWorkOrder.getExtent());
        }
        if (appWorkOrder.getStartUser() != 0) {
            qw.lambda().eq(com.industrial.domin.AppWorkOrder::getStartUser, appWorkOrder.getStartUser());
        }
        if (appWorkOrder.getHandlingUser() != 0) {
            qw.lambda().eq(com.industrial.domin.AppWorkOrder::getHandlingUser, appWorkOrder.getHandlingUser());
        }
        //等待时间
//        if (appWorkOrder.getCreateTime() != null) {
//            qw.lambda().last("AND date_format(create_time,'%y%m%d') &lt;= date_format("+appWorkOrder.getCreateTime().getTime()+",'%y%m%d')");
//        }
        return workOrderMapper.selectList(qw);
    }
}

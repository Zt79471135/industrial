package com.industrial.controller;

import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.core.page.TableDataInfo;
import com.industrial.common.dto.OrderDto;
import com.industrial.domin.AppCheckMainConfig;
import com.industrial.domin.AppOrder;
import com.industrial.domin.AppWorkOrder;
import com.industrial.service.AppWorkOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhu
 * @date 2022年01月26日 11:46
 */
@RestController
@RequestMapping("workorder")
public class AppWorkOrderController extends BaseController {
    @Resource
    private AppWorkOrderService workOrderService;

    /**
     * 工单派发
     */
    @PostMapping("payout")
    public ResponseResult<String> payout(Integer workorderId, Integer uid) {
        ResponseResult<String> result = null;
        if (workOrderService.payout(workorderId, uid)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 工单展示
     */
    @PostMapping("show")
    public ResponseResult<List<AppWorkOrder>> show() {
        workOrderService.show();
        return null;
    }

    @GetMapping("/list")
    public TableDataInfo list(AppWorkOrder appWorkOrder) {
        startPage();
        List<AppWorkOrder> list = workOrderService.selectAppWorkOrderList(appWorkOrder);
        return getDataTable(list);
    }
}

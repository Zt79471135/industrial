package com.industrial.controller;

import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.OrderDto;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.vo.OrderVo;
import com.industrial.entity.AppOrder;
import com.industrial.service.AppOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhu
 * @date 2022年01月19日 9:45
 */
@RestController
@RequestMapping("/order")
public class AppOrderController {
    @Resource
    private AppOrderService orderService;
    /**
     * 订单查询
     */
    @GetMapping("/select")
    public ResponseResult<AppOrder> selectById(@RequestParam Integer orderId){
        ResponseResult<AppOrder> result = null;
        OrderDto orderDto = orderService.selectById(orderId);
        result = ResponseResult.success(orderDto);
        return result;
    }
    /**
     * 订单添加
     */
    @PostMapping("add")
    public ResponseResult add(@RequestBody OrderVo orderVo ){
        ResponseResult result = null;
        if (orderService.insert(orderVo)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
}

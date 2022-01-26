package com.industrial.controller;

import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.core.domain.model.LoginUser;
import com.industrial.common.dto.OrderDto;
import com.industrial.common.vo.CheckVo;
import com.industrial.common.vo.FollowVo;
import com.industrial.common.vo.OrderVo;
import com.industrial.domin.AppFollow;
import com.industrial.domin.AppOrder;
import com.industrial.service.AppOrderService;
import com.industrial.service.IAppFollowService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author zhu
 * @date 2022年01月19日 9:45
 */
@RestController
@RequestMapping("/order")
public class AppOrderController extends BaseController {
    @Resource
    private AppOrderService orderService;
    @Resource
    private IAppFollowService followService;
//    @Resource
//    private AppCheckService checkService;
    public static final Integer  CHECK_STATUS_SUCCESS = 3;
    /**
     * 用户下单
     * status = 2 待审批
     * 审批  成功 status = 3  待派单  新建工单  业务员确认 成功 status = 4
     *       失败 status = 2  待审批  发送消息通知客户
     * 派单   status = 4  待实施
     * 实施   status = 5  待评价
     * 评价   status = 6
     */
    /**
     * 新建工单
     * status = 3 待派发
     * status = 4 待实施
     *
     */
    /**
     * 订单查询
     */
    @GetMapping("/select")
    public ResponseResult<AppOrder> selectById(@RequestParam Integer orderId) {
        ResponseResult<AppOrder> result = null;
        OrderDto orderDto = orderService.selectById(orderId);
        result = ResponseResult.success(orderDto);
        return result;
    }

    /**
     * 订单添加
     */
    @PostMapping("add")
    public ResponseResult<String> add(@RequestBody OrderVo orderVo, @RequestBody FollowVo follow) {
        ResponseResult<String> result = null;
        if (orderService.insert(orderVo)) {
            if (follow.getFollowOrder() != 0) {
                /*
                  建立跟进任务
                 */
                if (followService.insertFollow(follow)) {
                    result = ResponseResult.success();
                } else {
                    result = ResponseResult.error(ResponseCode.ERROR);
                }
                return result;
            }
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 订单审核
     */
//    @PostMapping("check")
//    public ResponseResult<String> check(@RequestBody CheckVo checkVo) {
//        ResponseResult<String> result = null;
//        if (checkService.updateStatus(checkVo)) {
//            if (orderService.updateStatus(checkVo.getId(),CHECK_STATUS_SUCCESS)) {
//                result = ResponseResult.success();
//            } else {
//                result = ResponseResult.error(ResponseCode.ERROR);
//            }
//
//        } else {
//            result = ResponseResult.error(ResponseCode.ERROR);
//        }
//        return result;
//    }
}

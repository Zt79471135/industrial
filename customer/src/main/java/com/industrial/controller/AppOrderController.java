package com.industrial.controller;

import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.OrderDto;
import com.industrial.common.dto.UserDto;
import com.industrial.common.vo.FollowVo;
import com.industrial.common.vo.OrderVo;
import com.industrial.common.vo.ShiftVo;
import com.industrial.domin.AppOrder;
import com.industrial.domin.User;
import com.industrial.service.AppOrderService;
import com.industrial.service.IAppFollowService;
import com.industrial.system.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    /**
     * 审核成功
     */
    public static final Integer CHECK_STATUS_SUCCESS = 3;
    /**
     * 待审核
     */
    public static final Integer TO_AUDIT = 2;
    /**
     * 已取消
     */
    public static final Integer CANCELED = 1;
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
    @PostMapping("/add")
    public ResponseResult<String> add(@RequestBody OrderVo orderVo, @RequestBody FollowVo follow) {
        ResponseResult<String> result = null;
        if (orderService.insert(orderVo)) {
            if (follow.getFollowId() != 0) {
                /*
                  建立跟进任务
                 */
                if (followService.insertFollow(follow,AppFollowController.ORDER_FOLLOW)) {
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
     * 转移订单
     */
    @PostMapping
    public ResponseResult<String> shift(@RequestParam ShiftVo shiftVo) {
        ResponseResult<String> result = null;
        shiftVo.setFromId(Math.toIntExact(getUserId()));
        shiftVo.setFromName(getUsername());
        if (orderService.updateAffiliate(shiftVo)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 通知人员
     */
    @GetMapping("select/team")
    public ResponseResult<List<UserDto>> selectTeam() {
        List<UserDto> userList = followService.selectTeam();
        return ResponseResult.success(userList);
    }

    /**
     * 添加协作人员
     */
    @PostMapping("add/team")
    public ResponseResult<String> addTeam(@RequestBody List<Integer> ids, @RequestParam Integer orderId) {
        ResponseResult<String> result = null;
        if (orderService.addTeam(ids, orderId)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 驳回审核
     */
    @PostMapping("reject")
    public ResponseResult<String> reject(@RequestParam String msg, Integer orderId) {
        ResponseResult<String> result = null;
        if (orderService.rejectOrder(msg, orderId, TO_AUDIT, getLoginUser())) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 删除订单
     */
    @DeleteMapping("remove")
    public ResponseResult<String> remove(@RequestParam Integer orderId) {
        ResponseResult<String> result = null;
        if (orderService.remove(orderId)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
}

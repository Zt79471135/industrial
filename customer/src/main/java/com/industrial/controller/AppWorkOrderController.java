package com.industrial.controller;

import com.industrial.common.annotation.Log;
import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.core.page.TableDataInfo;
import com.industrial.common.dto.AppUserDto;
import com.industrial.common.dto.OrderDto;
import com.industrial.common.enums.BusinessType;
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.domin.AppCheckMainConfig;
import com.industrial.domin.AppOrder;
import com.industrial.domin.AppUser;
import com.industrial.domin.AppWorkOrder;
import com.industrial.service.AppWorkOrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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


    @GetMapping("/list")
    public TableDataInfo list(AppWorkOrder appWorkOrder) {
        startPage();
        if (appWorkOrder.menuType == 2) {
            appWorkOrder.setStartUser(getUserId().intValue());
        }
        if (appWorkOrder.menuType == 3) {
            appWorkOrder.setHandlingUser(getUserId().intValue());
        }
        if (appWorkOrder.menuType == 4) {
            appWorkOrder.setCcUser(getUserId().toString());
        }
        List<AppWorkOrder> list = workOrderService.selectAppWorkOrderList(appWorkOrder);
        return getDataTable(list);
    }

    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppWorkOrder appWorkOrder) {
        return toAjax(workOrderService.addOrEditAppWorkOrder(appWorkOrder));
    }

    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PutMapping("/turn")
    public AjaxResult turn(@RequestBody AppWorkOrder appWorkOrder) {
        String msg = workOrderService.turnWorkOrder(appWorkOrder, getLoginUser());
        return AjaxResult.success(msg == "" ? "分配成功" : msg);
    }

    @Log(title = "工单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppWorkOrder appWorkOrder) {
        List<AppWorkOrder> list = workOrderService.selectAppWorkOrderList(appWorkOrder);
        ExcelUtil<AppWorkOrder> util = new ExcelUtil<AppWorkOrder>(AppWorkOrder.class);
        util.exportExcel(response, list, "工单管理数据");
    }

    @Log(title = "工单管理", businessType = BusinessType.IMPORT)
//    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<AppWorkOrder> util = new ExcelUtil<AppWorkOrder>(AppWorkOrder.class);
        List<AppWorkOrder> workList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = workOrderService.importUser(workList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @Log(title = "工单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(workOrderService.deleteAppWorkOrderByIds(ids));
    }


}

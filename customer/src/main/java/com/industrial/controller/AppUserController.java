package com.industrial.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.industrial.common.dto.AppUserDto;
import com.industrial.domin.AppUserAddress;
import com.industrial.domin.AppUserSalesman;
import com.industrial.service.IAppUserAddressService;
import com.industrial.service.IAppUserSalesmanService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.industrial.common.annotation.Log;
import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.enums.BusinessType;
import com.industrial.domin.AppUser;
import com.industrial.service.IAppUserService;
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.common.core.page.TableDataInfo;

/**
 * 客户管理Controller
 * 
 * @author lsk
 * @date 2022-01-18
 */
@RestController
@RequestMapping("/appuser")
public class AppUserController extends BaseController
{
    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private IAppUserAddressService appUserAddressService;
    @Autowired
    private IAppUserSalesmanService appUserSalesmanService;

    /**
     * 查询客户管理列表
     */
//    @PreAuthorize("@ss.hasPermi('appuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppUser appUser)
    {
        startPage();
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
//    @PreAuthorize("@ss.hasPermi('appuser:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppUser appUser)
    {
        List<AppUser> list = appUserService.selectAppUserList(appUser);
        ExcelUtil<AppUser> util = new ExcelUtil<AppUser>(AppUser.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('appuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        AppUser model=appUserService.selectAppUserById(id);
        List<AppUserAddress> address=appUserAddressService.selectAppUserAddressByUserId(id);
        List<AppUserSalesman> salesman=appUserSalesmanService.selectAppUserSalesmanByUserId(id);
        AppUserDto appUserDto=new AppUserDto();
        appUserDto.appUser=model;
        appUserDto.customList=address;
        appUserDto.saleManList=salesman;
        return AjaxResult.success(appUserDto);
    }

    /**
     * 新增客户管理
     */
//    @PreAuthorize("@ss.hasPermi('appuser:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppUserDto appUserDto)
    {
        return toAjax(appUserService.addOrEditAppUserAll(appUserDto.appUser,appUserDto.customList,appUserDto.saleManList));
    }

    /**
     * 修改客户管理
     */
//    @PreAuthorize("@ss.hasPermi('appuser:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppUserDto appUserDto)
    {
        return toAjax(appUserService.addOrEditAppUserAll(appUserDto.appUser,appUserDto.customList,appUserDto.saleManList));
    }

    @Log(title = "客户管理更改状态", businessType = BusinessType.UPDATE)
    @PutMapping("/state")
    public AjaxResult updateState(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.updateAppUser(appUser));
    }

    /**
     * 删除客户管理
     */
//    @PreAuthorize("@ss.hasPermi('appuser:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(appUserService.deleteAppUserByIds(ids));
    }
}

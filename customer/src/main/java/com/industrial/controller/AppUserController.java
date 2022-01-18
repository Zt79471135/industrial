package com.industrial.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.industrial.common.annotation.Log;
import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.enums.BusinessType;
import com.industrial.entity.AppUser;
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
@RequestMapping("/appuser/appuser")
public class AppUserController extends BaseController
{
    @Autowired
    private IAppUserService appUserService;

    /**
     * 查询客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('appuser:appuser:list')")
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
    @PreAuthorize("@ss.hasPermi('appuser:appuser:export')")
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
    @PreAuthorize("@ss.hasPermi('appuser:appuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appUserService.selectAppUserById(id));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('appuser:appuser:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.insertAppUser(appUser));
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('appuser:appuser:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AppUser appUser)
    {
        return toAjax(appUserService.updateAppUser(appUser));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('appuser:appuser:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(appUserService.deleteAppUserByIds(ids));
    }
}

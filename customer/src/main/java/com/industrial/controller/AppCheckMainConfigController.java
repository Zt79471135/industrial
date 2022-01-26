package com.industrial.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.CheckConfigDto;
import com.industrial.domin.AppCheckUser;
import com.industrial.domin.AppSubCheckConfig;
import com.industrial.mapper.AppCheckUserMapper;
import com.industrial.service.IAppSubCheckConfigService;
import org.springframework.beans.BeanUtils;
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
import com.industrial.domin.AppCheckMainConfig;
import com.industrial.service.IAppCheckMainConfigService;
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.common.core.page.TableDataInfo;

/**
 * 审核设置主Controller
 * 
 * @author lishenkang
 * @date 2022-01-24
 */
@RestController
@RequestMapping("/checkmainconfig")
public class AppCheckMainConfigController extends BaseController
{
    @Autowired
    private IAppCheckMainConfigService appCheckMainConfigService;
    @Autowired
    private IAppSubCheckConfigService appSubCheckConfigService;
    @Resource
    private AppCheckUserMapper appCheckUserService;

    /**
     * 查询审核设置主列表
     */
//    @PreAuthorize("@ss.hasPermi('checkmainconfig:checkmainconfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppCheckMainConfig appCheckmainconfig)
    {
        startPage();
        List<AppCheckMainConfig> list = appCheckMainConfigService.selectAppCheckMainConfigList(appCheckmainconfig);
        return getDataTable(list);
    }

    @GetMapping("/all")
    public TableDataInfo listall()
    {
        List<AppCheckMainConfig> mainList = appCheckMainConfigService.selectAppCheckMainConfigList(null);
        List<AppSubCheckConfig> sublist=appSubCheckConfigService.selectAppSubCheckConfigList(null);
        List<AppCheckUser> userlist=appCheckUserService.selectList(null);
        List<CheckConfigDto> list=new ArrayList<CheckConfigDto>();
        for (AppCheckMainConfig item:mainList) {
            CheckConfigDto model=new CheckConfigDto();
            BeanUtils.copyProperties(item,model);
            for (AppSubCheckConfig subItem:sublist) {
                if(item.getId()==subItem.getConfigId()){
                    if(model.subList.isEmpty()){
                        model.subList=new ArrayList<>();
                        for (AppCheckUser user:userlist) {
                            if(user.getCheckId().intValue()==subItem.getId()){
                                subItem.setAdminList(subItem.getAdminList()+user.getUserId()+",");
                            }
                        }
                    }
                    subItem.setAdminList(subItem.getAdminList().length()>0?
                            subItem.getAdminList().substring(0,subItem.getAdminList().length()-1):subItem.getAdminList());
                    model.subList.add(subItem);
                }
            }
            list.add(model);
        };
        return getDataTable(list);
    }

    /**
     * 导出审核设置主列表
     */
//    @PreAuthorize("@ss.hasPermi('checkmainconfig:checkmainconfig:export')")
    @Log(title = "审核设置主", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppCheckMainConfig appCheckMainConfig)
    {
        List<AppCheckMainConfig> list = appCheckMainConfigService.selectAppCheckMainConfigList(appCheckMainConfig);
        ExcelUtil<AppCheckMainConfig> util = new ExcelUtil<AppCheckMainConfig>(AppCheckMainConfig.class);
        util.exportExcel(response, list, "审核设置主数据");
    }

    /**
     * 获取审核设置主详细信息
     */
//    @PreAuthorize("@ss.hasPermi('checkmainconfig:checkmainconfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appCheckMainConfigService.selectAppCheckMainConfigById(id));
    }

    /**
     * 新增审核设置主
     */
//    @PreAuthorize("@ss.hasPermi('checkmainconfig:checkmainconfig:add')")
    @Log(title = "审核设置主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AppCheckMainConfig appCheckmainconfig)
    {
        return toAjax(appCheckMainConfigService.insertAppCheckMainConfig(appCheckmainconfig));
    }

    /**
     * 修改审核设置主
     */
//    @PreAuthorize("@ss.hasPermi('checkmainconfig:checkmainconfig:edit')")
    @Log(title = "审核设置主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckConfigDto checkConfigDto)
    {
        return toAjax(appCheckMainConfigService.updateCheckConfigDto(checkConfigDto));
    }

    @Log(title = "修改审批状态", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody AppCheckMainConfig appCheckmainconfig)
    {
        return toAjax(appCheckMainConfigService.updateAppCheckMainConfig(appCheckmainconfig));
    }

    /**
     * 删除审核设置主
     */
//    @PreAuthorize("@ss.hasPermi('checkmainconfig:checkmainconfig:remove')")
    @Log(title = "审核设置主", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(appCheckMainConfigService.deleteAppCheckMainConfigByIds(ids));
    }
}

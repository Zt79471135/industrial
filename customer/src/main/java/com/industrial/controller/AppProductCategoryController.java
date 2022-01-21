package com.industrial.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.common.core.domain.entity.SysUser;
import com.industrial.common.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.industrial.common.annotation.Log;
import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.enums.BusinessType;
import com.industrial.domin.AppProductCategory;
import com.industrial.service.IAppProductCategoryService;
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.common.core.page.TableDataInfo;
import com.industrial.common.vo.updateTypeVo;
import com.industrial.common.vo.UpdateDeletedVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品分类Controller
 * 
 * @author chenjh
 * @date 2022-01-19
 */
@RestController
@RequestMapping("/category")
public class AppProductCategoryController extends BaseController
{
    @Autowired
    private IAppProductCategoryService appProductCategoryService;

    /**
     * 查询商品分类列表
     */
//    @PreAuthorize("@ss.hasPermi('category:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppProductCategory appProductCategory)
    {
        startPage();
        //List<CategoryDto> list = appProductCategoryService.selectAppProductCategoryList(CategoryName,CategoryCode);
        List<CategoryDto> list = appProductCategoryService.selectAppProductCategoryList(appProductCategory);
        return getDataTable(list);
    }



    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<AppProductCategory> util = new ExcelUtil<AppProductCategory>(AppProductCategory.class);
        util.importTemplateExcel(response, "商品分类数据");
    }

    @Log(title = "商品分类", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<AppProductCategory> util = new ExcelUtil<AppProductCategory>(AppProductCategory.class);
        List<AppProductCategory> List = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message ="";
        // message = userService.importUser(List, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 导出商品分类列表
     */
    //@PreAuthorize("@ss.hasPermi('category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppProductCategory appProductCategory)
    {
        List<CategoryDto> list = appProductCategoryService.selectAppProductCategoryList(appProductCategory);
        ExcelUtil<CategoryDto> util = new ExcelUtil<CategoryDto>(CategoryDto.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    //@PreAuthorize("@ss.hasPermi('category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appProductCategoryService.selectAppProductCategoryById(id));
    }

    /**
     * 新增商品分类
     */
    //@PreAuthorize("@ss.hasPermi('category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated  @RequestBody AppProductCategory appProductCategory)
    {
        return toAjax(appProductCategoryService.insertAppProductCategory(appProductCategory));
    }

    /**
     * 修改商品分类
     */
    //@PreAuthorize("@ss.hasPermi('category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@Validated @RequestBody AppProductCategory appProductCategory)
    {
        return toAjax(appProductCategoryService.updateAppProductCategory(appProductCategory));
    }

    /**
     * 删除商品分类
     */
    //@PreAuthorize("@ss.hasPermi('category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(appProductCategoryService.deleteAppProductCategoryByIds(ids));
    }

    /**
     * 修改所属分类
     */
    @PostMapping("/updateType")
    public AjaxResult updateType(@RequestBody updateTypeVo typeVo )
    {
        return toAjax(appProductCategoryService.updateType(typeVo));
    }

    /**
     * 修改启用状态
     */
    @PostMapping("/updateDeleted")
    public AjaxResult updateDeleted(@RequestBody UpdateDeletedVo deletedVo)
    {
        return toAjax(appProductCategoryService.updateDeleted(deletedVo));
    }

    /**
     * 状态修改
     */
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody AppProductCategory category)
    {
        return toAjax(appProductCategoryService.changeStatus(category));
    }

}

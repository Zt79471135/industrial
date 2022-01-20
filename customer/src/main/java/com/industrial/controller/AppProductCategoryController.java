package com.industrial.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.industrial.common.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.industrial.common.annotation.Log;
import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.enums.BusinessType;
import com.industrial.domin.AppProductCategory;
import com.industrial.service.IAppProductCategoryService;
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.common.core.page.TableDataInfo;

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
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam String CategoryName,@RequestParam String CategoryCode)
    {
        startPage();
        List<CategoryDto> list = appProductCategoryService.selectAppProductCategoryList(CategoryName,CategoryCode);
        return getDataTable(list);
    }

    /**
     * 导出商品分类列表
     */
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestParam String CategoryName,@RequestParam String CategoryCode)
    {
        List<CategoryDto> list = appProductCategoryService.selectAppProductCategoryList(CategoryName,CategoryCode);
        ExcelUtil<CategoryDto> util = new ExcelUtil<CategoryDto>(CategoryDto.class);
        util.exportExcel(response, list, "商品分类数据");
    }

    /**
     * 获取商品分类详细信息
     */
    @GetMapping("/details")
    public AjaxResult getInfo(@RequestParam Long id)
    {
        return AjaxResult.success(appProductCategoryService.selectAppProductCategoryById(id));
    }

    /**
     * 新增商品分类
     */
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PutMapping("/add")
    public AjaxResult add(@RequestBody AppProductCategory appProductCategory)
    {
        return toAjax(appProductCategoryService.insertAppProductCategory(appProductCategory));
    }

    /**
     * 修改商品分类
     */
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody AppProductCategory appProductCategory)
    {
        return toAjax(appProductCategoryService.updateAppProductCategory(appProductCategory));
    }

    /**
     * 删除商品分类
     */
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/remove")
    public AjaxResult remove(@RequestParam Long[] ids)
    {
        return toAjax(appProductCategoryService.deleteAppProductCategoryByIds(ids));
    }

}

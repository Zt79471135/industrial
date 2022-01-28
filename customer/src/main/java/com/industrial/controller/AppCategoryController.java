package com.industrial.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.industrial.common.ChineseCharacterUtil;
import com.industrial.common.constant.UserConstants;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.common.utils.StringUtils;
import com.industrial.domin.AppCategory;
import com.industrial.service.IAppCategoryService;
import com.industrial.common.vo.UpdateDeletedVo;
import com.industrial.common.vo.updateTypeVo;
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
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import javax.servlet.http.HttpServletResponse;


/**
 * 描述:
 * 日期：2022-01-24
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
@RestController
@RequestMapping("/category")
public class AppCategoryController extends BaseController
{
    @Autowired
    private IAppCategoryService appCategoryService;

    /**
     * 查询商品分类列表
     */
    //@PreAuthorize("@ss.hasPermi('category:list')")
    @GetMapping("/list")
    public TableDataInfo list(AppCategory appCategory)
    {
        startPage();
        List<AppCategory> list = appCategoryService.selectAppCategoryList(appCategory);
        return getDataTable(list);
    }

    /**
     * 获取商品分类详细信息
     */
    //@PreAuthorize("@ss.hasPermi('category:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(appCategoryService.selectAppCategoryById(id));
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<AppCategory> util = new ExcelUtil<AppCategory>(AppCategory.class);
        util.importTemplateExcel(response, "商品分类数据");
    }

    @Log(title = "商品分类", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<AppCategory> util = new ExcelUtil<AppCategory>(AppCategory.class);
        List<AppCategory> List = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message ="";
        message = appCategoryService.importData(List, updateSupport, operName);
        return AjaxResult.success(message);
    }


    /**
     * 导出商品分类列表
     */
    //@PreAuthorize("@ss.hasPermi('category:export')")
    @Log(title = "商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AppCategory appCategory)
    {
        List<AppCategory> list = appCategoryService.selectAppCategoryList(appCategory);
        ExcelUtil<AppCategory> util = new ExcelUtil<AppCategory>(AppCategory.class);
        util.exportExcel(response, list, "商品分类数据");
    }


    /**
     * 状态修改
     */
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody AppCategory category)
    {
        return toAjax(appCategoryService.changeStatus(category));
    }

    /**
     * 新增商品分类
     */
    //@PreAuthorize("@ss.hasPermi('category:add')")
    @Log(title = "商品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody AppCategory appCategory)
    {
        if (UserConstants.NOT_UNIQUE.equals(appCategoryService.checkDeptNameUnique(appCategory)))
        {
            return AjaxResult.error("新增分类'" + appCategory.getCategoryName() + "'失败，分类名称已存在");
        }
        // 4位随机数
        long round = Math.round((Math.random() + 1) * 100);
        Long ParentId = appCategory.getParentId();
        if (ParentId == null)
        {
            appCategory.setParentId(Long.parseLong("0"));
        }
         //分类编号
        appCategory.setCategoryCode(ChineseCharacterUtil.convertHanzi2Pinyin(appCategory.getCategoryName()+round,false));
        return toAjax(appCategoryService.insertAppCategory(appCategory));
    }

    /**
     * 修改商品分类
     */
    //@PreAuthorize("@ss.hasPermi('category:edit')")
    @Log(title = "商品分类", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody AppCategory appCategory)
    {
        Long parentId = appCategory.getParentId();
        if (appCategoryService.checkDeptNameUnique(appCategory)=="1")
        {
            return AjaxResult.error("修改分类'" + appCategory.getCategoryName() + "'失败，分类名称已存在");
        }
        else if (appCategory.getId() == parentId)
        {
            return AjaxResult.error("修改分类'" + appCategory.getCategoryName() + "'失败，上级分类不能是自己");
        }
//        else if (StringUtils.equals("1", appCategory.getEnabled().toString())
//                && appCategoryService.selectNormalChildrenDeptById(appCategory.getId()) > 0)
//        {
//            return AjaxResult.error("该分类包含未停用的子分类！");
//        }
        // 4位随机数
        long round = Math.round((Math.random() + 1) * 100);
        //分类编号
        appCategory.setCategoryCode(ChineseCharacterUtil.convertHanzi2Pinyin(appCategory.getCategoryName()+round,false));
        return toAjax(appCategoryService.updateAppCategory(appCategory));
    }

    /**
     * 删除商品分类
     */
    //@PreAuthorize("@ss.hasPermi('category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        int ret = 0;
        if (ids.length==0)
        {
            return AjaxResult.error("请选择你要删除的数据");
        }
        for (Long categoryId : ids) {
            if (appCategoryService.hasChildByDeptId(categoryId))
            {
                return AjaxResult.error("存在下级分类,不允许删除");
            }
            if (appCategoryService.checkDataExist(categoryId))
            {
                return AjaxResult.error("分类存在商品,不允许删除");
            }
            ret = appCategoryService.deleteAppCategory(categoryId);
        }
        return toAjax(ret);

    }

    /**
     * 删除商品分类
     */
    //@PreAuthorize("@ss.hasPermi('category:remove')")
    @Log(title = "商品分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{id}")
    public AjaxResult categoryRemove(@PathVariable Long id)
    {
        if (appCategoryService.hasChildByDeptId(id))
        {
            return AjaxResult.error("存在下级分类,不允许删除");
        }
        if (appCategoryService.checkDataExist(id))
        {
            return AjaxResult.error("分类存在商品,不允许删除");
        }
        return toAjax(appCategoryService.deleteAppCategory(id));
    }

    /**
     * 获取分类下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(AppCategory appCategory)
    {
        List<AppCategory> appCategorys = appCategoryService.selectAppCategoryList(appCategory);
        return AjaxResult.success(appCategoryService.buildDeptTreeSelect(appCategorys));
    }


    /**
     * 修改所属分类
     */
    @PostMapping("/updateType")
    public AjaxResult updateType(@RequestBody updateTypeVo typeVo )
    {
        return toAjax(appCategoryService.updateType(typeVo));
    }

    /**
     * 修改启用状态
     */
    @PostMapping("/updateDeleted")
    public AjaxResult updateDeleted(@RequestBody UpdateDeletedVo deletedVo)
    {
        return toAjax(appCategoryService.updateDeleted(deletedVo));
    }
}
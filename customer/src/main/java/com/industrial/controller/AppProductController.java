package com.industrial.controller;


import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.AjaxResult;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.dto.ProductPriceDto;
import com.industrial.common.pojo.ProductExcel;
import com.industrial.common.utils.poi.ExcelUtil;
import com.industrial.common.vo.CheckVo;
import com.industrial.common.vo.EnableVo;
import com.industrial.common.vo.ProductVo;
import com.industrial.domin.AppProduct;
import com.industrial.domin.AppProductPrice;
import com.industrial.service.AppProductPriceService;
import com.industrial.service.AppProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2021年12月24日 8:51
 */
@RestController
@RequestMapping("/product")
public class AppProductController extends BaseController {
    @Resource
    private AppProductService productService;
    @Resource
    private AppProductPriceService productPriceService;
    public static final Byte SAVE_STATUS = 1;
    public static final Byte APPROVAL_STATUS = 2;
    public static final Byte PUTAWAY_STATUS = 3;
    public static final Byte SOLD_OUT_STATUS = 4;
    public static final String DEFAULT_IMAGE_ADDRESS = "Default image address";

    /**
     * 根据ID查询商品详情
     */
    @GetMapping("/details")
    public ResponseResult<ProductDto> details(@RequestParam Integer productId) {
        ResponseResult<ProductDto> result = null;
        ProductDto productDto = productService.selectProductById(productId);
        result = ResponseResult.success(productDto);
        return result;
    }

    /**
     * 根据分类ID查询与商品名称
     * status = 3 已经上架的商品
     */
    @GetMapping("/classify")
    public ResponseResult<List<ProductDto>> classify(@RequestParam(defaultValue = "0") Integer categoryId, @RequestParam(defaultValue = "") String productName) {
        ResponseResult<List<ProductDto>> result = null;
        startPage();
        List<ProductDto> productDto = productService.selectProductByCategoryId(categoryId, productName, 3);

        result = ResponseResult.success(productDto);
        return result;
    }

    /**
     * 根据状态查询商品
     * status = 1 保存商品
     * status = 2 待审核商品
     * status = 3 审核成功商品(上架商品)
     */
    @GetMapping("/status")
    public ResponseResult<List<ProductDto>> status(@RequestParam Integer status) {
        ResponseResult<List<ProductDto>> result = null;
        startPage();
        List<ProductDto> productDto = productService.selectProductByStatus(status);
        result = ResponseResult.success(productDto);
        return result;
    }

    /**
     * 保存商品
     * 成为保存商品
     * 默认 status = 1
     */
    @PostMapping("/save")
    public ResponseResult save(@RequestBody ProductVo productVo) {
        ResponseResult result = null;
        if (productService.insert(productVo)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 商品审核
     * 成功 status = 3 上架商品
     * 失败 status = 2 成为待审核
     */
    @PostMapping("/check")
    public ResponseResult check(@RequestBody CheckVo checkVo) {
        ResponseResult result = null;
        int status = APPROVAL_STATUS;
        if (checkVo.getCheck()) {
            status = PUTAWAY_STATUS;
        }
        if (productService.changeStatus(status, checkVo.getId())) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 商品删除
     *
     * @param productId
     * @return
     */
    @DeleteMapping("/remove")
    public ResponseResult remove(@RequestParam Integer productId) {
        ResponseResult result = null;
        if (productService.remove(productId)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;

    }

    /**
     * 商品禁用与启用
     * false 禁用 true 启用
     * 状态为0为禁用
     *
     * @param enableVo
     * @return
     */
    @PostMapping("/enable")
    public ResponseResult enable(@RequestBody EnableVo enableVo) {
        ResponseResult result = null;
        if (productService.changeEnabled(enableVo.getProductId(), enableVo.getEnable())) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 商品更新
     *
     * @param productVo
     * @return
     */
    @PutMapping("/update")
    public ResponseResult update(@RequestBody ProductVo productVo) {
        ResponseResult result = null;
        if (productService.update(productVo)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 商品批量上架
     * status = 2
     *
     * @return
     */
    @PostMapping("putaway")
    public ResponseResult putaway(@RequestBody List<Integer> ids) {
        ResponseResult result = null;
        if (productService.putaway(ids, (byte) PUTAWAY_STATUS)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 商品批量下架
     * status = 4
     *
     * @return
     */
    @PostMapping("soldOut")
    public ResponseResult soldOut(@RequestBody List<Integer> ids) {
        ResponseResult result = null;
        if (productService.soldOut(ids, (byte) SOLD_OUT_STATUS)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 编辑商品报价
     */
    @PostMapping("prices")
    public ResponseResult prices(@RequestBody List<AppProductPrice> productPriceList) {
        ResponseResult result = null;
        if (productPriceService.update(productPriceList)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }

    /**
     * 商品报价展示
     */
    @PostMapping("prices/show")
    public ResponseResult<List<ProductPriceDto>> pricesShow(@RequestBody List<Integer> productPriceId) {
        List<ProductPriceDto> productPriceDtoList = productPriceService.selectList(productPriceId);
        return ResponseResult.success(productPriceDtoList);
    }

    /**
     * 导出商品分类列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, @RequestBody List<Integer> ids) {
        List<ProductExcel> productList = productService.selectProductExcelList(ids);
        ExcelUtil<ProductExcel> util = new ExcelUtil<ProductExcel>(ProductExcel.class);
        util.exportExcel(response, productList, "商品数据");
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    public AjaxResult importTemplate() {
        ExcelUtil<ProductExcel> util = new ExcelUtil<ProductExcel>(ProductExcel.class);
        return util.importTemplateExcel("商品数据");
    }

    /**
     * 导入
     */
    @PostMapping("/importStudent")
    public AjaxResult importStudent(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<AppProduct> util = new ExcelUtil<AppProduct>(AppProduct.class);
        List<AppProduct> productList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = productService.importData(productList, updateSupport, operName);
        return AjaxResult.success(message);
    }

}

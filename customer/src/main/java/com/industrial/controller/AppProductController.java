package com.industrial.controller;


import com.industrial.common.core.controller.BaseController;
import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.vo.CheckVo;
import com.industrial.common.vo.ProductVo;
import com.industrial.service.AppProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhu
 * @date 2021年12月24日 8:51
 */
@RestController
@RequestMapping("/product")
public class AppProductController extends BaseController {
    @Resource
    private AppProductService productService;
    public static final Byte FORBIDDEN_STATUS = 0;
    public static final Byte SAVE_STATUS = 1;
    public static final Byte APPROVAL_STATUS = 2;
    public static final Byte PUTAWAY_STATUS = 3;
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
    public ResponseResult<List<ProductDto>> classify(@RequestParam() Integer categoryId,@RequestParam() String productName) {
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
     * @param checkVo
     * @return
     */
    @PostMapping("/enable")
    public ResponseResult enable(@RequestParam CheckVo checkVo) {
        ResponseResult result = null;
        int status = FORBIDDEN_STATUS;
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
     * 商品上架
     * status = 2
     *
     * @return
     */
    @PostMapping("putaway")
    public ResponseResult putaway(List<Integer> ids) {
        ResponseResult result = null;
        if (productService.putaway(ids, (byte) APPROVAL_STATUS)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
}

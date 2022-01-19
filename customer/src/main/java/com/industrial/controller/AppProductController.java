package com.industrial.controller;


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
public class AppProductController {
    @Resource
    private AppProductService productService;

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
     * 根据分类查询
     *  status = 3 已经上架的商品
     */
    @GetMapping("/classify")
    public ResponseResult<List<ProductDto>> classify(@RequestParam Integer status) {
        ResponseResult<List<ProductDto>> result = null;
        List<ProductDto> productDto = productService.selectProductByStatus(status);
        result = ResponseResult.success(productDto);
        return result;
    }
    /**
     * 根据状态查询商品
     *  status = 1 待审核商品
     *  status = 2 审核失败商品
     *  status = 3 审核成功商品(上架商品)
     */
    @GetMapping("/status")
    public ResponseResult<List<ProductDto>> status(@RequestParam Integer status) {
        ResponseResult<List<ProductDto>> result = null;
        List<ProductDto> productDto = productService.selectProductByStatus(status);
        result = ResponseResult.success(productDto);
        return result;
    }
    /**
     * 保存商品
     * 成为待审核
     * 默认 status = 1
     */
    @PostMapping("/putaway")
    public ResponseResult putaway(@RequestBody ProductVo productVo) {
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
     * 成功 status = 3
     * 失败 status = 2
     */
    @PostMapping("/check")
    public ResponseResult check(@RequestBody CheckVo checkVo) {
        ResponseResult result = null;
        int status = 2;
        if (checkVo.getCheck()) {
            status = 3;
        }
        if (productService.changeStatus(status, checkVo.getId())) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
    @DeleteMapping("/remove")
    public ResponseResult remove(@RequestParam Integer productId){
        ResponseResult result = null;
        if (productService.remove(productId)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;

    }
    @PutMapping("/update")
    public ResponseResult update(@RequestParam ProductVo productVo){
        ResponseResult result = null;
        if (productService.update(productVo)) {
            result = ResponseResult.success();
        } else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
}

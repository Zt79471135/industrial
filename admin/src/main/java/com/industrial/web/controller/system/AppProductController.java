package com.industrial.web.controller.system;


import com.industrial.common.core.domain.ResponseCode;
import com.industrial.common.core.domain.ResponseResult;
import com.industrial.common.core.domain.dto.ProductDto;
import com.industrial.system.domain.vo.ProductVo;
import com.industrial.system.service.AppProductService;
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
     * 合作伙伴待审核
     * 审核失败
     * 审核成功
     */
    public static final Byte STATUS = 1;
    public static final Byte ERROR_STATUS = 2;
    public static final Byte SUCCESS_STATUS = 3;
    /**
     * 管理员待审核
     * 审核失败
     * 审核成功
     */
    public static final Byte INSIDE_STATUS = 3;
    public static final Byte INSIDE_ERROR_STATUS = 4;
    public static final Byte INSIDE_SUCCESS_STATUS = 5;
    /**
     * 商品详情
     * 商品图片，商品名称，商品单价，商品数量
     */
    @GetMapping("/details")
    public ResponseResult<ProductDto> details(@RequestParam Integer productId){
        ResponseResult<ProductDto> result = null;
        ProductDto productDto = productService.selectProductById(productId);
        result = ResponseResult.success(productDto);
        return result;
    }
    /**
     * 上架商品
     * status = 1
     */
    @PostMapping("/putaway")
    public ResponseResult putaway(@RequestBody ProductVo productVo){
        ResponseResult result = null;
        if (productService.insert(productVo)){
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
    /**
     * 下架商品
     * status = 0
     */
    @DeleteMapping("/remove")
    public ResponseResult remove(@RequestParam Integer productId){
        ResponseResult result = null;
        ProductVo productVo = new ProductVo();
        productVo.setId(productId);
        if (productService.update(productVo,(byte)0)){
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
    /**
     * 合作伙伴查看待审核
     * status = 1
     */
    @GetMapping("/show")
    public ResponseResult<List<ProductDto>> show(){
        ResponseResult<List<ProductDto>> result = null;
        List<ProductDto> productDtoList =  productService.selectList((byte)1);
        result = ResponseResult.success(productDtoList);
        return result;
    }
    /**
     * 合作伙伴审核
     * 成功
     * status  = 3
     * 失败
     * status = 2
     * @return
     */
    @GetMapping("/pass")
    public ResponseResult pass(Boolean boo,Integer productId){
        ResponseResult result  =  null;
        boolean update = false;
        ProductVo productVo = new ProductVo();
        productVo.setId(productId);
        if (boo){
            update = productService.update(productVo, (byte)3);
        }else {
            update = productService.update(productVo, (byte)2);
        }
        if (update){
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
    /**
     * 商品修改
     * 商品ID非空
     * 合作伙伴修改
     * status  = 1
     */
    @PutMapping("/alter")
    public ResponseResult alter(@RequestBody ProductVo productVo){
        ResponseResult result = null;
        if (productService.update(productVo, (byte)1)){
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
    /**
     *
     */
    /**
     * 内部人员查看待审核
     * status = 1
     */
    @GetMapping("inside/show")
    public ResponseResult<List<ProductDto>> insideShow() {
        ResponseResult<List<ProductDto>> result = null;
        List<ProductDto> productDtoList = productService.selectList((byte) 3);
        result = ResponseResult.success(productDtoList);
        return result;
    }
    /**
     * 内部人员审核
     * status = 3
     * 成功
     * status = 5
     * 失败
     * status = 4
     * @param boo
     * @param productId
     * @return
     */
    @GetMapping("inside/pass")
    public ResponseResult insidePass(Boolean boo,Integer productId){
        ResponseResult result  =  null;
        boolean update = false;
        ProductVo productVo = new ProductVo();
        productVo.setId(productId);
        if (boo){
            update = productService.update(productVo,(byte)5);
        }else {
            update = productService.update(productVo, (byte)4);
        }
        if (update){
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
    /**
     * 商品修改
     * 商品ID非空
     * 内部人员修改
     * status = 3
     */
    @PutMapping("/inside/alter")
    public ResponseResult insideAlter(@RequestBody ProductVo productVo){
        ResponseResult result = null;
        if (productService.update(productVo,(byte)3)){
            result = ResponseResult.success();
        }else {
            result = ResponseResult.error(ResponseCode.ERROR);
        }
        return result;
    }
    /**
     * 关键字快捷查找
     */
    @PostMapping("/keyword ")
    public ResponseResult<List<ProductDto>> keyword(@RequestParam String keyword){
        ResponseResult<List<ProductDto>> result = null;
        List<ProductDto> productDtoList  =  productService.selectProductByKeyword(keyword);
        result = ResponseResult.success(productDtoList);
        return result;
    }
}

package com.industrial.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.ProductVo;
import com.industrial.entity.AppProduct;
import com.industrial.mapper.AppProductMapper;
import com.industrial.service.AppProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2021年12月24日 9:18
 */
@Service
public class AppProductServiceImpl implements AppProductService {
    @Resource
    private AppProductMapper productMapper;

    private static ProductDto apply(AppProduct product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    /**
     * 更改商品状态
     *
     * @param status
     * @return
     */
    @Override
    public boolean changeStatus(int status, Integer productId) {
        AppProduct product = new AppProduct();
        product.setStatus((byte) status);
        product.setId(productId);
        QueryWrapper<AppProduct> qw = new QueryWrapper<>();
        qw.lambda().eq(AppProduct::getDeleted, 1);
        return productMapper.update(product, qw) == 1;
    }

    /**
     * 通过商品ID检索商品详情
     *
     * @param productId
     * @return
     */
    @Override
    public ProductDto selectProductById(Integer productId) {
        ProductDto productDto = new ProductDto();
        AppProduct product = productMapper.selectById(productId);
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    /**
     * 根据状态检索商品
     *
     * @param status
     * @return
     */
    @Override
    public List<ProductDto> selectProductByStatus(int status) {
        QueryWrapper<AppProduct> qw = new QueryWrapper<>();
        qw.lambda().eq(AppProduct::getStatus, (byte) status);
        List<AppProduct> productList = productMapper.selectList(qw);
        if (productList.size() == 0) {
            throw new ServiceException("无此状态的商品");
        } else {
            List<ProductDto> productDtoList = productList.stream().map(product -> {
                ProductDto productDto = new ProductDto();
                BeanUtils.copyProperties(product, productDto);
                return productDto;
            }).collect(Collectors.toList());
            return productDtoList;
        }
    }

    /**
     * 商品保存
     * 成功待审核
     *
     * @param productVo
     * @return
     */
    @Override
    public boolean insert(ProductVo productVo) {
        AppProduct product = new AppProduct();
        BeanUtils.copyProperties(productVo, product);
        return productMapper.insert(product) == 1;
    }

    @Override
    public boolean remove(Integer productId) {
        return productMapper.deleteById(productId) == 1;
    }

    @Override
    public boolean update(ProductVo productVo) {
        Integer id = productVo.getId();
        if (id != 0) {
            AppProduct product = new AppProduct();
            BeanUtils.copyProperties(productVo, product);
            product.setStatus((byte) 1);
            QueryWrapper<AppProduct> qw = new QueryWrapper<>();
            qw.lambda().eq(AppProduct::getId, id);
            return productMapper.update(product, qw) == 1;
        } else {
            throw new ServiceException("id不能为空");
        }

    }

    /**
     * 根据分类id或者商品名称查询
     *
     * @param categoryId
     * @param productName
     * @param status
     * @return
     */
    @Override
    public List<ProductDto> selectProductByCategoryId(Integer categoryId, String productName, int status) {
        QueryWrapper<AppProduct> qw = new QueryWrapper<>();
        qw.lambda().eq(AppProduct::getStatus, (byte) status);
        if (categoryId != 0 || !"".equals(productName)) {
            if (categoryId!=0){
                qw.lambda().eq(AppProduct::getCategoryId,categoryId);
            }
            if (!"".equals(productName)){
                qw.lambda().like(AppProduct::getName,productName);
            }
        }
        List<AppProduct> productList = productMapper.selectList(qw);
        return productList.stream().map(AppProductServiceImpl::apply
        ).collect(Collectors.toList());
    }

}

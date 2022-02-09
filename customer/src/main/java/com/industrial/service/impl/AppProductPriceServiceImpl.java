package com.industrial.service.impl;

import com.industrial.common.dto.ProductPriceDto;
import com.industrial.domin.AppProduct;
import com.industrial.domin.AppProductPrice;
import com.industrial.mapper.AppProductMapper;
import com.industrial.mapper.AppProductPriceMapper;
import com.industrial.service.AppProductPriceService;
import com.industrial.service.AppProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2022年01月26日 14:56
 */
@Service
public class AppProductPriceServiceImpl implements AppProductPriceService {
    @Resource
    private AppProductMapper productMapper;
    @Resource
    private AppProductPriceMapper productPriceMapper;


    @Override
    public List<ProductPriceDto> selectList(List<Integer> productPriceIds) {
        List<AppProductPrice> productPriceList = productPriceIds.stream().map(productPriceId -> {
            return productPriceMapper.selectById(productPriceId);
        }).collect(Collectors.toList());
        List<ProductPriceDto> productPriceDtoList = productPriceList.stream().map(productPrice -> {
            ProductPriceDto priceDto = new ProductPriceDto();
            BeanUtils.copyProperties(productPrice, priceDto);
            Integer productId = productPrice.getProductId();
            AppProduct product = productMapper.selectById(productId);
            priceDto.setProductName(product.getName());
            priceDto.setNumber(product.getNumber());
            priceDto.setPrice(String.valueOf(product.getPrice()));
            priceDto.setSpecifica(product.getSpecifica());
            return priceDto;
        }).collect(Collectors.toList());
        return productPriceDtoList;
    }

    @Override
    public boolean update(List<AppProductPrice> productPriceList) {
        List<Integer> integerList = productPriceList.stream().map(productPrice -> {
            return productPriceMapper.updateById(productPrice);
        }).collect(Collectors.toList());
        return productPriceList.size() == integerList.size();
    }
}

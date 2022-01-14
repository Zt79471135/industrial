package com.industrial.system.service.impl;

import com.industrial.common.core.domain.entity.AppProduct;
import com.industrial.system.mapper.AppProductMapper;
import com.industrial.system.service.AppProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 79471
 */
@Service
public class AppProductServiceImpl implements AppProductService {
    @Resource
    private AppProductMapper productMapper;
    @Override
    public AppProduct getProductById(Long productId) {
        AppProduct appProduct = productMapper.selectById(productId);
        return appProduct;
    }

    @Override
    public String removeProductById(Long productId) {
        return null;
    }
}

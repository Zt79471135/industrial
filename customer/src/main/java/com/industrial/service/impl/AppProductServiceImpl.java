package com.industrial.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.core.domain.entity.SysDictData;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.vo.ProductVo;
import com.industrial.domin.*;
import com.industrial.mapper.*;
import com.industrial.service.AppProductService;
import com.industrial.system.mapper.SysDictDataMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Resource
    private AppProductFileMapper productFileMapper;
    @Resource
    private DictDataMapper dictDataMapper;
    @Resource
    private AppProductCategoryMapper productCategoryMapper;
    @Resource
    private AppImageFileMapper imageFileMapper;
    /**
     * 商品类别
     */
    public static final String DICT_CATE_TYPE = "dict_cate_type";
    /**
     * 商品单位
     */
    public static final String DICT_UNIT = "dict_unit";


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
        Integer categoryId = product.getCategoryId();
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
        /**
         * 当查询上架商品时,同样把禁用商品一起查询出来
         */
        if (status == 3) {
            qw.lambda().eq(AppProduct::getStatus, (byte) 0);
        }
        List<AppProduct> productList = productMapper.selectList(qw);
        if (productList.size() == 0) {
            throw new ServiceException("无此状态的商品");
        } else {
            List<ProductDto> productDtoList = productList.stream().map(this::apply).collect(Collectors.toList());
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
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public boolean insert(ProductVo productVo) {
        AppProduct product = new AppProduct();
        BeanUtils.copyProperties(productVo, product);
        List<Integer> imgIds = productVo.getImgIds();
        if(imgIds.size()>0){
            Integer integer = imgIds.get(0);
            AppImageFile imageFile = imageFileMapper.selectById(integer);
            String filePath = imageFile.getFilePath();
            product.setMainImgUrl(filePath);
        }
        if (productMapper.insert(product) == 1) {
            AppProductFile productFile = new AppProductFile();
            productFile.setProductId(product.getId());
            List<Integer> integerList = imgIds.stream().map(id -> {
                productFile.setFileId(id);
                return productFileMapper.insert(productFile);
            }).collect(Collectors.toList());
            return imgIds.size() == integerList.size();
        } else {
            throw new ServiceException();
        }
    }

    /**
     * 删除
     *
     * @param productId
     * @return
     */
    @Override
    public boolean remove(Integer productId) {
        return productMapper.deleteById(productId) == 1;
    }

    /**
     * 商品更新
     *
     * @param productVo
     * @return
     */
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
        if (categoryId != null || !"".equals(productName)) {
            if (categoryId != null) {
                qw.lambda().eq(AppProduct::getCategoryId, categoryId);
            }
            if (!"".equals(productName)) {
                qw.lambda().like(AppProduct::getName, productName);
            }
        }
        String sql = "and (status = " + status + " or status =" + 0;
        List<AppProduct> productList = productMapper.selectList(qw);
        if (productList.size() == 0) {
            throw new ServiceException("无此状态的商品");
        } else {
            List<ProductDto> productDtoList = productList.stream().map(this::apply).collect(Collectors.toList());
            return productDtoList;
        }

    }

    @Override
    public boolean putaway(List<Integer> ids, byte status) {

        return false;
    }


    private ProductDto apply(AppProduct product) {
        ProductDto productDto = new ProductDto();
        QueryWrapper<DictData> queryWrapper = null;
        /**
         *查询商品分类
         */
        AppProductCategory productCategory = productCategoryMapper.selectById(product.getCategoryId());
        /**
         * 查询商品单位
         */
        queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DictData::getDictType, DICT_UNIT);
        queryWrapper.lambda().eq(DictData::getDictValue, product.getUnitId());
        DictData dictData = dictDataMapper.selectOne(queryWrapper);
        String  dictLabel = dictData.getDictLabel();
        productDto.setUnitName(dictLabel);
        productDto.setCategory(productCategory);
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }
}
